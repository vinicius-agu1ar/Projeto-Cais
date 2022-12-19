package br.com.compass.cais.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class TestUtils {

    public static ObjectWriter mapper;

    static {
        mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    public static String mapToJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

}
