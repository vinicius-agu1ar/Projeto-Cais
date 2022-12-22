package br.com.compass.cais.exceptions;

import br.com.compass.cais.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class CompanyAlreadySelectedException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public CompanyAlreadySelectedException(){
        super(ErrorCode.COMPANY_ALREADY_LINKED.name());
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errorCode = ErrorCode.COMPANY_ALREADY_LINKED;
        this.details = ErrorCode.COMPANY_ALREADY_LINKED.getMessage();
    }
}
