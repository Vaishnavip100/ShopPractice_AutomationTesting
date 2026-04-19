package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties prop;

    public ConfigReader() {
        prop=new Properties();

        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (is==null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            prop.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(prop.getProperty("timeout"));
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }
}