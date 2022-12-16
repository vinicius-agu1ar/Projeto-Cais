package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Pier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PierRepository extends JpaRepository<Pier, Long> {
    Pier findByName(String name);
}
