package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class StayNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public StayNotFoundException(){
        super(ErrorCode.STAY_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.STAY_NOT_FOUND;
        this.codeErro = CodeErro.ESTADIA_NAO_ENCONTRADA;
        this.details = ErrorCode.STAY_NOT_FOUND.getMessage();
    }
}
