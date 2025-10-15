package utils;   // 👈 make sure this matches your folder path

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            properties = new Properties();
            // Path to your config.properties (adjust if needed)
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config.properties file!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
