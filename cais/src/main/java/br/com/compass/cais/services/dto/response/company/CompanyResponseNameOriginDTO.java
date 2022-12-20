package br.com.compass.cais.services.dto.response.company;

import br.com.compass.cais.enums.Origin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseNameOriginDTO {
    private String name;

    private Origin origin;
}
