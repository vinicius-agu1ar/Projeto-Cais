package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.Stay;
import br.com.compass.cais.repository.StayRepository;
import br.com.compass.cais.services.dto.response.stay.StayResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StayService {

    private final StayRepository repository;

    //    Criação do service - Adicionar um Stay
    @Transactional
    public Stay create(Stay stay) {
        return repository.save(stay);
    }

}
