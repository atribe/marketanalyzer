package com.ar.marketanalyzer.spring.init;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/*
 * From: http://kielczewski.eu/2013/11/setting-active-profile-and-property-sources-in-spring-mvc/
 * 
 * This works like that:
 * 1. Tries to locate property file in current working directory � that way the active profile could be set using a file lying around on the server
 * 2. If failed to found one, tries classpath � uses the file contained in WAR (you can generate spring.properties during the build process for different environments)
 * 3. If failed to found one, loads it from source directory � useful while running the application exploded i.e. via jetty-maven-plugin
 * 4. If failed to found one, panicks and returns empty Properties object
 */
public class PropCache {
	static Logger log = Logger.getLogger(PropCache.class.getName());

	private static Properties cachedProps = null;
	
	/**
	 * @param propertyName
	 * @return propertyValue
	 * 
	 * This function replaces the typical prop.getProperty(String propName).
	 * This function when called the first time loads the property files (multiple)
	 * Then retains that prop file info in memory, acting as a property cache.
	 * This allows for the loading of property files once and never again.
	 */
	public static synchronized String getCachedProps(String propertyName) {
		if (cachedProps == null) {
			cachedProps = new Properties();
			Properties propSet1 = loadActivePropertiesFile();
			Properties propSet2 = load("common.properties");
			cachedProps.putAll(propSet1);
			cachedProps.putAll(propSet2);
		}
		return cachedProps.getProperty(propertyName);
	}
	
    public static Properties load(String fileName) {
        Properties prop = new Properties();
        InputStream im = null;
        try {
            im = findFile(fileName);
            prop.load(im);
        } catch (IOException ignore) {
        } finally {
            if (im != null) {
                try {
                    im.close();
                } catch (IOException ignore) {
                }
            }
        }
        return prop;
    }

    private static InputStream findFile(String fileName) throws FileNotFoundException {
        InputStream im = findInWorkingDirectory(fileName);
        if (im == null) im = findInClasspath(fileName);
        if (im == null) im = findInSourceDirectory(fileName);
        return im;
    }

    private static InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
        log.trace("__--++Trying "+ fileName +" in source directory");
        return new FileInputStream("src/main/resources/" + fileName);
    }

    private static InputStream findInClasspath(String fileName) {
        log.trace("__--++Trying "+ fileName +" on the classpath");
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    private static InputStream findInWorkingDirectory(String fileName) {
        try {
            log.trace("__--++Trying "+ fileName +" in current directory");
            return new FileInputStream(System.getProperty("user.dir") + fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private static Properties loadActivePropertiesFile() {
    	Properties propSpring = new Properties();
    	Properties prop = new Properties();
        InputStream im = null;
        try {
            im = findFile("spring.properties"); //loads the spring.properties file, this is where the active profile is set
            propSpring.load(im);
            
            im = findFile(propSpring.getProperty("spring.profiles.active") + ".properties"); //gets the name of the active profile from the previously loaded property file
            
            prop.load(im);
        } catch (IOException ignore) {
        } finally {
            if (im != null) {
                try {
                    im.close();
                } catch (IOException ignore) {
                }
            }
        }
        return prop;
    }
}
