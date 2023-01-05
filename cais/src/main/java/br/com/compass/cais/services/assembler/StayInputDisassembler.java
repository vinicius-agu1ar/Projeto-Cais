package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
@RequiredArgsConstructor
public class StayInputDisassembler {
    private final ModelMapper modelMapper;

    public Stay toDomainObject(StayRequestDTO stayRequestDTO) {
        return modelMapper.map(stayRequestDTO, Stay.class);
    }

    public void copyToDomainObject(StayRequestDTO stayRequestDTO, Stay stay){
        modelMapper.map(stayRequestDTO, stay);
    }
}
