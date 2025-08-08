package com.magneto.automation.utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        try {
            // ✅ Loads file from classpath (no hardcoded path!)
            InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("❌ config.properties not found in classpath");
            }

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
