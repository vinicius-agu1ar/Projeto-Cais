package br.com.compass.cais.services.assembler;


import br.com.compass.cais.entites.Ship;

import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;

import br.com.compass.cais.services.dto.response.ship.ShipResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ShipDTOAssembler {

    private final ModelMapper modelMapper;

    public ShipResponseDTO toModel(Ship ship){
        return modelMapper.map(ship, ShipResponseDTO.class);
    }

    public ShipResumeResponseDTO toModelResume(Ship ship){
        return modelMapper.map(ship, ShipResumeResponseDTO.class);
    }

    public List<ShipResponseDTO> toCollectionModel(List<Ship> ships){
        return ships.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<ShipResumeResponseDTO> toCollectionModelResume(List<Ship> ships){
        return ships.stream()
                .map(this::toModelResume)
                .collect(Collectors.toList());
    }
}
