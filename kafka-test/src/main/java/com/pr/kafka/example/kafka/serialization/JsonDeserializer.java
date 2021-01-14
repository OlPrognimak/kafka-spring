package com.pr.kafka.example.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Oleksandr Prognimak
 * @created 13.01.2021 - 11:06
 */
public class JsonDeserializer<T> implements Deserializer<T> {
    private static final Logger logger = Logger.getLogger(JsonDeserializer.class.toString());
    private ObjectMapper objectMapper = new ObjectMapper();
    protected Class<T> deserializedClass;

    public JsonDeserializer(Class<T> deserializedClass) {
        this.deserializedClass = deserializedClass;
    }

    public JsonDeserializer() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure(Map<String, ?> map, boolean b) {
        logger.info("Deserialize Configure Map: "+map+" : B="+b);
        logger.info(" Desereilize class :"+deserializedClass);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        if(bytes == null){
            return null;
        }

        try {
            logger.info(" Deserialize class :"+deserializedClass);
            return objectMapper.readValue(bytes, deserializedClass);
        }catch (Exception ex){
            throw new RuntimeException("can not deserialize :"+(new String(bytes)), ex);
        }
    }

    @Override
    public void close() {

    }
}
