package ru.tandser.solution.web.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import ru.tandser.solution.domain.Meal;
import ru.tandser.solution.service.MealService;
import ru.tandser.solution.web.AbstractControllerTest;
import ru.tandser.solution.web.json.JsonConverter;

import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tandser.solution.MealTestData.*;
import static ru.tandser.solution.UserTestData.user;

public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_PATH = MealRestController.REST_PATH + '/';

    private MealService mealService;

    @Autowired
    public void setMealService(MealService mealService) {
        this.mealService = mealService;
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PATH + existingMeal.getId()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get(REST_PATH + existingMeal.getId()).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_MATCHER.contentMatcher(existingMeal));

        mockMvc.perform(get(REST_PATH + nonExistentMeal.getId()).with(userAccount))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_PATH))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get(REST_PATH).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_WITH_EXCESS_MATCHER.contentMatcher(mealsWithExcess));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_PATH + "between?from=" + from + "&to=" + to))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get(REST_PATH + "between?from=" + from + "&to=" + to).with(userAccount))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MEAL_WITH_EXCESS_MATCHER.contentMatcher(betweenMealsWithExcess));

        mockMvc.perform(get(REST_PATH + "between?from=" + "&to=").with(userAccount))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete(REST_PATH + existingMeal.getId()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete(REST_PATH + existingMeal.getId()).with(userAccount))
                .andExpect(status().isOk());

        assertTrue(MEAL_MATCHER.equals(updatedMeals, mealService.getAll(user.getId())));

        mockMvc.perform(delete(REST_PATH + nonExistentMeal.getId()).with(userAccount))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void testSaveWithLocation() throws Exception {
        mockMvc.perform(post(REST_PATH)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(newMeal)))
                .andExpect(status().isUnauthorized());

        ResultActions response = mockMvc.perform(post(REST_PATH).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(newMeal)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        Meal returned = JsonConverter.fromJson(response.andReturn().getResponse().getContentAsString(), Meal.class);

        assertTrue(MEAL_MATCHER.equals(newMeal, returned));
        assertTrue(MEAL_MATCHER.equals(newMeal, mealService.get(returned.getId(), user.getId())));

        mockMvc.perform(post(REST_PATH).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedMeal)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        mockMvc.perform(post(REST_PATH).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(duplicatedMeal)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put(REST_PATH + updatedMeal.getId())
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedMeal)))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(put(REST_PATH + updatedMeal.getId()).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(updatedMeal)))
                .andExpect(status().isOk());

        assertTrue(MEAL_MATCHER.equals(updatedMeal, mealService.get(updatedMeal.getId(), user.getId())));

        mockMvc.perform(put(REST_PATH + nonExistentMeal.getId()).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(nonExistentMeal)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        mockMvc.perform(put(REST_PATH + updatedMeal.getId()).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(nonExistentMeal)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));

        mockMvc.perform(put(REST_PATH + conflictedMeal.getId()).with(userAccount)
                .contentType(APPLICATION_JSON_VALUE)
                .content(JsonConverter.toJson(conflictedMeal)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }
}