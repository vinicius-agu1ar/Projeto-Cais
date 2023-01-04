package br.com.compass.cais.services;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.User;
import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.services.assembler.CompanyDTOAssembler;
import br.com.compass.cais.services.assembler.UserDTOAssembler;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserAssemblerTest {

    @InjectMocks
    private UserDTOAssembler service;

    @Spy
    private ModelMapper mapper;

    @Test
    void toModelSucess(){
        User user = getUser();
        UserResponseDTO userResponseDTO = service.toModel(user);
        Assertions.assertEquals(user.getEmail(), userResponseDTO.getEmail());
    }

    @Test
    void toColletionModelSucess(){
        List<User> user = List.of(getUser());
        List<UserResponseDTO> userResponseDTO = service.toCollectionModel(user);
        Assertions.assertEquals(user.get(0).getEmail(), userResponseDTO.get(0).getEmail());
    }


    private static User getUser() {
        User user = new User();
        user.setId(1L);
        user.setPassword("aaa");
        user.setEmail("sdsds");
        return user;
    }
}
