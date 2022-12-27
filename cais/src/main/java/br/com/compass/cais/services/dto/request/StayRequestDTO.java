package br.com.compass.cais.services.dto.request;

import br.com.compass.cais.entites.Ship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StayRequestDTO {


    private Ship ship;

    private LocalDateTime entry;

    private LocalDateTime exit;

    private BigDecimal value;


}
