package br.com.compass.cais.exceptions.handlers;

import java.util.ArrayList;
import java.util.List;

import br.com.compass.cais.enums.CodeErro;
import br.com.compass.cais.enums.ErrorCode;
import br.com.compass.cais.exceptions.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INVALID_PARAMETER, CodeErro.INVALIDO_PARAMETRO, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, CodeErro.PEDIDO_RUIM, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<String> errors = new ArrayList<>();
        fieldErrors.forEach(error ->
                errors.add(String.format("%s : %s", error.getField(), error.getDefaultMessage()))
        );

        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, CodeErro.PEDIDO_RUIM, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, CodeErro.PEDIDO_RUIM, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.BAD_REQUEST, CodeErro.PEDIDO_RUIM, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public final ResponseEntity<Object> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.COMPANY_NOT_FOUND, CodeErro.EMPRESA_NAO_ENCONTRADA, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(PierNotFoundException.class)
    public final ResponseEntity<Object> handlePierNotFoundException(PierNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.PIER_NOT_FOUND, CodeErro.CAIS_NAO_ENCONTRADO, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(ShipNotFoundException.class)
    public final ResponseEntity<Object> handleShipNotFoundException(ShipNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.SHIP_NOT_FOUND, CodeErro.NAVIO_NAO_ENCONTRADO, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(EntityInUseException.class)
    public final ResponseEntity<Object> handleEntityInUseException(Exception ex){
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.ENTITY_IS_IN_USE, CodeErro.ENTIDADE_EM_USO, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }
    @ExceptionHandler(PierFullException.class)
    public final ResponseEntity<Object> handlePierFullException(PierFullException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.PIER_FULL, CodeErro.CAIS_CHEIO, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(CompanyAlreadySelectedException.class)
    public final ResponseEntity<Object> handleCompanyAlreadySelectedException(CompanyAlreadySelectedException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.COMPANY_ALREADY_LINKED, CodeErro.EMPRESA_JA_LIGADA, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(ShipNotCompatibleException.class)
    public final ResponseEntity<Object> handleEntityNotCompatibleException(ShipNotCompatibleException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.SHIP_NOT_COMPATIBLE, CodeErro.NAVIO_NAO_COMPATIVEL, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
    @ExceptionHandler(ShipOpenInStayException.class)
    public final ResponseEntity<Object> handleShipOpenInStayException(ShipOpenInStayException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.SHIP_OPEN_IN_STAY, CodeErro.NAVIO_ABERTO_EM_ESTADIA, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(StayNotFoundException.class)
    public final ResponseEntity<Object> handleStayNotFoundException(StayNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.STAY_NOT_FOUND, CodeErro.ESTADIA_NAO_ENCONTRADA, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(StayCloseException.class)
    public final ResponseEntity<Object> handleStayCloseException(StayCloseException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.STAY_IS_ALREADY_CLOSED, CodeErro.ESTADIA_JA_ESTA_FECHADA, ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.error(ex.getMessage(), ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.INTERNAL_SERVER_ERROR, CodeErro.ERRO_INTERNO_SERVIDOR, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        log.error(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCode.USER_NOT_FOUND, CodeErro.USUARIO_NAO_ENCONTRADO, ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
