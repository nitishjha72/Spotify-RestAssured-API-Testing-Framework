package com.nitish.qa.config;

public final class EnvironmentResolver {

    private EnvironmentResolver() {}

    private static final Environment CURRENT_ENV;

    static {
        String env =
                System.getProperty("env");     // highest priority

        if (env == null) {
            env = System.getenv("ENV");        // fallback
        }

        CURRENT_ENV = Environment.from(env);
    }

    public static Environment get() {
        return CURRENT_ENV;
    }
}
