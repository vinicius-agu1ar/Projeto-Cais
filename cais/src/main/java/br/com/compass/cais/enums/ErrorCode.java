package br.com.compass.cais.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    COMPANY_NOT_FOUND("Company not found, check before making this request again"),
    PIER_NOT_FOUND("Pier not found, check before making this request again | Cais não encontrado, verifique antes de fazer esta solicitação novamente"),
    SHIP_NOT_FOUND("Ship not found, check before making this request again"),
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occurred."),
    ENTITY_IS_IN_USE("Entity is in use, please check before performing this action again"),
    COMPANY_IS_IN_USE ("Company cannot be removed as it is in use"),
    COMPANY_ALREADY_LINKED("A company is already linked to this ship, please unlink first"),
    PIER_IS_IN_USE ("Pier is in use, please check before performing this action again"),
    PIER_FULL("Pier is full, try to change the pier");

    private final String message;
}

