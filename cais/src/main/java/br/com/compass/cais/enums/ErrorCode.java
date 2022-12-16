package br.com.compass.cais.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    COMPANY_NOT_FOUND("Company not found, check before making this request again"),
    PIER_NOT_FOUND("Pier not found, check before making this request again"),
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    COMPANY_IS_IN_USE ("Company cannot be removed as it is in use"),
    PIER_IS_IN_USE ("Pier is in use, please check before performing this action again");

    private final String message;
}

