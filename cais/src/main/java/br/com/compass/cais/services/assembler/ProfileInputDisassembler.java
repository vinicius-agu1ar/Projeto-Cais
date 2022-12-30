package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Profile;
import br.com.compass.cais.services.dto.request.ProfileRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileInputDisassembler {

    private final ModelMapper modelMapper;

    public Profile toDomainObject(ProfileRequestDTO profileRequestDTO) {
        return modelMapper.map(profileRequestDTO, Profile.class);
    }

    public void copyToDomainObject(ProfileRequestDTO profileRequestDTO, Profile profile){
        modelMapper.map(profileRequestDTO, profile);
    }
}
