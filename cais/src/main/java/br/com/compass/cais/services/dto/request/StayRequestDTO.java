package br.com.compass.cais.services.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StayRequestDTO {

    @NotNull
    private ShipResumeRequestStay ship;

    private BigDecimal finalPrice;
}
