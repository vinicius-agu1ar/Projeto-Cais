package br.com.compass.cais.services;

import br.com.compass.cais.entites.User;
import br.com.compass.cais.exceptions.response.EntityInUseException;
import br.com.compass.cais.exceptions.response.UserNotFoundException;
import br.com.compass.cais.repository.UserRepository;
import br.com.compass.cais.services.assembler.UserDTOAssembler;
import br.com.compass.cais.services.assembler.UserInputDisassembler;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserDTOAssembler assembler;

    private final UserInputDisassembler disassembler;

    private final PasswordEncoder encoder;

    @Transactional
    public UserResponseDTO create(UserRequestDTO request) {
        log.info("Chamando método create - Service User");
        String encode = encoder.encode(request.getPassword());
        request.setPassword(encode);
        User user = disassembler.toDomainObject(request);
        user = create(user);
        return assembler.toModel(user);
    }

    @Transactional
    public User create(User user){
        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new EntityInUseException();
        }
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {

        Page<User> userPage = repository.findAll(pageable);
        List<UserResponseDTO> userResponseDTOS = assembler.toCollectionModel(userPage.getContent());
        return new PageImpl<>(userResponseDTOS, pageable, userPage.getTotalElements());
    }

    public UserResponseDTO findBy(Long id) {
        log.info("Chamando método findBy - Service User");
        User user = fetchOrFail(id);
        return assembler.toModel(user);
    }

    public User fetchOrFail(Long id){
        log.info("Chamando método fetchOrFail - Service User");
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO request) {
        log.info("Chamando método update - Service User");
        User user = fetchOrFail(id);
        String encode = encoder.encode(request.getPassword());
        disassembler.copyToDomainObject(request,user);
        user.setPassword(encode);
        user = create(user);
        return assembler.toModel(user);
    }

    @Transactional
    public void delete(Long id){
        log.info("Chamando método delete - Service User");
        try{
            repository.deleteById(id);
            repository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }catch (DataIntegrityViolationException e) {
            throw new EntityInUseException();
        }
    }
}
