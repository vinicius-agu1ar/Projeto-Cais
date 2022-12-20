package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PierDTOAssembler {

    private final ModelMapper modelMapper;

    public PierResponseDTO toModel(Pier pier){
        return modelMapper.map(pier, PierResponseDTO.class);
    }

    public List<PierResponseDTO> toCollectionModel(List<Pier> piers){
        return piers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
