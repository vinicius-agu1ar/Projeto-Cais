package br.com.compass.cais.controller;

import br.com.compass.cais.services.ProfileService;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
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
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> create(@RequestBody @Valid ProfileRequestDTO request) {
        log.info("Criando um novo Profile...");
        ProfileResponseDTO response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable("id") Long id){
        log.info("Excluindo uma Profile por Id...");
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> findAll(@RequestParam(required = false, name = "name") String name, @PageableDefault(size = 10) Pageable pagination) {
        log.info("Listando Profile com página de {} registros...", pagination.getPageSize());
        List<ProfileResponseDTO> responsePage = service.verifyProfileResponseDTO(pagination,name);
        return ResponseEntity.status(HttpStatus.OK).body(responsePage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> findBy(@PathVariable("id") Long id){
        log.info("Buscando Profile por id...");
        ProfileResponseDTO profileResponseDTO = service.findBy(id);
        return ResponseEntity.status(HttpStatus.OK).body(profileResponseDTO);
    }
}
