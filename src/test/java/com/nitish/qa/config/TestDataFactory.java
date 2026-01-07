package com.nitish.qa.config;

import com.nitish.qa.constants.TestDataKeys;
import com.nitish.qa.io.PropertyReader;

import java.util.Properties;

public final class TestDataFactory {

    private static volatile Properties data;

    private TestDataFactory() {}

    public static Properties get() {
        if (data == null) {
            synchronized (TestDataFactory.class) {
                if (data == null) {
                    data = PropertyReader.load(showPath());
                }
            }
        }
        return data;
    }

    private static String showPath() {
        Environment env = EnvironmentResolver.get();
        return "testdata/data-" + env.name().toLowerCase() + ".properties";
    }

    public static String getPlaylistId() {
        return get().getProperty(TestDataKeys.GET_PLAYLIST_ID);
    }

    public static String getUpdatePlaylistId() {
        return get().getProperty(TestDataKeys.UPDATE_PLAYLIST_ID);
    }
}
