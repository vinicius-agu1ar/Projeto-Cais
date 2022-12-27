package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;

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

}
