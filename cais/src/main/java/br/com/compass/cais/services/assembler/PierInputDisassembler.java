package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PierInputDisassembler {

    private final ModelMapper modelMapper;

    public Pier toDomainObject(PierRequestDTO pierRequestDTO) {
        return modelMapper.map(pierRequestDTO, Pier.class);
    }

    public void copyToDomainObject(PierRequestDTO pierRequestDTO, Pier pier){
        modelMapper.map(pierRequestDTO, pier);
    }
}
