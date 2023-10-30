package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    
    private static Properties config = new Properties();

    static {
        try (FileInputStream file = new FileInputStream("config.properties")) {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error en obtener el archivo properties " +e.getMessage() );
        }
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}
