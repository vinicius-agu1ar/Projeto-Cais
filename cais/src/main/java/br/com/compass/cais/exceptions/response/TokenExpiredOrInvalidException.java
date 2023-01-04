package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class TokenExpiredOrInvalidException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;

    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public TokenExpiredOrInvalidException(){
        super(ErrorCode.TOKEN_EXPIRED_INVALID.name());
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.errorCode = ErrorCode.TOKEN_EXPIRED_INVALID;
        this.codeErro = CodeErro.TOKEN_INVALIDO_EXPIRADO;
        this.details = ErrorCode.TOKEN_EXPIRED_INVALID.getMessage();
    }
}
