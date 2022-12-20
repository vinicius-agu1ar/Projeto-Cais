package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.exceptions.ShipNotFoundException;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.response.ShipResponseDTO;
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

    @Transactional
    public ShipResponseDTO create(ShipRequestDTO request) {
        log.info("Chamando método create - Service Ship");
        Ship ship = disassembler.toDomainObject(request);
        ship = create(ship);
        return assembler.toModel(ship);
    }

    @Transactional
    public ShipResponseDTO update(Long id, ShipRequestDTO request) {
        log.info("Chamando método update - Service Ship");
        Ship ship = fetchOrFail(id);
        disassembler.copyToDomainObject(request,ship);
        ship = create(ship);
        return assembler.toModel(ship);
    }

    @Transactional
    public Ship create(Ship ship){
        return repository.save(ship);
    }

    private Ship fetchOrFail(Long shipId){
        log.info("Chamando método fetchOrFail - Service Ship");
        return repository.findById(shipId).orElseThrow(ShipNotFoundException::new);
    }
}
