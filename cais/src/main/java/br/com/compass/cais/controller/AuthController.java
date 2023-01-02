package br.com.compass.cais.controller;

import br.com.compass.cais.config.security.service.TokenService;
import br.com.compass.cais.entites.User;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import br.com.compass.cais.services.dto.response.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class AuthController {

    private final AuthenticationManager manager;

    private final TokenService service;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserRequestDTO request){
        log.info("Chamando o endpoint login - AuthController");
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication authenticate = manager.authenticate(authenticationToken);
        String tokenJWT = service.generateToken((User) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(tokenJWT));
    }
}
