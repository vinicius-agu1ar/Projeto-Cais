package br.com.compass.cais.services.dto.request;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import br.com.compass.cais.services.dto.response.company.CompanyResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private Double weight;

    @NotNull
    private CompanyResponseDTO company;

    @NotNull
    private PierResponseDTO pier;

}
