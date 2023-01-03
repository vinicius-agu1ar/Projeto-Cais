package br.com.compass.cais.services;

import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.exceptions.response.CompanyNotFoundException;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.PierFullException;
import br.com.compass.cais.exceptions.response.PierNotFoundException;
import br.com.compass.cais.repository.PierRepository;
import br.com.compass.cais.services.assembler.PierDTOAssembler;
import br.com.compass.cais.services.assembler.PierInputDisassembler;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PierServiceTest {

    static final Long ID = 1L;
    @InjectMocks
    private PierService service;
    @Mock
    private PierRepository repository;
    @Mock
    private PierDTOAssembler assembler;
    @Mock
    private PierInputDisassembler disassembler;
    @Mock
    private ShipService shipService;
    @Mock
    private Pageable pageable;

    @Test
    void shouldFindPierById_success() {
        Pier pier = new Pier();
        PierResponseDTO response = new PierResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pier));
        Mockito.when(assembler.toModel(pier)).thenReturn(response);

        PierResponseDTO pierResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getName(), pierResponseDTO.getName());
        Assertions.assertEquals(response, pierResponseDTO);
    }

    @Test
    void shouldCreatePier_success() {
        PierRequestDTO request = new PierRequestDTO();
        PierResponseDTO response = new PierResponseDTO();
        Pier pier = new Pier();

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(pier);
        Mockito.when(repository.save(any())).thenReturn(pier);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        PierResponseDTO pierResponseDTO = service.create(request);
        assertEquals(response, pierResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldCreatePier_error() {
        Pier pier = new Pier();

        doThrow(new DataIntegrityViolationException("test")).when(repository).save(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.create(pier));
    }

    @Test
    void shouldUpdatePier_success() {
        Pier pier = new Pier();
        PierRequestDTO request = new PierRequestDTO();
        PierResponseDTO response = new PierResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pier));
        Mockito.when(repository.save(any())).thenReturn(pier);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        PierResponseDTO pierResponseDTO = service.update(ID, request);
        assertEquals(response, pierResponseDTO);
        verify(repository).save(any());

    }

    @Test
    void shouldDeletePier_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    @Test
    void shouldBind_success() {
        Pier pier = new Pier();
        pier.setSpots(3);
        Ship ship = new Ship();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pier));
        Mockito.when(shipService.fetchOrFail(any())).thenReturn(ship);
        service.bind(ID, ID);

        assertEquals(pier.getClass(), ship.getPier().getClass());
    }

    @Test
    void shouldUnlink_success() {
        Ship ship = new Ship();

        Mockito.when(shipService.fetchOrFail(any())).thenReturn(ship);
        service.unlink(ship.getId());

        assertNull(ship.getPier());
    }

    @Test
    void shouldBind_error() {
        Pier pier = new Pier();
        pier.setId(3L);
        pier.setSpots(0);

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(pier));

        Assertions.assertThrows(PierFullException.class, () -> service.bind(pier.getId(), ID));
    }

    @Test
    void shouldFindByPier_PierNotFoundExceptionError() {

        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PierNotFoundException.class, () -> service.findBy(ID));
    }

    @Test
    void shouldDeletePier_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }

    @Test
    void shouldFindAllPiers_success() {
        Page<Pier> piersPage = new PageImpl<>(List.of(new Pier()));
        List<PierResponseDTO> pierResponseDTOs = Arrays.asList(new PierResponseDTO());
        PageImpl<PierResponseDTO> pierResponseDTOPage = new PageImpl<>(pierResponseDTOs, pageable, piersPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(piersPage);
        Mockito.when(assembler.toCollectionModel(piersPage.getContent())).thenReturn(pierResponseDTOs);

        Page<PierResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(pierResponseDTOPage, all);
    }

    @Test
    void shouldFindPierByName_success() {
        Pier pier = new Pier();
        PierResponseDTO response = new PierResponseDTO();

        Mockito.when(repository.findByName(any())).thenReturn(pier);
        Mockito.when(assembler.toModel(pier)).thenReturn(response);

        PierResponseDTO pierResponseDTO = service.findByName(any());

        Assertions.assertEquals(response.getName(), pierResponseDTO.getName());
        Assertions.assertEquals(response, pierResponseDTO);
    }
}
