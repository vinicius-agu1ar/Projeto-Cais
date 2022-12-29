package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class StayCloseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;


    public StayCloseException() {
        super(ErrorCode.STAY_IS_ALREADY_CLOSED.name());
        this.httpStatus = HttpStatus.CONFLICT;
        this.errorCode = ErrorCode.STAY_IS_ALREADY_CLOSED;
        this.codeErro = CodeErro.ESTADIA_JA_ESTA_FECHADA;
        this.details = ErrorCode.STAY_IS_ALREADY_CLOSED.getMessage();
    }
}
