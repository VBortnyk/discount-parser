package com.parser.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperty {
    private static final Properties properties;

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream("app.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load application properties");
        }
    }

    public static String get(String property) {
        return properties.getProperty(property);
    }
}
