package br.com.compass.cais.repository;

import br.com.compass.cais.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
