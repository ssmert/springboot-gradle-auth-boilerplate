package com.example.demo.core.infrastructure.json;


import com.example.demo.core.util.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Used to deserialize Java.util.Date, which is not a common JSON type, so we have to create a custom serialize method;.
 *
 * @author Loiane Groner http://loianegroner.com (English) http://loiane.com (Portuguese)
 */
@Component
public class DateYmsDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return DateUtil.toDateYms(p.getText());
    }
}
