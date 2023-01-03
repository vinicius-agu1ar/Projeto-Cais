package br.com.compass.cais.controllers;

import br.com.compass.cais.config.security.SecurityFilter;
import br.com.compass.cais.config.security.service.TokenService;
import br.com.compass.cais.controller.AuthController;
import br.com.compass.cais.controller.CompanyController;
import br.com.compass.cais.entites.User;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {
    public static final String BASE_URL = "/api/login";

    @MockBean
    private AuthenticationManager manager;

    @MockBean
    private TokenService service;

//    @MockBean
//    private TokenService tokenService;

    @MockBean
    private SecurityFilter securityFilter;

    @Autowired
    private MockMvc mvc;

    @Test
    void login() throws Exception {
        UserRequestDTO request = getUserRequestDTO();
        String input = TestUtils.mapToJson(request);
//        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
//        when(manager.authenticate(any())).thenReturn(authenticationToken);
//        when(service.generateToken((any()))).thenReturn("test");

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertNotEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private UserRequestDTO getUserRequestDTO() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword("test");
        userRequestDTO.setEmail("test1");
        return userRequestDTO;
    }
}
