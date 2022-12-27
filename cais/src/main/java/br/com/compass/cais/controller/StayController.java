package br.com.compass.cais.controller;

import br.com.compass.cais.services.StayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stay")
public class StayController {

    private final StayService stayService;
    @PostMapping("/{id}/stay/{stayId}")
    public ResponseEntity<Void> bindStayShip(@PathVariable("id") Long id, @PathVariable("shipId") Long shipId) {
        log.info("Vinculando um Stay a um Ship...");
        stayService.bind(id,shipId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
