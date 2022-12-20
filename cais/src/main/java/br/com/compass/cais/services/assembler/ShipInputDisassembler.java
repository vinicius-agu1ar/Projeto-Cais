package br.com.compass.cais.services.assembler;


import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShipInputDisassembler {

    private final ModelMapper modelMapper;

    public Ship toDomainObject(ShipRequestDTO shipRequestDTO) {

        return modelMapper.map(shipRequestDTO, Ship.class);
    }
}
