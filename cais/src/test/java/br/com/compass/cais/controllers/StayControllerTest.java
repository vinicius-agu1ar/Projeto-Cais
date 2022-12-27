package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.StayController;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = StayController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StayControllerTest {

    public static final String BASE_URL = "/api/stay";
    public static final String ID_URL = BASE_URL + "/1";
    public static final Long ID = 1L;

    @MockBean
    private StayRepository repository;
    @MockBean
    private StayService service;
    @MockBean
    private StayDTOAssembler assembler;
    @MockBean
    private StayInputDisassembler disassembler;
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

    private StayRequestDTO getStayRequestDTO() {
        return StayRequestDTO.builder()
                .entry(null)
                .exit(null)
                .build();
    }
}
