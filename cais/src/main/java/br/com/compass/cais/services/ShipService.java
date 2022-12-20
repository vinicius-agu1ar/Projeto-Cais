package br.com.compass.cais.services;

import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.exceptions.PierNotFoundException;
import br.com.compass.cais.exceptions.ShipNotFoundException;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipService {

    private final ShipRepository repository;

    private final ShipDTOAssembler assembler;

    private final ShipInputDisassembler disassembler;


    @Transactional
    public void delete(Long shipId){
        log.info("Chamando método delete - Service Ship");
        try{
            repository.deleteById(shipId);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new ShipNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }
}
