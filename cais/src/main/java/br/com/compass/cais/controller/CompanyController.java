package br.com.compass.cais.controller;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.services.CompanyService;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.CompanyInputDisassembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
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
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository repository;
    private final CompanyService companyService;
    private final CompanyDTOAssembler assembler;
    private final CompanyInputDisassembler disassembler;
    private final CompanyRepository companyRepository;

    @GetMapping
    public Page<CompanyResponseDTO> listCompanies(Pageable pagination) {
        Page<Company> companies = repository.findAll(pagination);
        List<CompanyResponseDTO> companyResponseDTOS = assembler.toCollectionModel(companies.getContent());
        Page<CompanyResponseDTO> pagesDTO = new PageImpl<>(companyResponseDTOS, pagination, companies.getTotalElements());
        return pagesDTO;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody @Valid CompanyRequestDTO request) {
        Company company = disassembler.toDomainObject(request);
        company = companyService.adicionar(company);
        CompanyResponseDTO response = assembler.toModel(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping ("/id")
    public void delete (Long id){
        getCompany(id);
        companyRepository.deleteById(id);
    }

    private Company getCompany(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
    }
}