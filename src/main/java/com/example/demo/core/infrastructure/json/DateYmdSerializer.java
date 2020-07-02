package com.example.demo.core.infrastructure.json;


import com.example.demo.core.util.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Used to serialize Java.util.Date, which is not a common JSON type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner http://loianegroner.com (English) http://loiane.com (Portuguese)
 */
@Component
public class DateYmdSerializer extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        gen.writeString(DateUtil.toYmdDashString(date));
    }
}
