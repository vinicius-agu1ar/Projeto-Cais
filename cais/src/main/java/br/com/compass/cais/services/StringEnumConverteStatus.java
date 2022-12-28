package br.com.compass.cais.services;

import br.com.compass.cais.enums.Status;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConverteStatus implements Converter<String, Status>{

    @Override
    public Status convert(String source) {
        return Status.valueOf(source.toUpperCase());
    }
}
