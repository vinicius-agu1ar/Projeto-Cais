package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import br.com.compass.cais.services.dto.response.stay.StayResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class StayDTOAssembler {

     private final ModelMapper modelMapper;

    public StayResponseDTO toModel(Stay stay){
        return modelMapper.map(stay,StayResponseDTO.class);
    }

    public StayResumeResponseDTO toResumeModel(Stay stay){
        return modelMapper.map(stay,StayResumeResponseDTO.class);
    }
    public List<StayResumeResponseDTO> toCollectionResumeModel(List<Stay> stays){
        return stays.stream()
                .map(this::toResumeModel)
                .collect(Collectors.toList());
    }
    public List<StayResponseDTO> toCollectionModel(List<Stay> stays){
        return stays.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
