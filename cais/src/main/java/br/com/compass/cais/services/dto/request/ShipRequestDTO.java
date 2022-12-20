package br.com.compass.cais.services.dto.request;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
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
    private Company companyId;

    @NotNull
    private Pier pierId;

}
