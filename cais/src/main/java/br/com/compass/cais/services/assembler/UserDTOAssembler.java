package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.User;
import br.com.compass.cais.services.dto.response.user.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDTOAssembler {

    private final ModelMapper modelMapper;

    public UserResponseDTO toModel(User user){
        return modelMapper.map(user,UserResponseDTO.class);
    }

    public List<UserResponseDTO> toCollectionModel(List<User> users){
        return users.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
