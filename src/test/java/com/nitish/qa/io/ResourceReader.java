package com.nitish.qa.io;

import java.io.IOException;
import java.io.InputStream;

public final class ResourceReader {

    private ResourceReader() {}

    public static InputStream read(String path) {
        InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);

        if (is == null) {
            throw new RuntimeException("Resource not found: " + path);
        }
        return is;
    }

    public static String readAsString(String path) {
        try (InputStream is = read(path)) {
            return new String(is.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read: " + path, e);
        }
    }
}
