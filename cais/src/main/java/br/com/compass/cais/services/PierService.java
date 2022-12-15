package br.com.compass.cais.services;

import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.exceptions.EntityInUseException;
import br.com.compass.cais.exceptions.PierNotFoundException;
import br.com.compass.cais.repository.PierRepository;
import br.com.compass.cais.services.assembler.PierDTOAssembler;
import br.com.compass.cais.services.assembler.PierInputDisassembler;
import br.com.compass.cais.services.dto.request.PierRequestDTO;
import br.com.compass.cais.services.dto.response.PierResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PierService {

    private final PierRepository repository;

    private final PierDTOAssembler assembler;

    private final PierInputDisassembler disassembler;

    public Page<PierResponseDTO> findAll(Pageable pageable) {
        Page<Pier> pagePier = repository.findAll(pageable);
        List<PierResponseDTO> pierResponseDTOS = assembler.toCollectionModel(pagePier.getContent());
        return new PageImpl<>(pierResponseDTOS, pageable, pagePier.getTotalElements());
    }

    public PierResponseDTO findBy(Long id) {
        Pier pier = fetchOrFail(id);
        return assembler.toModel(pier);
    }

    @Transactional
    public PierResponseDTO create(PierRequestDTO request) {
        Pier pier = disassembler.toDomainObject(request);
        pier = create(pier);
        return assembler.toModel(pier);
    }

    @Transactional
    public PierResponseDTO update(Long id, PierRequestDTO request) {
        Pier pier = fetchOrFail(id);
        disassembler.copyToDomainObject(request,pier);
        pier = create(pier);
        return assembler.toModel(pier);
    }

    @Transactional
    public void delete(Long pierId){
        try{
            repository.deleteById(pierId);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new PierNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

    public Pier create(Pier pier){
        return repository.save(pier);
    }

    private Pier fetchOrFail(Long pierId){
        return repository.findById(pierId).orElseThrow(PierNotFoundException::new);
    }

}
