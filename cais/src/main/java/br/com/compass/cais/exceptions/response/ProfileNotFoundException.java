package br.com.compass.cais.exceptions.response;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
@Getter
public class ProfileNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final CodeErro codeErro;
    private final HttpStatus httpStatus;

    public ProfileNotFoundException(){
        super(ErrorCode.PROFILE_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.PROFILE_NOT_FOUND;
        this.codeErro = CodeErro.PERFIL_NAO_ENCONTRADO;
        this.details = ErrorCode.PROFILE_NOT_FOUND.getMessage();
    }
}
