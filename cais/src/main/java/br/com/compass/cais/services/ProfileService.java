package br.com.compass.cais.services;


import br.com.compass.cais.entites.Profile;
import br.com.compass.cais.exceptions.response.CompanyNotFoundException;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.repository.ProfileRepository;
import br.com.compass.cais.services.assembler.ProfileDTOAssembler;
import br.com.compass.cais.services.assembler.ProfileInputDisassembler;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileDTOAssembler assembler;

    private final ProfileInputDisassembler disassembler;

    private final ProfileRepository repository;

    @Transactional
    public ProfileResponseDTO create(ProfileRequestDTO request) {
        log.info("Chamando método create - Service Profile");
        Profile profile = disassembler.toDomainObject(request);
        profile = create(profile);
        return assembler.toModel(profile);
    }

    @Transactional
    public Profile create(Profile profile){
        log.info("Chamando método create (salvando no repository) - Service Profile");
        try {
            return repository.save(profile);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }

    }

    @Transactional
    public void delete(Long profileId) {
        log.info("Chamando método delete (excluindo no repository) - Service Profile");
        try{
            repository.deleteById(profileId);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new CompanyNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

}
