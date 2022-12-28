package br.com.compass.cais.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("Open")
    OPEN,
    @JsonProperty("Close")
    CLOSE;
}
