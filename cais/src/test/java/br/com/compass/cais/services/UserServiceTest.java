package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Ship;
import br.com.compass.cais.entites.User;
import br.com.compass.cais.exceptions.response.CompanyNotFoundException;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.UserNotFoundException;
import br.com.compass.cais.repository.CompanyRepository;
import br.com.compass.cais.repository.ShipRepository;
import br.com.compass.cais.repository.UserRepository;
import br.com.compass.cais.services.assembler.*;
import br.com.compass.cais.services.dto.request.CompanyRequestDTO;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import br.com.compass.cais.services.dto.response.ship.ShipResumeResponseDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    static final Long ID = 1L;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserDTOAssembler assembler;

    @Mock
    private UserInputDisassembler disassembler;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private Pageable pageable;

    @Test
    void shouldFindUserById_success() {
        User user = new User();
        UserResponseDTO response = new UserResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(assembler.toModel(user)).thenReturn(response);

        UserResponseDTO userResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getEmail(), userResponseDTO.getEmail());
    }

    @Test
    void shouldFindAllCompanies_success() {
        Page<User> usersPage = new PageImpl<>(List.of(new User()));
        List<UserResponseDTO> userResponseDTOs = Arrays.asList(new UserResponseDTO());
        PageImpl<UserResponseDTO> userResponseDTOPage = new PageImpl<>(userResponseDTOs, pageable, usersPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(usersPage);
        Mockito.when(assembler.toCollectionModel(usersPage.getContent())).thenReturn(userResponseDTOs);

        Page<UserResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(userResponseDTOPage, all);
    }


    @Test
    void shouldCreateUser_success() {
        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO response = new UserResponseDTO();
        User user = new User();

        Mockito.when(disassembler.toDomainObject(any())).thenReturn(user);
        Mockito.when(repository.save(any())).thenReturn(user);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        UserResponseDTO userResponseDTO = service.create(request);
        assertEquals(response, userResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldCreateUser_error() {
        User user = new User();

        doThrow(new DataIntegrityViolationException("test")).when(repository).save(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.create(user));
    }

    @Test
    void shouldDeleteUser_error() {
        doThrow(new EmptyResultDataAccessException(21)).when(repository).deleteById(any());
        Assertions.assertThrows(UserNotFoundException.class, () -> service.delete(ID));
    }

    @Test
    void shouldDeleteUser_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }


    @Test
    void shouldUpdateUser_success() {
        User user = new User();
        UserRequestDTO request = new UserRequestDTO();
        UserResponseDTO response = new UserResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(repository.save(any())).thenReturn(user);
        Mockito.when(assembler.toModel(any())).thenReturn(response);

        UserResponseDTO userResponseDTO = service.update(ID, request);
        assertEquals(response, userResponseDTO);
        verify(repository).save(any());
    }

    @Test
    void shouldDeleteUser_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }
}
