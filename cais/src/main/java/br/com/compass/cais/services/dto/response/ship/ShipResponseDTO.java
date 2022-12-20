package br.com.compass.cais.services.dto.response.ship;

import br.com.compass.cais.services.dto.response.company.CompanyResponseNameOriginDTO;
import br.com.compass.cais.services.dto.response.pier.PierResponseName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipResponseDTO {

    private Long id;

    private String name;

    private Double weight;

    private CompanyResponseNameOriginDTO company;

    private PierResponseName pier;
}
