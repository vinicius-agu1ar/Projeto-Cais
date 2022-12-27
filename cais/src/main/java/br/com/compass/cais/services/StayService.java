package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.exceptions.response.StayNotFoundException;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.assembler.StayDTOAssembler;
import br.com.compass.cais.services.assembler.StayInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.request.StayRequestDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;

    private final StayDTOAssembler assembler;
    private final StayInputDisassembler disassembler;

    @Transactional
    public Stay create(Stay stay) {
        return repository.save(stay);
    }

    public BigDecimal calculate(Stay stay){

        String DAILY = "200";
        String PERCENT = "0.1";

        BigDecimal valueDaily = new BigDecimal(DAILY);
        BigDecimal valueWeight = new BigDecimal(PERCENT);

        long timeDocked = stay.getEntry().until(stay.getExit(), ChronoUnit.DAYS);

        BigDecimal dailyResult = valueDaily.multiply(new BigDecimal(timeDocked));

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

    public Stay fetchOrFail(Long stayId){
        log.info("Chamando método fetchOrFail - Service Stay");
        return repository.findById(stayId).orElseThrow(StayNotFoundException::new);
    }

    @Transactional
    public StayResponseDTO update(Long id, StayRequestDTO request) {
        log.info("Chamando método update - Service Stay");
        Stay stay = fetchOrFail(id);
        disassembler.copyToDomainObject(request,stay);
        stay = create(stay);
        return assembler.toModel(stay);
    }


}
