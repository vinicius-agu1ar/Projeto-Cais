package br.com.compass.cais.repository;

import br.com.compass.cais.entites.Pier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PierRepository extends JpaRepository<Pier, Long> {
    Pier findByName(String name);
}
