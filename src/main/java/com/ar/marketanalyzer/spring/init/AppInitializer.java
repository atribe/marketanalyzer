package com.ar.marketanalyzer.spring.init;

import java.util.HashSet;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ar.marketanalyzer.spring.init.PropCache;
import com.ar.marketanalyzer.threads.marketAnalyzerListener;

public class AppInitializer implements WebApplicationInitializer {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(AppInitializer.class.getName());
	private static final String CONFIG_LOCATION = "com.atomrockets.marketanalyzer.spring.config";
	private static final String MAPPING_URL = "/";
    
    private static final String SPRING_PROPERTIES_FILE_NAME = "spring.properties";
    private static final String ACTIVE_PROFILE_PROPERTY_NAME = "spring.profiles.active";
    private static final String DEFAULT_PROFILE = "dev";
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	log.trace("0.0 onStartup method is starting");
    	
    	WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.addListener(new marketAnalyzerListener());
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping(MAPPING_URL);
        
        //This block removes the jsessionid tag from URLs
        HashSet<SessionTrackingMode> set = new HashSet<SessionTrackingMode>();
        set.add(SessionTrackingMode.COOKIE);
        servletContext.setSessionTrackingModes(set);
        
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        
        Properties prop = PropCache.load(SPRING_PROPERTIES_FILE_NAME);
        context.getEnvironment().setActiveProfiles(prop.getProperty(ACTIVE_PROFILE_PROPERTY_NAME, DEFAULT_PROFILE));
        return context;
    }

}
