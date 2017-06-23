package com.klsw.crosswaylive.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//import com.fasterxml.jackson.core.JsonParser;

public class JsonUtils {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        /*objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);*/
    }

    private JsonUtils() {
    }

    public static String encode(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("encode(Object)", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param typeReference
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return (T) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            logger.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }

}