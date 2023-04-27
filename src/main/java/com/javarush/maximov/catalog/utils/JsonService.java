package com.javarush.maximov.catalog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class JsonService {
    private JsonService() {
    }

    public static class JsonProcessingException extends RuntimeException {
        public JsonProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static <T> List<T> jsonToList(String jsonFileName, Class<T[]> type) {
        try {
            log.info("Reading JSON file and converting to list: {}", jsonFileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Path path = Paths.get(ClassLoader.getSystemResource(jsonFileName).getFile());
            String content = Files.readString(path);
            return List.of(mapper.readValue(content, type));
        } catch (IOException e) {
            log.error("Error while reading JSON file and converting to list: {}", jsonFileName, e);
            throw new JsonProcessingException("Error processing JSON file: " + jsonFileName, e);
        }
    }
}

