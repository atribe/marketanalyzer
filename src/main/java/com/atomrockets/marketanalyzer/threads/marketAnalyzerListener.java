package com.atomrockets.marketanalyzer.threads;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
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
	private MarketsDBInitRunnable maBean;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
		
	private static Thread t;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.debug("1.0 Tomcat has booted up");
		WebApplicationContextUtils
			.getRequiredWebApplicationContext(sce.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
		
		log.trace("1.1 Creating the DB init thread");
		String threadName = maBean.getThread_name();
		t = new Thread(maBean, threadName);
		
		log.trace("1.2 Starting the DB init thread");
		t.start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub	
	}
	
	public static boolean dbInitThreadIsAlive() {
		// java.lang.Thread.State can be NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
		System.out.println("State:" + t.getState());
		System.out.println("Is alive?:" + t.isAlive());
		return t.isAlive();
	}
}
