package edu.nu.cs.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyUtil {
    private static Properties properties;

    //add property file names in the list
    private static List<String> propertiesFiles = new ArrayList<String>() {{
        add("application.properties");
        add("environment.properties");
    }};

    private void loadProperties() throws IOException {
        InputStream inputStream = null;

        for(String fileName: propertiesFiles) {
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + fileName + "' not found in the classpath");
            }
        }
    }

    public static String getProperty(String key){
        if(properties==null){
            synchronized (PropertyUtil.class){
                if(properties==null){
                    properties = new Properties();

                    try {
                        new PropertyUtil().loadProperties();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        String prop = properties.getProperty(key);

        return prop !=null ? prop.trim() : prop;
    }
}