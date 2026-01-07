package com.nitish.qa.io;

import java.io.InputStream;

public final class SchemaReader {

    private SchemaReader() {}

    public static InputStream read(String path) {
        return ResourceReader.read(path);
    }
}
