package br.com.compass.cais.services.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShipResumeRequestStay {

    @NotNull
    private Double weight;
}
