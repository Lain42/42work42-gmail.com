package com.seleniumtest.qa.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum ApplicationProperties {
    INSTANCE;

    private Properties properties;

    ApplicationProperties() {

        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getLogin() {
        return properties.getProperty("login");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getSearchword() {
        return properties.getProperty("search_word");
    }

    public String getChromeDriver() {
        return properties.getProperty("chrome_driver");
    }

    public String getReferenceEmail() {
        return properties.getProperty("test_email");
    }

    public String getCurrentTestEmailFolder() {
        return properties.getProperty("current_test_email_folder");
    }
}
