package br.com.compass.cais.controllers;

import br.com.compass.cais.controller.ShipController;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = ShipController.class)
@AutoConfigureMockMvc(addFilters = false)
class ShipControllerTest {
}
