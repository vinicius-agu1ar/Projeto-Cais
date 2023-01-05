package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResumeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ShipAssemblerTest {

    @InjectMocks
    private ShipDTOAssembler assembler;
    @Spy
    private ModelMapper mapper;

    @Test
    void toModel(){
        Ship ship = newShip();
        ShipResponseDTO shipResponseDTO = assembler.toModel(ship);
        Assertions.assertEquals(ship.getWeight(), shipResponseDTO.getWeight());
    }

    @Test
    void toModelResume(){
        Ship ship = newShip();
        ShipResumeResponseDTO shipResumeResponseDTO = assembler.toModelResume(ship);
        Assertions.assertEquals(ship.getWeight(), shipResumeResponseDTO.getWeight());
    }

    @Test
    void toCollectionModel(){
        List<Ship> ships = List.of(newShip());
        List<ShipResponseDTO> shipResponseDTOS = assembler.toCollectionModel(ships);
        Assertions.assertEquals(ships.get(0).getWeight(), shipResponseDTOS.get(0).getWeight());
    }

    @Test
    void toCollectionModelResume(){
        List<Ship> ships = List.of(newShip());
        List<ShipResumeResponseDTO> shipResumeResponseDTOS = assembler.toCollectionModelResume(ships);
        Assertions.assertEquals(ships.get(0).getWeight(), shipResumeResponseDTOS.get(0).getWeight());
    }

    private static Ship newShip(){
        Ship ship = new Ship();
        ship.setName("name");
        ship.setWeight(1000.0);
        return ship;

    }
}
