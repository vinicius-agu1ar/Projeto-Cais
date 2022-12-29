package br.com.compass.cais.services.assembler;

import br.com.compass.cais.entites.Profile;
import br.com.compass.cais.services.dto.response.profile.ProfileResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfileDTOAssembler {

    private final ModelMapper modelMapper;
    public ProfileResponseDTO toModel(Profile profile) {
        return modelMapper.map(profile,ProfileResponseDTO.class);
    }

    public List<ProfileResponseDTO> toCollectionModel(List<Profile> profiles){
        return profiles.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
