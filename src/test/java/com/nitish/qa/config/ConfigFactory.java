package com.nitish.qa.config;

import com.nitish.qa.io.PropertyReader;

import java.util.Properties;

public final class ConfigFactory {

    private static volatile Config config;

    private ConfigFactory() {}

    public static Config get() {
        if (config == null) {
            synchronized (ConfigFactory.class) {
                if (config == null) {
                    Properties props = PropertyReader.load(resolvePath());
                    config = new Config(props);
                }
            }
        }
        return config;
    }

    private static String resolvePath() {
        Environment env = EnvironmentResolver.get();
        return "config/application-" + env.name().toLowerCase() + ".properties";
    }
}
