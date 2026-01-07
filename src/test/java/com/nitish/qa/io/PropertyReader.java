package com.nitish.qa.io;

import com.nitish.qa.constants.ErrorMessages;

import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    private PropertyReader() {}

    public static Properties load(String path) {
        try (InputStream is = ResourceReader.read(path)) {
            Properties props = new Properties();
            props.load(is);
            return props;
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessages.UNABLE_TO_LOAD_PROPERTIES + path, e);
        }
    }
}

