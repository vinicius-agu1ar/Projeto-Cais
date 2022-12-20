package br.com.compass.cais.controller;

import br.com.compass.cais.services.ShipService;
import br.com.compass.cais.services.dto.response.ShipResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ship")
public class ShipController {

    private final ShipService service;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo um Ship por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ShipResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination){
        log.info("Listando Navios com página de {} registros...", pagination.getPageSize());
        List<ShipResponseDTO> responsePage = service.findAll(pagination).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);

    }
}
