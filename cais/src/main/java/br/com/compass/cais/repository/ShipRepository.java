package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Page<Ship> findByCompanyId(Long companyId, Pageable pagination);

    Page<Ship> findByPierId(Long pierId, Pageable pagination);
}
