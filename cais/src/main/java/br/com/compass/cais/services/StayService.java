package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.enums.Status;
import br.com.compass.cais.exceptions.response.ShipNotCompatibleException;
import br.com.compass.cais.exceptions.response.ShipOpenInStayException;
import br.com.compass.cais.exceptions.response.StayCloseException;
import br.com.compass.cais.exceptions.response.StayNotFoundException;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import br.com.compass.cais.services.dto.response.stay.StayResumeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;
    private final StayDTOAssembler assembler;
    private final StayInputDisassembler disassembler;
    private final ShipService shipService;

    @Transactional
    public Stay create(Stay stay) {
        log.info("Chamando método create (Salvando no banco) - Service Stay");
        return repository.save(stay);
    }

    public BigDecimal calculate(Stay stay){
        log.info("Chamando método calculate - Service Stay");

        BigDecimal valueDaily = new BigDecimal("200");
        BigDecimal valueWeight = new BigDecimal("0.1");

        long timeDocked = stay.getEntry().until(stay.getExitShip(), ChronoUnit.DAYS);

        BigDecimal dailyResult = valueDaily.multiply(new BigDecimal(timeDocked + 1L));

        BigDecimal weightResult = valueWeight.multiply(BigDecimal.valueOf(stay.getShip().getWeight()));

        return dailyResult.add(weightResult).setScale(2, RoundingMode.HALF_UP);
    }

    public Page<StayResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Stay");
        Page<Stay> pageStay = repository.findAll(pageable);
        List<StayResponseDTO> stayResponseDTOS = assembler.toCollectionModel(pageStay.getContent());
        return new PageImpl<>(stayResponseDTOS, pageable, pageStay.getTotalElements());
    }

    public StayResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Stay");
        Stay stay = fetchOrFail(id);
        return assembler.toModel(stay);
    }

    public Stay fetchOrFail(Long id){
        log.info("Chamando método fetchOrFail - Service Stay");
        return repository.findById(id).orElseThrow(StayNotFoundException::new);
    }

    @Transactional
    public StayResponseDTO bind(Long id){
        log.info("Chamando método bind - Service Stay");
        Ship ship = shipService.fetchOrFail(id);

        verifyExistsStayWithShipWithStatusOpen(id, ship);
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now());
        stay.setStatus(Status.OPEN);
        Stay newStay = create(stay);
        return assembler.toModel(newStay);
    }

    @Transactional
    public StayResponseDTO update(Long id, StayRequestDTO request) {
        log.info("Chamando método update - Service Stay");
        Stay stay = fetchOrFail(id);
        disassembler.copyToDomainObject(request,stay);
        stay = create(stay);
        return assembler.toModel(stay);
    }

    @Transactional
    public StayResponseDTO exit(Long id) {
        log.info("Chamando método exit - Service Stay");
        Stay stay = fetchOrFail(id);
        if(stay.getExitShip() != null){
            throw new StayCloseException();
        }
        stay.setExitShip(LocalDateTime.now());
        stay.setFinalPrice(calculate(stay));
        stay.setStatus(Status.CLOSE);
        return assembler.toModel(stay);
    }

    private void verifyExistsStayWithShipWithStatusOpen(Long id, Ship ship) {
        log.info("Chamando método verifyExistsStayWithShipWithStatusOpen - Service Stay");
        List<Stay> byShipIdAndStatus = repository.findByShipIdAndStatus(id, Status.OPEN);
        if(!byShipIdAndStatus.isEmpty()){
            throw new ShipOpenInStayException();
        }
        if(ship.getCompany() == null || ship.getPier() == null){
            throw new ShipNotCompatibleException();
        }
    }

    public List<StayResumeResponseDTO> shipStays(Long id) {
        log.info("Chamando método shipStays - Service Stay");
        List<Stay> shipStay = repository.findByShipId(id);
        return assembler.toCollectionResumeModel(shipStay);
    }

    public List<StayResponseDTO> verifyStayResponseDTO (Pageable pageable, Status status){
        log.info("");
        if (status == null){
            return findAll(pageable).toList();
        }else {
            List<Stay> stays = repository.findByStatus(pageable, status).getContent();
            return assembler.toCollectionModel(stays);
        }
    }
}
