package br.com.compass.cais.controllers;

import br.com.compass.cais.config.security.SecurityFilter;
import br.com.compass.cais.config.security.service.TokenService;
import br.com.compass.cais.controller.UserController;
import br.com.compass.cais.entites.User;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.repository.UserRepository;
import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.UserService;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.assembler.UserDTOAssembler;
import br.com.compass.cais.services.assembler.UserInputDisassembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import br.com.compass.cais.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    public static final String BASE_URL = "/api/user";
    public static final String ID_URL = BASE_URL + "/1";

    @MockBean
    private UserRepository repository;
    @MockBean
    private UserService service;
    @MockBean
    private UserDTOAssembler assembler;
    @MockBean
    private UserInputDisassembler disassembler;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private SecurityFilter securityFilter;
    @Autowired
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        List<UserResponseDTO> users = Arrays.asList(new UserResponseDTO());
        Page<UserResponseDTO> page = new PageImpl<>(users);
        when(service.findAll(any(Pageable.class))).thenReturn(page);
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse resposta = result.getResponse();
        assertEquals(HttpStatus.OK.value(), resposta.getStatus());
    }

    @Test
    void create() throws Exception {
        UserRequestDTO request = getUserRequestDTO();
        String input = TestUtils.mapToJson(request);
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        UserRequestDTO request = getUserRequestDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private UserRequestDTO getUserRequestDTO() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setPassword("test");
        userRequestDTO.setEmail("test1");
        return userRequestDTO;
    }
}
