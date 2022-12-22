package br.com.compass.cais.controller;

import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.services.CompanyService;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResumeResponseDTO;
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
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService service;

    @GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> findAll(@RequestParam(required = false, name = "Origin") Origin origin, @PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Companies com página de {} registros...", pagination.getPageSize());
        List<CompanyResponseDTO> responsePage = service.verifyCompanyResponseDTO(origin,pagination);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Company por id...");
        CompanyResponseDTO companyResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
    }

    @GetMapping("/{id}/ships")
    public ResponseEntity<List<ShipResumeResponseDTO>> findAllShips(@PathVariable("id") Long id){
        log.info("Listando Ships de uma determinada Company...");
        List<ShipResumeResponseDTO> shipsResumeResponseDTO = service.findAll(id);
        return ResponseEntity.status(HttpStatus.OK).body(shipsResumeResponseDTO);
    }

    @PostMapping("/{id}/ship/{shipId}")
    public ResponseEntity<Void> bindCompanyShip(@PathVariable("id") Long id, @PathVariable("shipId") Long shipId) {
        log.info("Vinculando uma Company a um Ship...");
        service.bind(id,shipId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/ship/{id}")
    public ResponseEntity<Void> unlinkCompanyShip(@PathVariable("id") Long id) {
        log.info("Desvinculando um Company a um Ship...");
        service.unlink(id);
        return ResponseEntity.status(HttpStatus.OK).build();
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

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo uma Company por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}