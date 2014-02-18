package com.atomrockets.marketanalyzer.spring.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.atomrockets.marketanalyzer.something.testMain;
import com.atomrockets.marketanalyzer.spring.controller.AccountController;

public class AppInitializer implements WebApplicationInitializer {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(AccountController.class.getName());
	private static final String CONFIG_LOCATION = "com.atomrockets.marketanalyzer.spring.config";
	
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	log.info("__--+=onStartup method is starting=+--__");
    	
    	WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        testMain.pizza();
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

}
