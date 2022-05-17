package com.tictactoe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyReader {
    public static Properties readPropertiesFile(String fileName) {
        Properties prop = null;
        File file = new File("resources/tictactoe.properties");
        try (InputStream inputStream = new FileInputStream(file)){
            prop = new Properties();
            prop.load(inputStream);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return prop;
    }
}
