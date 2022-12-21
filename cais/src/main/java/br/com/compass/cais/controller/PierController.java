package br.com.compass.cais.controller;

import br.com.compass.cais.services.PierService;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
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
@RequestMapping("/api/pier")
public class PierController {

    private final PierService service;

    @GetMapping
    public ResponseEntity<List<PierResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Pier com página de {} registros...", pagination.getPageSize());
        List<PierResponseDTO> responsePage = service.findAll(pagination).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PierResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Pier por id...");
        PierResponseDTO pierResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @PostMapping
    public ResponseEntity<PierResponseDTO> create(@RequestBody @Valid PierRequestDTO request) {
        log.info("Criando um novo Pier...");
        PierResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/ship/{shipId}")
    public ResponseEntity<Void> bindPierShip(@PathVariable("id") Long id, @PathVariable("shipId") Long shipId) {
        log.info("Vinculando um Pier a um Ship...");
        service.bind(id,shipId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/ship/{id}")
    public ResponseEntity<Void> unlinkPierShip(@PathVariable("id") Long id) {
        log.info("desvinculando um Pier a um Ship...");
        service.unlink(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PierResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid PierRequestDTO request){
        log.info("Atualizando Company por id...");
        PierResponseDTO pierResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(pierResponseDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo um Pier por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
