package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.response.ShipResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShipServiceTest {
    public static final Long ID = 1L;
    @Mock
    private ShipRepository repository;
    @InjectMocks
    private ShipService service;
    @Mock
    private ShipDTOAssembler assembler;
    @Mock
    private ShipInputDisassembler disassembler;

    @Test
    void shouldDeleteShip_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    @Test
    void shouldCreateShip_success() {
        ShipRequestDTO request = new ShipRequestDTO();
        ShipResponseDTO response = new ShipResponseDTO();
        Ship ship = new Ship();

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(ship);
        Mockito.when(repository.save(any())).thenReturn(ship);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        ShipResponseDTO shipResponseDTO = service.create(request);
        assertEquals(response, shipResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldUpdateShip_success() {
        ShipRequestDTO request = new ShipRequestDTO();
        ShipResponseDTO response = new ShipResponseDTO();
        Ship ship = new Ship();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(ship));
        Mockito.when(repository.save(any())).thenReturn(ship);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        ShipResponseDTO shipResponseDTO = service.update(ID,request);
        assertEquals(response, shipResponseDTO);
        verify(repository).save(any());
    }
}
