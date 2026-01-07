package com.nitish.qa.config;

public enum Environment {
    DEV,
    QA,
    PROD;

    public static Environment from(String value) {
        if (value == null || value.isBlank()) {
            return QA; // default
        }
        return Environment.valueOf(value.toUpperCase());
    }
}
