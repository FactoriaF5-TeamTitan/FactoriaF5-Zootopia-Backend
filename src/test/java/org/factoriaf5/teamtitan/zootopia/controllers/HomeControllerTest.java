package org.factoriaf5.teamtitan.zootopia.controllers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = HomeController.class)
@AutoConfigureMockMvc(addFilters = false) // to disable security
public class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Should return message Hello Spring Boot")
    void testIndex() throws Exception {
        String expected = "Hello Spring Boot!!!";

        MockHttpServletResponse response = mockMvc.perform(get("")
                .accept(MediaType.APPLICATION_JSON)
                .content("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(200));
        assertThat(response.getContentAsString(), is(expected));
    }
}
