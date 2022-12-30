package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ProfileController;
import br.com.compass.cais.repository.ProfileRepository;
import br.com.compass.cais.services.ProfileService;
import br.com.compass.cais.services.assembler.ProfileDTOAssembler;
import br.com.compass.cais.services.assembler.ProfileInputDisassembler;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfileControllerTest {

    public static final String BASE_URL = "/api/profile";
    public static final String ID_URL = BASE_URL + "/1";

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
    void findAll() throws Exception {
        List<ProfileResponseDTO> profiles = Arrays.asList(new ProfileResponseDTO());
        Page<ProfileResponseDTO> page = new PageImpl<>(profiles);
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
    void findById() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}
