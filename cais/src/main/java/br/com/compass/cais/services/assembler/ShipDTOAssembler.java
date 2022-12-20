package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.services.dto.response.ShipResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShipDTOAssembler {
    //assembler

    private final ModelMapper modelMapper;

    public ShipResponseDTO toModel(Ship ship){
        return modelMapper.map(ship, ShipResponseDTO.class );
    }

    public List<ShipResponseDTO> toCollectionModel(List<Ship> ships){
        return ships.stream().map(this::toModel).collect(Collectors.toList());
    }
}
