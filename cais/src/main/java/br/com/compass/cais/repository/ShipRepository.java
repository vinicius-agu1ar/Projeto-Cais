package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    List<Ship> findByCompanyId(Long companyId);

    List<Ship> findByPierId(Long pierId);
}
