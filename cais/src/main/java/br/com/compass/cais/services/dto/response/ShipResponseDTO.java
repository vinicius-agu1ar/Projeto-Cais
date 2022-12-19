package br.com.compass.cais.services.dto.response;

import br.com.compass.cais.entites.Company;
import br.com.compass.cais.entites.Pier;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipResponseDTO {
    private String name;

    private Double weight;

    private Company companyId;

    private Pier pierId;
}
