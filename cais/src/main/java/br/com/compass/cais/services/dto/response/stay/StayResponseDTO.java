package br.com.compass.cais.services.dto.response.stay;

import br.com.compass.cais.services.dto.response.ship.ShipResumeStayDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class StayResponseDTO {

    private Long id;

    private ShipResumeStayDTO ship;

    private LocalDateTime entry;

    private LocalDateTime exit;

    private BigDecimal value;

}
