package br.com.compass.cais.services.dto.response.stay;

import br.com.compass.cais.enums.Status;
import br.com.compass.cais.services.dto.response.ship.ShipResumeStayDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class StayResponseDTO {

    private Long id;

    private ShipResumeStayDTO ship;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime entry;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime exitShip;

    private BigDecimal finalPrice;

    private Status status;

}
