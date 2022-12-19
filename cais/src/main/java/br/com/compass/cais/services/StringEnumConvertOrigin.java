package br.com.compass.cais.services;

import br.com.compass.cais.enums.Origin;
import org.springframework.core.convert.converter.Converter;

public class StringEnumConvertOrigin implements Converter<String, Origin>{

    @Override
    public Origin convert(String source) {
        return Origin.valueOf(source.toUpperCase());
    }
}
