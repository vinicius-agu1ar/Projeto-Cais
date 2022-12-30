package br.com.compass.cais.services;

import br.com.compass.cais.entites.Profile;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.ProfileNotFoundException;
import br.com.compass.cais.repository.ProfileRepository;
import br.com.compass.cais.services.assembler.ProfileDTOAssembler;
import br.com.compass.cais.services.assembler.ProfileInputDisassembler;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTest {

    static final Long ID = 1L;
    @InjectMocks
    private ProfileService service;

    @Mock
    private ProfileRepository repository;

    @Mock
    private ProfileDTOAssembler assembler;

    @Mock
    private ProfileInputDisassembler disassembler;

    @Mock
    private Pageable pageable;


    @Test
    void shouldCreateProfile_success() {
        ProfileRequestDTO request = new ProfileRequestDTO();
        ProfileResponseDTO response = new ProfileResponseDTO();
        Profile profile = new Profile();

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(profile);
        Mockito.when(repository.save(any())).thenReturn(profile);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        ProfileResponseDTO profileResponseDTO = service.create(request);
        assertEquals(response, profileResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldCreateProfile_error() {
        Profile profile = new Profile();

        doThrow(new DataIntegrityViolationException("test")).when(repository).save(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.create(profile));
    }

    @Test
    void shouldDeleteProfile_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }

    @Test
    void shouldDeleteProfile_error() {
        doThrow(new EmptyResultDataAccessException(21)).when(repository).deleteById(any());
        Assertions.assertThrows(ProfileNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldDeleteProfile_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }


    @Test
    void shouldFindProfileById_success() {
        Profile profile = new Profile();
        ProfileResponseDTO response = new ProfileResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(profile));
        Mockito.when(assembler.toModel(profile)).thenReturn(response);

        ProfileResponseDTO profileResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getName(), profileResponseDTO.getName());
        Assertions.assertEquals(response, profileResponseDTO);
    }

    @Test
    void shouldFindAllProfiles_success() {
        Page<Profile> profilesPage = new PageImpl<>(List.of(new Profile()));
        List<ProfileResponseDTO> profileResponseDTOS = Arrays.asList(new ProfileResponseDTO());
        PageImpl<ProfileResponseDTO> profileResponseDTOPage = new PageImpl<>(profileResponseDTOS, pageable, profilesPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(profilesPage);
        Mockito.when(assembler.toCollectionModel(profilesPage.getContent())).thenReturn(profileResponseDTOS);

        Page<ProfileResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(profileResponseDTOPage, all);
    }

    @Test
    void shouldFindProfileByName_success() {
        Profile profile = new Profile();
        ProfileResponseDTO response = new ProfileResponseDTO();

        Mockito.when(repository.findByName(any())).thenReturn(profile);
        Mockito.when(assembler.toModel(profile)).thenReturn(response);

        ProfileResponseDTO profileResponseDTO = service.findByName(any());

        Assertions.assertEquals(response.getName(), profileResponseDTO.getName());
        Assertions.assertEquals(response, profileResponseDTO);
    }
}
