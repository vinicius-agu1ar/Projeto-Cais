package br.com.compass.cais.services.dto.request;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private Double weight;

    @NotBlank
    private Company companyId;

    @NotBlank
    private Pier pierId;

}
