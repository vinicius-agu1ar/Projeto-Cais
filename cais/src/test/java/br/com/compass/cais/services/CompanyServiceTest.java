package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.exceptions.CompanyNotFoundException;
import br.com.compass.cais.exceptions.EntityInUseException;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Mock
    private Pageable pageable;

    @Test
    void shouldFindCompanyById_success() {
        Company company = new Company();
        CompanyResponseDTO response = new CompanyResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(company));
        Mockito.when(assembler.toModel(company)).thenReturn(response);

        CompanyResponseDTO companyResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getName(), companyResponseDTO.getName());
        Assertions.assertEquals(response, companyResponseDTO);
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

    @Test
    void shouldDeleteCompany_error() {
        doThrow(new EmptyResultDataAccessException(21)).when(repository).deleteById(any());
        Assertions.assertThrows(CompanyNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldDeleteCompany_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }

    @Test
    void shouldFindAllCompanies_success() {
        Page<Company> companiesPage = new PageImpl<>(List.of(new Company()));
        List<CompanyResponseDTO> companyResponseDTOs = Arrays.asList(new CompanyResponseDTO());
        PageImpl<CompanyResponseDTO> companyResponseDTOPage = new PageImpl<>(companyResponseDTOs, pageable, companiesPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(companiesPage);
        Mockito.when(assembler.toCollectionModel(companiesPage.getContent())).thenReturn(companyResponseDTOs);

        Page<CompanyResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(companyResponseDTOPage, all);
    }
}
