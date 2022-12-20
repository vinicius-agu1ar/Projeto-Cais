package br.com.compass.cais.controller;

import br.com.compass.cais.services.ShipService;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

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
        log.info("Listando Ships com página de {} registros...", pagination.getPageSize());
        List<ShipResponseDTO> responsePage = service.findAll(pagination).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);

    }

    @PostMapping
    public ResponseEntity<ShipResponseDTO> create(@RequestBody @Valid ShipRequestDTO request) {
        log.info("Cadastrando um novo Ship...");
        ShipResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid ShipRequestDTO request){
        log.info("Atualizando Ship por id...");
        ShipResponseDTO shipResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(shipResponseDTO);
    }
}
