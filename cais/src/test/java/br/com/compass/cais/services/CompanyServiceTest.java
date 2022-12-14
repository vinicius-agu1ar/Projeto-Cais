package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.CompanyInputDisassembler;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.response.CompanyResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    static final Long ID = 1L;
    @InjectMocks
    private CompanyService service;

    @Mock
    private CompanyRepository repository;

    @Mock
    private CompanyDTOAssembler assembler;

    @Mock
    private CompanyInputDisassembler disassembler;

    @Test
    void shouldFindCompanyById_success() {
        Company company = new Company();
        CompanyResponseDTO response = new CompanyResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(company));
        Mockito.when(assembler.toModel(company)).thenReturn(response);

        CompanyResponseDTO companyResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response, companyResponseDTO);
    }

    @Test
    void shouldfetchOrFail_success() {
        Company company = new Company();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(company));
        Company company1 = service.fetchOrFail(ID);
        assertEquals(company, company1);
    }

    @Test
    void shouldCreateCompany_success() {
        CompanyRequestDTO request = new CompanyRequestDTO();
        CompanyResponseDTO response = new CompanyResponseDTO();
        Company company = new Company();

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(company);
        Mockito.when(repository.save(any())).thenReturn(company);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        CompanyResponseDTO companyResponseDTO = service.create(request);
        assertEquals(response, companyResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldUpdateCompany_success() {
        Company company = new Company();
        CompanyRequestDTO request = new CompanyRequestDTO();
        CompanyResponseDTO response = new CompanyResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(company));
        Mockito.when(repository.save(any())).thenReturn(company);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        CompanyResponseDTO companyResponseDTO = service.update(ID,request);
        assertEquals(response, companyResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldDeleteCompany_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    //Implementar o teste de Erro do delete

//        @Test
//    void shouldFindAllCompanies_success(){
//        List<CompanyResponseDTO> companyResponseDTOs = Arrays.asList(new CompanyResponseDTO());
//        Page<Company> companiesPage = new PageImpl<>(List.of(new Company()));
//
//        PageImpl<CompanyResponseDTO> companyResponseDTOS1 = new PageImpl<>(companyResponseDTOs, pageable, companiesPage.getTotalElements());
//
//        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(companiesPage);
//        Mockito.when(assembler.toCollectionModel(companiesPage.getContent())).thenReturn(companyResponseDTOs);
//
//        Page<CompanyResponseDTO> all = service.findAll(any(Pageable.class));
//
//        Assertions.assertEquals(companyResponseDTOS1, all.getTotalElements());
//    }
}
