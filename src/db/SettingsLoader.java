/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Jovana
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    Properties properties;

    private SettingsLoader() throws IOException {
        LoadSettings();
    }

    public static SettingsLoader getInstance() throws IOException {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    private void LoadSettings() throws FileNotFoundException, IOException {
        properties = new Properties();
        FileInputStream input = new FileInputStream("SettingsLoader.properties");
        properties.load(input);
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }
}
