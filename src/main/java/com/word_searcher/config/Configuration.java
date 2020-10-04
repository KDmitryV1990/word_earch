package com.word_searcher.config;

import com.sun.javafx.fxml.PropertyNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static String url;

    static {
        url = loadUrl();
    }

    private static String loadUrl(){
        Properties property = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("configuration.properties");
            property.load(fis);

            return property.getProperty("url");

        } catch (IOException e) {
            throw new PropertyNotFoundException("File 'configuration.properties' not found");
        }
    }
}
