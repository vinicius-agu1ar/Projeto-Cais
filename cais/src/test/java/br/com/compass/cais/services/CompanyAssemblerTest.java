package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.PierDTOAssembler;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CompanyAssemblerTest {

    @InjectMocks
    private CompanyDTOAssembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        Company company = getCompany();
        CompanyResponseDTO companyResponseDTO = service.toModel(company);
        Assertions.assertEquals(company.getName(), companyResponseDTO.getName());
    }

    @Test
    void toColletionModelSucess(){
        List<Company> company = List.of(getCompany());
        List<CompanyResponseDTO> companyResponseDTO = service.toCollectionModel(company);
        Assertions.assertEquals(company.get(0).getName(), companyResponseDTO.get(0).getName());
    }


    private static Company getCompany() {
        Company company = new Company();
        company.setOrigin(Origin.INTERNATIONAL);
        company.setName("a");
        company.setId(1L);
        company.setCnpj("1111111111111111111111111111");
        return company;
    }
}
