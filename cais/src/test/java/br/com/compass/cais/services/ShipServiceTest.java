package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.exceptions.ShipNotFoundException;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
    void shouldDeleteShip_errorShipNotFoundException() {
        service.delete(ID);
        verify(repository).deleteById(any());

        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(any());
        Assertions.assertThrows(ShipNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldDeleteShip_errorEntityInUseException() {
        service.delete(ID);
        verify(repository).deleteById(any());

        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
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
    @Test
    void findBy_sucess(){
        Ship ship = new Ship();
        ShipResponseDTO response = new ShipResponseDTO();
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(ship));
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        ShipResponseDTO shipResponseDTO = service.findBy(ID);
        assertEquals(response, shipResponseDTO);
    }
}
