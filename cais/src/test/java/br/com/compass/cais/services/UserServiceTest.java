package br.com.compass.cais.services;

import br.com.compass.cais.entites.User;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.ProfileNotFoundException;
import br.com.compass.cais.repository.UserRepository;
import br.com.compass.cais.services.assembler.UserDTOAssembler;
import br.com.compass.cais.services.assembler.UserInputDisassembler;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.*;

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
    private Pageable pageable;

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
    void shouldDeleteUser_success() {
        service.delete(ID);
        verify(repository).deleteById(any());
    }
    @Test
    void shouldDeleteUser_errorDataIntegrityViolationException() {
        doThrow(new DataIntegrityViolationException("test")).when(repository).deleteById(any());
        Assertions.assertThrows(EntityInUseException.class, () -> service.delete(ID));
    }


    @Test
    void shouldFindUserById_success() {
        User user = new User();
        UserResponseDTO response = new UserResponseDTO();

        Mockito.when(repository.findById(any())).thenReturn(Optional.of(user));
        Mockito.when(assembler.toModel(user)).thenReturn(response);

        UserResponseDTO userResponseDTO = service.findBy(ID);

        Assertions.assertEquals(response.getId(), userResponseDTO.getId());
        Assertions.assertEquals(response, userResponseDTO);
    }

    @Test
    void shouldFindAllUser_success() {
        Page<User> usersPage = new PageImpl<>(List.of(new User()));
        List<UserResponseDTO> userResponseDTOS = Arrays.asList(new UserResponseDTO());
        PageImpl<UserResponseDTO> userResponseDTOPage = new PageImpl<>(userResponseDTOS, pageable, usersPage.getTotalElements());

        Mockito.when(repository.findAll(any(Pageable.class))).thenReturn(usersPage);
        Mockito.when(assembler.toCollectionModel(usersPage.getContent())).thenReturn(userResponseDTOS);

        Page<UserResponseDTO> all = service.findAll(pageable);

        Assertions.assertEquals(userResponseDTOPage, all);
    }

}
