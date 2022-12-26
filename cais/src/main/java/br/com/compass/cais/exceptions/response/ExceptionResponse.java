package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
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

    private final String menssagem;

    private final List<String> details;


    public ExceptionResponse(ErrorCode errorCode, CodeErro codeErro, Throwable ex) {
        this(errorCode, codeErro, ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
    }

    public ExceptionResponse(ErrorCode errorCode, CodeErro codeErro, String details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.menssagem = codeErro.getMenssagem();
        this.details = Collections.singletonList(details);
    }

    public ExceptionResponse(ErrorCode errorCode, CodeErro codeErro, List<String> details) {
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
        this.menssagem = codeErro.getMenssagem();
        this.details = details;
    }
}
