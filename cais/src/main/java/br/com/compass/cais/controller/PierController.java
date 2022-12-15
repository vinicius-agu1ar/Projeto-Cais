package br.com.compass.cais.controller;

import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.services.PierService;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.PierResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pier")
public class PierController {

    private final PierService service;

    @GetMapping
    public ResponseEntity<Page<PierResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Pier com página de {} registros...", pagination.getPageSize());
        Page<PierResponseDTO> responsePage = service.findAll(pagination);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }
}
