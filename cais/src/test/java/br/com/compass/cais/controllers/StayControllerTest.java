package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ShipController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = ShipController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StayControllerTest {

    public static final String BASE_URL = "/api/ship";
    public static final String ID_URL = BASE_URL + "/1";
    public static final Long ID = 1L;
}
