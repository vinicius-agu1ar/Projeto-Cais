package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository <Company, Long>{
    Page<Company> findByName(String name, Pageable pagination);

}