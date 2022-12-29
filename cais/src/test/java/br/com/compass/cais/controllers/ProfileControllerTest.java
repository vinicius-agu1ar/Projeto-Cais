package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ProfileController;
import br.com.compass.cais.repository.ProfileRepository;
import br.com.compass.cais.services.ProfileService;
import br.com.compass.cais.services.assembler.ProfileDTOAssembler;
import br.com.compass.cais.services.assembler.ProfileInputDisassembler;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfileControllerTest {

    public static final String BASE_URL = "/api/profile";
    @MockBean
    private ProfileRepository repository;
    @MockBean
    private ProfileService service;
    @MockBean
    private ProfileDTOAssembler assembler;
    @MockBean
    private ProfileInputDisassembler disassembler;
    @Autowired
    private MockMvc mvc;

    @Test
    void create() throws Exception {
        ProfileRequestDTO request = getProfileRequestDTO();
        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    private ProfileRequestDTO getProfileRequestDTO() {
        return ProfileRequestDTO.builder()
                .name("Test")
                .build();
    }
}
