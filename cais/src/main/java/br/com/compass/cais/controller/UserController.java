package br.com.compass.cais.controller;

import br.com.compass.cais.services.UserService;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO request) {
        log.info("Criando um novo User...");
        UserResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(@PageableDefault(size = 10) Pageable pagination){
        log.info("Listando Ships com página de {} registros...", pagination.getPageSize());
        List<UserResponseDTO> responsePage = service.findAll(pagination).getContent();
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando User por id...");
        UserResponseDTO userResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long id, @RequestBody @Valid UserRequestDTO request){
        log.info("Atualizando User por id...");
        UserResponseDTO userResponseDTO = service.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo um User por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
