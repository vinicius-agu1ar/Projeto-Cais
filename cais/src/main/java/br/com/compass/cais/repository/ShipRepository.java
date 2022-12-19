package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.entites.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {

    Page<Ship> findByCompany(Company company, Pageable pagination);

    Page<Ship> findByPier(Pier pier, Pageable pagination);
}
