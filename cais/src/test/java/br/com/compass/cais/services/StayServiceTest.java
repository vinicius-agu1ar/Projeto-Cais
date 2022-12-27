package br.com.compass.cais.services;

import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StayServiceTest {

    public static final Long ID = 1L;

    @Mock
    private StayRepository repository;
    @InjectMocks
    private StayService service;
    @Mock
    private StayDTOAssembler assembler;
    @Mock
    private StayInputDisassembler disassembler;
    @Mock
    private Pageable pageable;

    @Test
    void shouldUpdateStay_success() {
        StayRequestDTO request = new StayRequestDTO();
        StayResponseDTO response = new StayResponseDTO();
        Stay stay = new Stay();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(stay));
        Mockito.when(repository.save(any())).thenReturn(stay);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        StayResponseDTO stayResponseDTO = service.update(ID,request);
        assertEquals(response, stayResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldFindAllStays_success() {
        Page<Stay> staysPage = new PageImpl<>(List.of(new Stay()));
        List<StayResponseDTO> stayResponseDTOS = Arrays.asList(new StayResponseDTO());
        PageImpl<StayResponseDTO> stayResponseDTOPage = new PageImpl<>(stayResponseDTOS, pageable, staysPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(staysPage);
        Mockito.when(assembler.toCollectionModel(staysPage.getContent())).thenReturn(stayResponseDTOS);

        Page<StayResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(stayResponseDTOPage, all);
    }

    @Test
    void shouldFindStayById_success() {
        Stay stay = new Stay();
        StayResponseDTO response = new StayResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(stay));
        Mockito.when(assembler.toModel(stay)).thenReturn(response);

        StayResponseDTO pierResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getClass(), pierResponseDTO.getClass());
        Assertions.assertEquals(response, pierResponseDTO);
    }
}
