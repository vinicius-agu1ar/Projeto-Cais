package br.com.compass.cais.services;

import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.PierFullException;
import br.com.compass.cais.exceptions.response.PierNotFoundException;
import br.com.compass.cais.repository.PierRepository;
import br.com.compass.cais.services.assembler.PierDTOAssembler;
import br.com.compass.cais.services.assembler.PierInputDisassembler;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PierService {

    private final PierRepository repository;

    private final ShipService shipService;

    private final PierDTOAssembler assembler;

    private final PierInputDisassembler disassembler;

    public Page<PierResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Pier");
        Page<Pier> pagePier = repository.findAll(pageable);
        List<PierResponseDTO> pierResponseDTOS = assembler.toCollectionModel(pagePier.getContent());
        return new PageImpl<>(pierResponseDTOS, pageable, pagePier.getTotalElements());
    }

    public PierResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Pier");
        Pier pier = fetchOrFail(id);
        return assembler.toModel(pier);
    }

    public PierResponseDTO findByName(String name) {
        log.info("Chamando método findByName - Service Pier");
        Pier pier = fetchOrFail(name);
        return assembler.toModel(pier);
    }

    @Transactional
    public void bind(Long id, Long shipId) {
        log.info("Chamando método bind - Service Pier");
        Pier pier = fetchOrFail(id);

        if (pier.getSpots() < 1) {
            throw new PierFullException();
        }

        Ship ship = shipService.fetchOrFail(shipId);
        ship.setPier(pier);
        int subtract = pier.getSpots() - 1;
        pier.setSpots(subtract);
    }

    @Transactional
    public void unlink(Long id) {
        log.info("Chamando método unlink - Service Pier");
        Ship ship = shipService.fetchOrFail(id);
        ship.setPier(null);
    }

    @Transactional
    public PierResponseDTO create(PierRequestDTO request) {
        log.info("Chamando método create - Service Pier");
        Pier pier = disassembler.toDomainObject(request);
        pier = create(pier);
        return assembler.toModel(pier);
    }

    @Transactional
    public PierResponseDTO update(Long id, PierRequestDTO request) {
        log.info("Chamando método update - Service Pier");
        Pier pier = fetchOrFail(id);
        disassembler.copyToDomainObject(request, pier);
        pier = create(pier);
        return assembler.toModel(pier);
    }

    @Transactional
    public void delete(Long pierId) {
        log.info("Chamando método delete - Service Pier");
        try {
            repository.deleteById(pierId);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new PierNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

    public Pier create(Pier pier) {
        log.info("Chamando método create (salvando no repository) - Service Pier");
        try {
            return repository.save(pier);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }
    }


    private Pier fetchOrFail(Long pierId) {
        log.info("Chamando método fetchOrFail - Service Pier");
        return repository.findById(pierId).orElseThrow(PierNotFoundException::new);
    }

        private Pier fetchOrFail(String pierName) {
        log.info("Chamando método fetchOrFail - Service Pier");
        return Optional.ofNullable(repository.findByName(pierName)).orElseThrow(PierNotFoundException::new);
        }

    public List<PierResponseDTO> verifyPierResponseDTO(Pageable pageable, String name) {
        log.info("Chamando método verifyCompanyResponseDTO - Service Company");
        if(name != null){
            List<PierResponseDTO> list = new ArrayList<>();
            PierResponseDTO byName = findByName(name);
            list.add(byName);
            return list;
        }else {
            List<Pier> piers = repository.findAll(pageable).getContent();
            return assembler.toCollectionModel(piers);
        }
    }
}
