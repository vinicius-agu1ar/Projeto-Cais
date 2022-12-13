package br.com.compass.cais.services.dto.response;

import br.com.compass.cais.enums.Origin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO {

    private Long id;

    private String cnpj;

    private String name;

    private Origin origin;
}
