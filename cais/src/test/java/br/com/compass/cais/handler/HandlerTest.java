package br.com.compass.cais.handler;

import br.com.compass.cais.exceptions.handlers.RestExceptionHandler;
import br.com.compass.cais.exceptions.response.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HandlerTest {

    @InjectMocks
    private RestExceptionHandler handler;

    @Test
    void pierFullExceptionTest() {
        PierFullException ex = new PierFullException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handlePierFullException(ex).getStatusCode());
    }

    @Test
    void pierNotFoundExceptionTest() {
        PierNotFoundException ex = new PierNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handlePierNotFoundException(ex).getStatusCode());
    }

    @Test
    void companyAlreadySelectedExceptionTest() {
        CompanyAlreadySelectedException ex = new CompanyAlreadySelectedException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleCompanyAlreadySelectedException(ex).getStatusCode());
    }


    @Test
    void companyNotFoundExceptionTest() {
        CompanyNotFoundException ex = new CompanyNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleCompanyNotFoundException(ex).getStatusCode());
    }

    @Test
    void shipOpenInStayExceptionTest() {
        ShipOpenInStayException ex = new ShipOpenInStayException();
        assertEquals(HttpStatus.CONFLICT, handler.handleShipOpenInStayException(ex).getStatusCode());
    }

    @Test
    void shipNotFoundExceptionTest() {
        ShipNotFoundException ex = new ShipNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleShipNotFoundException(ex).getStatusCode());
    }

    @Test
    void shipNotCompatibleExceptionTest() {
        ShipNotCompatibleException ex = new ShipNotCompatibleException();
        assertEquals(HttpStatus.BAD_REQUEST, handler.handleEntityNotCompatibleException(ex).getStatusCode());
    }

    @Test
    void entityInUseExceptionTest() {
        EntityInUseException ex = new EntityInUseException();
        assertEquals(HttpStatus.CONFLICT, handler.handleEntityInUseException(ex).getStatusCode());
    }

    @Test
    void stayNotFoundExceptionTest() {
        StayNotFoundException ex = new StayNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleStayNotFoundException(ex).getStatusCode());
    }

    @Test
    void stayCloseExceptionTest() {
        StayCloseException ex = new StayCloseException();
        assertEquals(HttpStatus.CONFLICT, handler.handleStayCloseException(ex).getStatusCode());
    }

    @Test
    void userNotFoundExceptionTest() {
        UserNotFoundException ex = new UserNotFoundException();
        assertEquals(HttpStatus.NOT_FOUND, handler.handleUserNotFoundException(ex).getStatusCode());
    }

    @Test
    void tokenExpiredOrInvalidExceptionTest() {
        TokenExpiredOrInvalidException ex = new TokenExpiredOrInvalidException();
        assertEquals(HttpStatus.FORBIDDEN, handler.handleTokenExpiredOrInvalidException(ex).getStatusCode());
    }
}
