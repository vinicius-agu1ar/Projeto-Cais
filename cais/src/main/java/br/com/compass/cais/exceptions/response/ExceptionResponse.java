package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private final String code;

    private final String message;

    private final List<String> details;

    public ExceptionResponse(ErrorCode errorCode, Throwable ex) {
        this(errorCode, ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
    }

    public ExceptionResponse(ErrorCode errorCode, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCode errorCode, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.details = details;
    }
}
