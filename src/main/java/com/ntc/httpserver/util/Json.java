package com.ntc.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {

    private static ObjectMapper myObjectMapper =defaultObjectMapper();
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static JsonNode parse(String jsonsrc) throws IOException {
        return myObjectMapper.readTree(jsonsrc);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    public  static JsonNode toJson(Object obj) throws JsonProcessingException {
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node,false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node,true);
    }

    private static String generateJson(Object obj,boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();

        if(pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }


        return objectWriter.writeValueAsString(obj);
    }


}
