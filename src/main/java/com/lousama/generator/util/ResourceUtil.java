package com.lousama.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * read config.properties
 */
public class ResourceUtil {
    private static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);
    private static Properties prop;
    private static InputStream inputStream;

    static {
        try {
            inputStream = ResourceUtil.class.getResourceAsStream("/config.properties");
            prop = new Properties();
            prop.load(inputStream);
        } catch (Exception e) {
            logger.error("error:[load config.properties error]-",e);
        }
    }
    
    public static String getString(final String propertyName) {
        return prop.getProperty(propertyName);
    }

    public static int getInt(final String propertyName) {
        return Integer.valueOf(getString(propertyName));
    }

    public static boolean getBoolean(final String propertyName) {
        return Boolean.valueOf(getString(propertyName));
    }

}
