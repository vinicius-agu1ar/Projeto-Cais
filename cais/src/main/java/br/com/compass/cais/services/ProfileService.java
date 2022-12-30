package br.com.compass.cais.services;


import br.com.compass.cais.entites.Profile;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.ProfileNotFoundException;
import br.com.compass.cais.repository.ProfileRepository;
import br.com.compass.cais.services.assembler.ProfileDTOAssembler;
import br.com.compass.cais.services.assembler.ProfileInputDisassembler;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
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
    public Profile create(Profile profile) {
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
        try {
            repository.deleteById(profileId);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new ProfileNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }

    public Page<ProfileResponseDTO> findAll(Pageable pageable) {
        log.info("Chamando método findAll - Service Profile");
        Page<Profile> pageProfile = repository.findAll(pageable);
        List<ProfileResponseDTO> profileResponseDTOS = assembler.toCollectionModel(pageProfile.getContent());
        return new PageImpl<>(profileResponseDTOS, pageable, pageProfile.getTotalElements());
    }

    public List<ProfileResponseDTO> verifyProfileResponseDTO(Pageable pageable, String name) {
        log.info("Chamando método verifyCompanyResponseDTO - Service Company");
        if (name != null) {
            List<ProfileResponseDTO> list = new ArrayList<>();
            ProfileResponseDTO byName = findByName(name);
            list.add(byName);
            return list;
        } else {
            List<Profile> profiles = repository.findAll(pageable).getContent();
            return assembler.toCollectionModel(profiles);
        }
    }

    public ProfileResponseDTO findByName(String name) {
        log.info("Chamando método findByName - Service Profile");
        Profile profile = fetchOrFail(name);
        return assembler.toModel(profile);
    }

    private Profile fetchOrFail(String profileName) {
        log.info("Chamando método fetchOrFail - Service profile");
        return Optional.ofNullable(repository.findByName(profileName)).orElseThrow(ProfileNotFoundException::new);
    }

    private Profile fetchOrFail(Long profileId) {
        log.info("Chamando método fetchOrFail - Service Profile");
        return repository.findById(profileId).orElseThrow(ProfileNotFoundException::new);
    }

    public ProfileResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service Profile");
        Profile profile = fetchOrFail(id);
        return assembler.toModel(profile);
    }
}
