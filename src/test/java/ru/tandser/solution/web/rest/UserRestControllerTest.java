package ru.tandser.solution.web.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.tandser.solution.domain.User;
import ru.tandser.solution.service.UserService;
import ru.tandser.solution.web.AbstractControllerTest;
import ru.tandser.solution.web.json.JsonConverter;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tandser.solution.MealTestData.MEAL_MATCHER;
import static ru.tandser.solution.MealTestData.reverseOrderMeals;
import static ru.tandser.solution.UserTestData.*;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_PATH = UserRestController.REST_PATH + '/';

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PATH + user.getId()).with(userAccount))
                .andExpect(status().isForbidden());

        mockMvc.perform(get(REST_PATH + user.getId()).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(USER_MATCHER.contentMatcher(user));

        mockMvc.perform(get(REST_PATH + nonExistentUser.getId()).with(adminAccount))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_PATH).with(userAccount))
                .andExpect(status().isForbidden());

        mockMvc.perform(get(REST_PATH).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(USER_MATCHER.contentMatcher(Arrays.asList(admin, user)));
    }

    @Test
    public void testGetByEmail() throws Exception {
        mockMvc.perform(get(REST_PATH + "by?email=" + user.getEmail()).with(userAccount))
                .andExpect(status().isForbidden());

        mockMvc.perform(get(REST_PATH + "by?email=" + user.getEmail()).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(USER_MATCHER.contentMatcher(user));

        mockMvc.perform(get(REST_PATH + "by?email=" + nonExistentUser.getEmail()).with(adminAccount))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetWithMeals() throws Exception {
        mockMvc.perform(get(REST_PATH + "details/" + user.getId()).with(userAccount))
                .andExpect(status().isForbidden());

        ResultActions response = mockMvc.perform(get(REST_PATH + "details/" + user.getId()).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        User returned = JsonConverter.fromJson(response.andReturn().getResponse().getContentAsString(), User.class);

        assertTrue(USER_MATCHER.equals(user, returned));
        assertTrue(MEAL_MATCHER.equals(reverseOrderMeals, returned.getMeals()));

        mockMvc.perform(get(REST_PATH + "details/" + nonExistentUser.getId()).with(adminAccount))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete(REST_PATH + user.getId()).with(userAccount))
                .andExpect(status().isForbidden());

        mockMvc.perform(delete(REST_PATH + user.getId()).with(adminAccount))
                .andExpect(status().isOk());

        assertTrue(USER_MATCHER.equals(Collections.singletonList(admin), userService.getAll()));

        mockMvc.perform(delete(REST_PATH + nonExistentUser.getId()).with(adminAccount))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveWithLocation() throws Exception {
        mockMvc.perform(post(REST_PATH).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(newUser)))
                .andExpect(status().isForbidden());

        ResultActions response = mockMvc.perform(post(REST_PATH).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(newUser)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        User returned = JsonConverter.fromJson(response.andReturn().getResponse().getContentAsString(), User.class);

        assertTrue(USER_MATCHER.equals(newUser, returned));
        assertTrue(USER_MATCHER.equals(newUser, userService.get(returned.getId())));

        mockMvc.perform(post(REST_PATH).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(REST_PATH + updatedUser.getId()).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedUser)))
                .andExpect(status().isForbidden());

        mockMvc.perform(put(REST_PATH + updatedUser.getId()).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedUser)))
                .andExpect(status().isOk());

        assertTrue(USER_MATCHER.equals(updatedUser, userService.get(updatedUser.getId())));

        mockMvc.perform(put(REST_PATH + nonExistentUser.getId()).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(nonExistentUser)))
                .andExpect(status().isNotFound());

        mockMvc.perform(put(REST_PATH + updatedUser.getId()).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(nonExistentUser)))
                .andExpect(status().isBadRequest());

        mockMvc.perform(put(REST_PATH + conflictedUser.getId()).with(adminAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(conflictedUser)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testToggle() throws Exception {
        mockMvc.perform(put(REST_PATH + "toggle/" + user.getId() + "?state=false").with(adminAccount))
                .andExpect(status().isOk());

        assertFalse(userService.get(user.getId()).getEnabled());

        mockMvc.perform(put(REST_PATH + "toggle/" + user.getId() + "?state=true").with(adminAccount))
                .andExpect(status().isOk());

        assertTrue(userService.get(user.getId()).getEnabled());

        mockMvc.perform(put(REST_PATH + "toggle/" + nonExistentUser.getId() + "?state=false").with(adminAccount))
                .andExpect(status().isNotFound());

        mockMvc.perform(put(REST_PATH + "toggle/" + user.getId() + "?state=false").with(userAccount))
                .andExpect(status().isForbidden());
    }
}