package br.com.compass.cais.controllers;

import br.com.compass.cais.config.security.SecurityFilter;
import br.com.compass.cais.config.security.service.TokenService;
import br.com.compass.cais.controller.StayController;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = StayController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StayControllerTest {

    public static final String BASE_URL = "/api/stay";
    public static final String ID_URL = BASE_URL + "/1";
    public static final String ID_URL_BIND = BASE_URL + "/bind/ship/1";
    public static final String ID_URL_EXIT = BASE_URL + "/exit/1";
    public static final String ID_URL_SHIP_STAYS = BASE_URL + "/ship/1";
    public static final Long ID = 1L;

    @MockBean
    private StayRepository repository;
    @MockBean
    private StayService service;
    @MockBean
    private StayDTOAssembler assembler;
    @MockBean
    private StayInputDisassembler disassembler;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private SecurityFilter securityFilter;
    @Autowired
    private MockMvc mvc;

   @Test
    void update() throws Exception {
        StayRequestDTO request = getStayRequestDTO();
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

    @Test
    void findAll() throws Exception {
        List<StayResponseDTO> stays = Arrays.asList(new StayResponseDTO());
        Page<StayResponseDTO> page = new PageImpl<>(stays);
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

    @Test
    void bind() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(ID_URL_BIND)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    @Test
    void exit() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(ID_URL_EXIT)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void shipStaysBy() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL_SHIP_STAYS)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private StayRequestDTO getStayRequestDTO() {
        return StayRequestDTO.builder()
                .finalPrice(BigDecimal.valueOf(1.0))
                .build();
    }
}