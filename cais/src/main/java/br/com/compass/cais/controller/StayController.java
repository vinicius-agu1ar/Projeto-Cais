package br.com.compass.cais.controller;

import br.com.compass.cais.services.StayService;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
