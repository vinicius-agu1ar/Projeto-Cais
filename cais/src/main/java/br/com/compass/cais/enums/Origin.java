package br.com.compass.cais.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Origin {
    @JsonProperty("National")
    NATIONAL,
    @JsonProperty("International")
    INTERNATIONAL;
}
