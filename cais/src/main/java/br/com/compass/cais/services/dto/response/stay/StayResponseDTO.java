package br.com.compass.cais.services.dto.response.stay;

import br.com.compass.cais.services.dto.response.ship.ShipResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class StayResponseDTO {

    private ShipResponseDTO ship;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime entry;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime exit;

    private BigDecimal value;

}
