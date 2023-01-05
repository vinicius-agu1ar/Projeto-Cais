package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.services.assembler.PierDTOAssembler;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PierAssemblerTest {

    @InjectMocks
    private PierDTOAssembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        Pier pier = getPier();
        PierResponseDTO pierResponseDTO = service.toModel(pier);
        Assertions.assertEquals(pier.getName(), pierResponseDTO.getName());
    }

    @Test
    void toColletionModelSucess(){
        List<Pier> pier = List.of(getPier());
        List<PierResponseDTO> pierResponseDTOS = service.toCollectionModel(pier);
        Assertions.assertEquals(pier.get(0).getName(), pierResponseDTOS.get(0).getName());
    }


    private static Pier getPier() {
        Pier pier = new Pier();
        pier.setName("a");
        pier.setId(1L);
        pier.setSpots(10);
        return pier;
    }
}
