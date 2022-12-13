package br.com.compass.cais.controller;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository repository;

    private final CompanyDTOAssembler assembler;

    @GetMapping
    public Page<CompanyResponseDTO> listCompanies(Pageable pagination) {
        Page<Company> companies = repository.findAll(pagination);
        List<CompanyResponseDTO> companyResponseDTOS = assembler.toCollectionModel(companies.getContent());
        Page<CompanyResponseDTO> pagesDTO = new PageImpl<>(companyResponseDTOS, pagination, companies.getTotalElements());
        return pagesDTO;
    }


}