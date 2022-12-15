package br.com.compass.cais.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    COMPANY_NOT_FOUND("Company not found"),
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    COMPANY_IS_IN_USE ("Company cannot be removed as it is in use");

    private final String message;
}

