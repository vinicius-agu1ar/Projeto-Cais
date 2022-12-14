package br.com.compass.cais.controller;

import br.com.compass.cais.services.CompanyService;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService service;

    @GetMapping
    public Page<CompanyResponseDTO> findAll(@PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Companies com página de {} registros...", pagination.getPageSize());
        return service.findAll(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Company por id...");
        CompanyResponseDTO companyResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid CompanyRequestDTO request){
        log.info("Atualizando Company por id...");
        CompanyResponseDTO companyResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
    }
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody @Valid CompanyRequestDTO request) {
        log.info("Criando uma nova Company...");
        CompanyResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping ("/id")
    public ResponseEntity<Void> delete (Long id){
        log.info("Excluindo uma Company por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}