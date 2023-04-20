package com.javarush.maximov.catalog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonService {
    private JsonService() {
    }

    public static <T> List<T> jsonToList(String jsonFileName, Class<T[]> type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Path path = Paths.get(ClassLoader.getSystemResource(jsonFileName).getFile());
            String content = Files.readString(path);
            return List.of(mapper.readValue(content, type));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

