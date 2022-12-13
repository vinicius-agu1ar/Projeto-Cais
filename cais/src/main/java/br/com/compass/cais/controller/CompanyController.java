package br.com.compass.cais.controller;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.services.CompanyService;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.CompanyInputDisassembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyRepository repository;
    private final CompanyService service;
    private final CompanyDTOAssembler assembler;
    private final CompanyInputDisassembler disassembler;

    @GetMapping
    public Page<CompanyResponseDTO> findAll(Pageable pagination) {
        Page<Company> companies = repository.findAll(pagination);
        List<CompanyResponseDTO> companyResponseDTOS = assembler.toCollectionModel(companies.getContent());
        return new PageImpl<>(companyResponseDTOS, pagination, companies.getTotalElements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> findBy(@PathVariable("id") Long id){
        Company company = service.fetchOrFail(id);
        CompanyResponseDTO companyResponseDTO = assembler.toModel(company);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid CompanyRequestDTO request){
        Company company = service.fetchOrFail(id);
        disassembler.copyToDomainObject(request,company);
        company = service.create(company);
        CompanyResponseDTO companyResponseDTO = assembler.toModel(company);

        return ResponseEntity.status(HttpStatus.OK).body(companyResponseDTO);
    }
    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody @Valid CompanyRequestDTO request) {
        Company company = disassembler.toDomainObject(request);
        company = service.create(company);
        CompanyResponseDTO response = assembler.toModel(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping ("/id")
    public void delete (Long id){
        service.delete(id);
    }
}