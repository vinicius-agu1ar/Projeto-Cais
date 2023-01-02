package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class GenerateTokenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;


    public GenerateTokenException() {
        super(ErrorCode.GENERATE_TOKEN_ERROR.name());
        this.httpStatus = HttpStatus.CONFLICT;
        this.errorCode = ErrorCode.GENERATE_TOKEN_ERROR;
        this.codeErro = CodeErro.ERROR_AO_GERAR_TOKEN;
        this.details = ErrorCode.GENERATE_TOKEN_ERROR.getMessage();
    }
}
