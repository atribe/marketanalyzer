package com.atomrockets.marketanalyzer.threads;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * @author Allan
 * This class waits until the context has been initialized (aka the tomcat server has been started,
 * or at least some part of it anyway) and then spawns threads as needed.
 * 
 * Threads spawned:
 *   MarketAnalyzerBean, which handles the database initialization.
 */
@Component
public class marketAnalyzerListener implements ServletContextListener{

	@Autowired
	private MarketAnalyzerBean maBean;
	
	private Thread t;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils
			.getRequiredWebApplicationContext(sce.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
		t = new Thread(maBean, "MA_Database_Init_Thread");
		
		t.start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub	
	}
}
