package ru.tandser.solution.web.rest;

import org.junit.Test;
import ru.tandser.solution.web.AbstractControllerTest;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.tandser.solution.UserTestData.*;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_PATH = UserRestController.REST_PATH + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PATH + admin.getId()).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(admin.getName()));

        mockMvc.perform(get(REST_PATH + nonExistentUser.getId()).with(adminAccount))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(REST_PATH + user.getId()).with(userAccount))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_PATH).with(adminAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name").value(admin.getName()))
                .andExpect(jsonPath("$[1].name").value(user.getName()));
    }
}