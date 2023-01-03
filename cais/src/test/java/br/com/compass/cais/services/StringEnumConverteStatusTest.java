package br.com.compass.cais.services;

import br.com.compass.cais.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class StringEnumConverteStatusTest {

    @Test
    void shouldConvert_success() {
        String statusE = "close";
        StringEnumConverteStatus status = new StringEnumConverteStatus();
        Status convert = status.convert(statusE);
        Assertions.assertEquals(Status.CLOSE,convert);
    }
}
