package com.atomrockets.marketanalyzer.spring.init;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.atomrockets.marketanalyzer.spring.controller.AccountController;

/*
 * From: http://kielczewski.eu/2013/11/setting-active-profile-and-property-sources-in-spring-mvc/
 * 
 * This works like that:
 * 1. Tries to locate property file in current working directory � that way the active profile could be set using a file lying around on the server
 * 2. If failed to found one, tries classpath � uses the file contained in WAR (you can generate spring.properties during the build process for different environments)
 * 3. If failed to found one, loads it from source directory � useful while running the application exploded i.e. via jetty-maven-plugin
 * 4. If failed to found one, panicks and returns empty Properties object
 */
public class PropertiesLoader {
	static Logger log = Logger.getLogger(AccountController.class.getName());

    public Properties load(String fileName) {
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

    private InputStream findFile(String fileName) throws FileNotFoundException {
        InputStream im = findInWorkingDirectory(fileName);
        if (im == null) im = findInClasspath(fileName);
        if (im == null) im = findInSourceDirectory(fileName);
        return im;
    }

    private InputStream findInSourceDirectory(String fileName) throws FileNotFoundException {
        log.debug("Trying "+ fileName +" in source directory");
        return new FileInputStream("src/main/resources/" + fileName);
    }

    private InputStream findInClasspath(String fileName) {
        log.debug("Trying "+ fileName +" on the classpath");
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }

    private InputStream findInWorkingDirectory(String fileName) {
        try {
            log.debug("Trying "+ fileName +" in current directory");
            return new FileInputStream(System.getProperty("user.dir") + fileName);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
