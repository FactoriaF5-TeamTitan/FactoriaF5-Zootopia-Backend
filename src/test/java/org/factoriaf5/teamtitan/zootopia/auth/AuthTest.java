package org.factoriaf5.teamtitan.zootopia.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class AuthTest {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser // @WithMockUser(username="minnie") podemos personalizar el username y más...
    void testUsingAnnotationMockUser() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/login"))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();

                System.out.println(response.getContentAsString());

        assertThat(response.getContentAsString(), containsString("roles"));
        assertThat(response.getContentAsString(), containsString("Logged"));

        assertThat(response.getContentAsString(),
                is("{\"roles\":\"ROLE_USER\",\"message\":\"Logged\",\"username\":\"user\"}"));
    }

    @Test
    @WithMockUser(roles = "USER")
    void testRoleUserCanAccessPathGetCountries() throws Exception {
        mockMvc.perform(get("/api/v1/countries"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testRoleUserCannotAccessPathPostCountries() throws Exception {
        mockMvc.perform(post("/api/v1/countries"))
                .andExpect(status().isForbidden())
                .andReturn()
                .getResponse();
    }

    @Test
    @WithAnonymousUser
    void testUsingAnonymousUser() throws Exception {
        mockMvc.perform(get("/api/v1/login"))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

    }

    @Test
    void testLogout() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/logout"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(204));
    }

    @Test
    void testUserUnauthenticated() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/login"))
                .andExpect(unauthenticated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    void testUserCanLogin() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/api/v1/login").with(user("POPP").password("pass").roles("ADMIN")))
                .andExpect(authenticated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(HttpStatus.ACCEPTED.value()));
        assertThat(response.getContentAsString(), containsString("Logged"));
    }

    @Test
    void testUserCannotPostCountries() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(post("/api/v1/countries").with(user("POPP").password("pass").roles("USER")))
                .andExpect(authenticated())
                .andReturn()
                .getResponse();

        assertThat(response.getStatus(), is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    void testBasicAuth() throws Exception {
        MockHttpServletResponse response = mockMvc
                .perform(get("/api/v1/login").with(httpBasic("pepe", "password")))
                .andExpect(status().isAccepted())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString(),
                is("{\"roles\":\"ROLE_USER\",\"message\":\"Logged\",\"username\":\"pepe\"}"));
    }

}
