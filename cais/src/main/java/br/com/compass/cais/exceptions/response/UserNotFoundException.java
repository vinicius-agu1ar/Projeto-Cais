package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.USER_NOT_FOUND;
        this.codeErro = CodeErro.USUARIO_NAO_ENCONTRADO;
        this.details = ErrorCode.USER_NOT_FOUND.getMessage();
    }
}
