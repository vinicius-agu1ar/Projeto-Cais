package br.com.compass.cais.services.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.compass.cais.enums.Origin;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDTO {

    @NotBlank
    private String cnpj;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Origin Origin;


}
