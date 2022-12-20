package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyDTOAssembler {

    private final ModelMapper modelMapper;

    public CompanyResponseDTO toModel(Company company){
        return modelMapper.map(company,CompanyResponseDTO.class);
    }

    public List<CompanyResponseDTO> toCollectionModel(List<Company> companies){
        return companies.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
