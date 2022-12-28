package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class ShipOpenInStayException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;


    public ShipOpenInStayException() {
        super(ErrorCode.SHIP_OPEN_IN_STAY.name());
        this.httpStatus = HttpStatus.CONFLICT;
        this.errorCode = ErrorCode.SHIP_OPEN_IN_STAY;
        this.codeErro = CodeErro.NAVIO_ABERTO_EM_ESTADIA;
        this.details = ErrorCode.SHIP_OPEN_IN_STAY.getMessage();
    }
}
