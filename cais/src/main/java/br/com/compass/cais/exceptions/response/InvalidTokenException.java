package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class InvalidTokenException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public InvalidTokenException() {
        super(ErrorCode.TOKEN_INVALID.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.TOKEN_INVALID;
        this.codeErro = CodeErro.TOKEN_INVALIDO;
        this.details = ErrorCode.TOKEN_INVALID.getMessage();
    }
}
