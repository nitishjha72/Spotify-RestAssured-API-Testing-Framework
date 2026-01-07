package com.nitish.qa.io;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JsonReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    private JsonReader() {}

    public static String readRaw(String path) {
        return ResourceReader.readAsString(path);
    }

    public static <T> T read(String path, Class<T> clazz) {
        try {
            return mapper.readValue(ResourceReader.read(path), clazz);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse json: " + path, e);
        }
    }
}
