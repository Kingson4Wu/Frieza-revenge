package com.kxw.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Created by kingsonwu on 17/6/23.
 * https://stackoverflow.com/questions/26827343/jackson-serialize-boolean-to-1-0-instead-of-true-false
 */
public class NumericBooleanSerializer extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean b, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws
        IOException {
        jsonGenerator.writeNumber(b ? 1 : 0);
    }
}