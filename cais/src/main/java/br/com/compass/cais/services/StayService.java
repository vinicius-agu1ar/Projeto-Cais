package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.hibernate.criterion.Example.create;

@Slf4j
@Service
@RequiredArgsConstructor
public class StayService {

    private final ShipService shipService;

    @Transactional
    public void bind(Long id, Long shipId){
        log.info("Chamando método bind - Service Stay");
        Ship ship = shipService.fetchOrFail(shipId);
        Stay stay = new Stay();
        stay.setShip(ship);
        stay.setEntry(LocalDateTime.now());

        create(stay);

    }

}
