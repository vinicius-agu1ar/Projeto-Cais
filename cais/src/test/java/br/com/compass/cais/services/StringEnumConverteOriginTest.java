package br.com.compass.cais.services;

import br.com.compass.cais.enums.Origin;
import br.com.compass.cais.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StringEnumConverteOriginTest {

    @Test
    void shouldConvert_success() {
        String originE = "national";
        StringEnumConvertOrigin origin = new StringEnumConvertOrigin();
        Origin convert = origin.convert(originE);
        Assertions.assertEquals(convert,Origin.NATIONAL);
    }
}
