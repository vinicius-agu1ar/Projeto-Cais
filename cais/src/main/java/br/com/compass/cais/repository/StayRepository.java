package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> {

    Stay findByEntry (LocalDateTime entry);

    Stay findByExit (LocalDateTime exit);
}
