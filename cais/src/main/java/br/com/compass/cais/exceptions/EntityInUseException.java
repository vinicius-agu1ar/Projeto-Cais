package br.com.compass.cais.exceptions;

import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class EntityInUseException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;


    public EntityInUseException() {
        super(ErrorCode.ENTITY_IS_IN_USE.name());
        this.httpStatus = HttpStatus.CONFLICT;
        this.errorCode = ErrorCode.ENTITY_IS_IN_USE;
        this.details = ErrorCode.ENTITY_IS_IN_USE.getMessage();
    }
}
