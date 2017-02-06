package ru.tandser.solution.web.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.tandser.solution.web.AbstractControllerTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.tandser.solution.UserTestData.admin;

public class UserRestControllerTest extends AbstractControllerTest {

    private static final String REST_PATH = UserRestController.REST_PATH + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_PATH + admin.getId()).with(httpBasic(admin.getEmail(), admin.getPassword())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(admin.getName()));
    }

}