package br.com.compass.cais.controller;

import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
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

    @GetMapping
    public ResponseEntity<List<StayResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Stays com página de {} registros...", pagination.getPageSize());
        List<StayResponseDTO> responsePage = service.findAll(pagination).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StayResponseDTO> findById(@PathVariable("id") Long id){
        log.info("Pesquisando Stay por ID....");
        StayResponseDTO stayResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(stayResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StayResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid StayRequestDTO request){
        log.info("Atualizando Stay por id...");
        StayResponseDTO stayResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(stayResponseDTO);
    }

    @PostMapping("/bind/ship/{id}")
    public ResponseEntity<StayResponseDTO> bind(@PathVariable("id") Long id){
        log.info("Vinculando Stay com Ship...");
        StayResponseDTO bind = service.bind(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(bind);
    }

    @PostMapping("/exit/{id}")
    public ResponseEntity<StayResponseDTO> exit(@PathVariable("id") Long id) {
        log.info("desvinculando uma Stay a um Ship...");
        StayResponseDTO exit = service.exit(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(exit);
    }
    @GetMapping("/ship/{id}")
    public ResponseEntity<List<StayResponseDTO>> shipStaysBy(@PathVariable("id") Long id) {
        log.info("Listando Stays de um Ship pelo seu ID");
        List<StayResponseDTO> stayResponseDTO = service.shipStays(id);
        return ResponseEntity.status(HttpStatus.OK).body(stayResponseDTO);
    }
}
