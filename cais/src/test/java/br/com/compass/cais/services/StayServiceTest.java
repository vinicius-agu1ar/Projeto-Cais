package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.enums.Status;
import br.com.compass.cais.exceptions.response.*;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    private ShipService shipService;

    @Test
    void shouldUpdateStay_success() {
        StayRequestDTO request = new StayRequestDTO();
        StayResponseDTO response = new StayResponseDTO();
        Stay stay = new Stay();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(stay));
        Mockito.when(repository.save(any())).thenReturn(stay);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        StayResponseDTO stayResponseDTO = service.update(ID, request);
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
    void shouldDeleteExit_error() {
        Assertions.assertThrows(StayNotFoundException.class, () -> service.fetchOrFail(ID));
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

    @Test
    void shouldFindByStayNotFoundExceptionError() {

        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(StayNotFoundException.class, () -> service.findBy(ID));
    }

    @Test
    void shouldUpdateStayNotFoundExceptionError() {

        StayRequestDTO request = new StayRequestDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(StayNotFoundException.class, () -> service.update(ID, request));
    }

    @Test
    void shouldBindStayShip_success() {
        Ship ship = new Ship();
        ship.setPier(new Pier());
        ship.setCompany(new Company());
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now());

        Mockito.when(shipService.fetchOrFail(any())).thenReturn(ship);
        service.bind(ID);
        assertEquals(ship.getClass(), stay.getShip().getClass());
    }

    @Test
    void shouldBindStayShip_fail() {
        Ship ship = new Ship();
        ship.setPier(null);
        ship.setCompany(null);
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now());

        Mockito.when(shipService.fetchOrFail(any())).thenReturn(ship);
        Assertions.assertThrows(ShipNotCompatibleException.class, () -> service.bind(ID));
    }

    @Test
    void shouldCalculate_success() {
        Ship ship = new Ship();
        ship.setWeight(1000.0);
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now());
        stay.setExitShip(LocalDateTime.now().plusHours(1));

        Assertions.assertEquals(BigDecimal.valueOf(300.0), service.calculate(stay).setScale(1));
    }

    @Test
    void shouldExit_success() {
        Ship ship = new Ship();
        ship.setWeight(1000.0);
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now().minusHours(2));
        StayResponseDTO response = new StayResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(stay));
        Mockito.when(assembler.toModel(stay)).thenReturn(response);

        StayResponseDTO pierResponseDTO = service.exit(ID);

        Assertions.assertEquals(response.getExitShip(), pierResponseDTO.getExitShip());
    }

    @Test
    void shouldExit_fail() {
        Stay stay = new Stay();
        stay.setExitShip(LocalDateTime.now().minusHours(1));

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(stay));
        Assertions.assertThrows(StayCloseException.class, () -> service.exit(ID));
    }

    @Test
    void shouldFindShipStays() {
        List<Stay> shipStay = repository.findByShipId(ID);

        Mockito.when(repository.findByShipId(any())).thenReturn(shipStay);
        Assertions.assertEquals(shipStay, service.shipStays(ID));
    }

    @Test
    void shouldVerifyStayResponseDTO_withStatus(){
    Page<Stay> staysPage = new PageImpl<>(List.of(new Stay()));
    List<StayResponseDTO> stayResponseDTOS = Arrays.asList(new StayResponseDTO());

        Mockito.when(repository.findByStatus(any(), any())).thenReturn(staysPage);
        Mockito.when(assembler.toCollectionModel(staysPage.getContent())).thenReturn(stayResponseDTOS);

    List<StayResponseDTO> all = service.verifyStayResponseDTO(pageable, Status.CLOSE);

        Assertions.assertEquals(all, stayResponseDTOS);
    }

    @Test
    void shouldVerifyStayResponseDTO_withoutStatus() {
        Page<Stay> staysPage = new PageImpl<>(List.of(new Stay()));
        List<StayResponseDTO> stayResponseDTOS = Arrays.asList(new StayResponseDTO());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(staysPage);
        Mockito.when(assembler.toCollectionModel(staysPage.getContent())).thenReturn(stayResponseDTOS);

        List<StayResponseDTO> all = service.verifyStayResponseDTO(pageable, null);

        Assertions.assertEquals(all, stayResponseDTOS);
    }
}