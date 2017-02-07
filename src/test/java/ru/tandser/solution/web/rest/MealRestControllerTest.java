package ru.tandser.solution.web.rest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tandser.solution.service.MealService;
import ru.tandser.solution.web.AbstractControllerTest;

import static org.junit.Assert.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .andExpect(status().isNotFound());
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
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRemove() throws Exception {
        mockMvc.perform(delete(REST_PATH + existingMeal.getId()))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(delete(REST_PATH + existingMeal.getId()).with(userAccount))
                .andExpect(status().isOk());

        assertTrue(MEAL_MATCHER.equals(updatedMeals, mealService.getAll(user.getId())));

        mockMvc.perform(delete(REST_PATH + nonExistentMeal.getId()).with(userAccount))
                .andExpect(status().isNotFound());
    }
}