package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyInputDisassembler {
    private final ModelMapper modelMapper;

    public Company toDomainObject(CompanyRequestDTO companyRequestDTO) {
        return modelMapper.map(companyRequestDTO, Company.class);
    }

    public void copyToDomainObject(CompanyRequestDTO companyRequestDTO, Company company){
        modelMapper.map(companyRequestDTO, company);
    }
}
