package br.com.compass.cais.services;

import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.exceptions.ShipNotFoundException;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.services.assembler.ShipDTOAssembler;
import br.com.compass.cais.services.assembler.ShipInputDisassembler;
import br.com.compass.cais.services.dto.request.ShipRequestDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public Page<ShipResponseDTO> findAll(Pageable pageable) {

        Page<Ship> pageShip = repository.findAll(pageable);
        List<ShipResponseDTO> shipResponseDTOS = assembler.toCollectionModel(pageShip.getContent());
        return new PageImpl<>(shipResponseDTOS, pageable, pageShip.getTotalElements());
    }

    public ShipResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Ship");
        Ship ship = fetchOrFail(id);
        return assembler.toModel(ship);
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

    public Ship fetchOrFail(Long shipId){
        log.info("Chamando método fetchOrFail - Service Ship");
        return repository.findById(shipId).orElseThrow(ShipNotFoundException::new);
    }
}
