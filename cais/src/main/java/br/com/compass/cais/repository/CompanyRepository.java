package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.enums.Origin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository <Company, Long>{
    Page<Company> findByOrigin(Origin origin, Pageable pagination);

    List<Company> findByNameStartingWithIgnoreCase(String name);

}