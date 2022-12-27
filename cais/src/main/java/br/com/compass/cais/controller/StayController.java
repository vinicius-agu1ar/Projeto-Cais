package br.com.compass.cais.controller;

import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stay")
public class StayController {

    private final StayService service;



    @PostMapping("/{id}/stay/{stayId}")
    public ResponseEntity<Void> bindStayShip(@PathVariable("id") Long id, @PathVariable("shipId") Long shipId) {
        log.info("Vinculando um Stay a um Ship...");
        service.bind(id,shipId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
