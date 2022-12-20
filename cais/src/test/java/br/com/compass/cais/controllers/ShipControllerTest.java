package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ShipController;
import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.ShipService;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
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

    @Test
    void create() throws Exception {
        ShipRequestDTO request = getShipRequestDTO();
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

    @Test
    void update() throws Exception {
        ShipRequestDTO request = getShipRequestDTO();
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

    private ShipRequestDTO getShipRequestDTO() {
        return ShipRequestDTO.builder()
                .name("Test")
                .weight(100.65)
                .companyId(new Company())
                .pierId(new Pier())
                .build();
    }
}
