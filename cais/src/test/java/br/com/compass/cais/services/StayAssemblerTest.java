package br.com.compass.cais.services;

import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import br.com.compass.cais.services.dto.response.stay.StayResumeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StayAssemblerTest {

    @InjectMocks
    private StayDTOAssembler assembler;
    @Spy
    private ModelMapper mapper;


    @Test
    void toModel(){
        Stay stay = newStay();
        StayResponseDTO stayResponseDTO = assembler.toModel(stay);
        Assertions.assertEquals(stay.getFinalPrice(), stayResponseDTO.getFinalPrice());
    }

    @Test
    void toResumeModel(){
        Stay stay = new Stay();
        StayResumeResponseDTO stayResumeResponseDTO = assembler.toResumeModel(stay);
        Assertions.assertEquals(stay.getFinalPrice(), stayResumeResponseDTO.getFinalPrice());
    }

    @Test
    void toColletionModel(){
        List<Stay> stays = List.of(newStay());
        List<StayResponseDTO> stayResponseDTOS = assembler.toCollectionModel(stays);
        Assertions.assertEquals(stays.get(0).getFinalPrice(), stayResponseDTOS.get(0).getFinalPrice());
    }

    @Test
    void toCollectionResumeModel(){
        List<Stay> stays = List.of(newStay());
        List<StayResumeResponseDTO> stayResumeResponseDTOS = assembler.toCollectionResumeModel(stays);
        Assertions.assertEquals(stays.get(0).getFinalPrice(), stayResumeResponseDTOS.get(0).getFinalPrice());
    }
    private static Stay newStay(){
        Stay stay = new Stay();
        stay.setFinalPrice(BigDecimal.valueOf(100.0));
        return stay;
    }
}
