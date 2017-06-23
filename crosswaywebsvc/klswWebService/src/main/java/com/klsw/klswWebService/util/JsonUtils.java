package com.klsw.klswWebService.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    private static Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    /**
     * 对象序列化成字符串
     *
     * @param obj 对象
     * @return 序列化
     */
    public static String encode(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json      jsonString
     * @param valueType 对象类型
     * @return 对象
     */
    public static <T> T decode(String json, Class<T> valueType) {
        return gson.fromJson(json, valueType);
    }

}