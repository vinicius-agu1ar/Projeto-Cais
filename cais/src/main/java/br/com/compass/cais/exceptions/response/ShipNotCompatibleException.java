package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ShipNotCompatibleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;


    public ShipNotCompatibleException() {
        super(ErrorCode.SHIP_NOT_COMPATIBLE.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.SHIP_NOT_COMPATIBLE;
        this.codeErro = CodeErro.NAVIO_NAO_COMPATIVEL;
        this.details = ErrorCode.SHIP_NOT_COMPATIBLE.getMessage();
    }
}
