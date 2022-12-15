package br.com.compass.cais.services.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PierRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private Integer spots;
}
