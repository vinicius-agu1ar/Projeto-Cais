package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ShipNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public ShipNotFoundException(){
        super(ErrorCode.SHIP_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.SHIP_NOT_FOUND;
        this.codeErro = CodeErro.NAVIO_NAO_ENCONTRADO;
        this.details = ErrorCode.SHIP_NOT_FOUND.getMessage();
    }
}
