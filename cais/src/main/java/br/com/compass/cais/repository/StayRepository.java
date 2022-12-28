package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {
    Stay findByEntry (LocalDateTime entry);
    Stay findByExitShip (LocalDateTime exitShip);
    List<Stay> findByShipIdAndStatus (Long shipId, Status status);
}
