package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ShipController;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.ShipService;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = ShipController.class)
@AutoConfigureMockMvc(addFilters = false)
class ShipControllerTest {

    public static final String BASE_URL = "/api/ship";
    public static final String ID_URL = BASE_URL + "/1";
    public static final Long ID = 1L;

    @MockBean
    private ShipRepository repository;
    @MockBean
    private ShipService service;
    @MockBean
    private ShipDTOAssembler assembler;
    @MockBean
    private ShipInputDisassembler disassembler;
    @Autowired
    private MockMvc mvc;

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
}
