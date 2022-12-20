package br.com.compass.cais.services;

import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
