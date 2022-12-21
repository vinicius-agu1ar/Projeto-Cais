package br.com.compass.cais.exceptions;

import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class PierFullException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;


    public PierFullException() {
        super(ErrorCode.PIER_FULL.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.PIER_FULL;
        this.details = ErrorCode.PIER_FULL.getMessage();
    }
}
