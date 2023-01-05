package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.User;
import br.com.compass.cais.services.dto.request.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInputDisassembler {

    private final ModelMapper modelMapper;

        public User toDomainObject(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }

    public void copyToDomainObject(UserRequestDTO userRequestDTO, User user){
        modelMapper.map(userRequestDTO, user);
    }
}
