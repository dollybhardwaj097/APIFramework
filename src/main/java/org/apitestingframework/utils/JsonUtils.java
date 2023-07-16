package org.apitestingframework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeBase;
import io.restassured.common.mapper.TypeRef;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static Map<String,Object > getJSONDataAsMap(String JSONfileName) throws IOException {

       String CompleteJsonFileName= System.getProperty("user.dir")+"/src/test/resources/" + JSONfileName;
        Map<String,Object > data =objectMapper.readValue(new File(CompleteJsonFileName), new TypeReference<>(){});
        return data;
    }
}
