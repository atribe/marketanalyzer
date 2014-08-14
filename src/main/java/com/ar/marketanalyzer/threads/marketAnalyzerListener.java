package com.ar.marketanalyzer.threads;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ar.marketanalyzer.spring.init.PropCache;

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

	@Autowired @Qualifier("IndexBacktestInit")
	private Runnable maBean;
	
	//@Autowired @Qualifier("IBD50InitRunnable")
	//private Runnable i50Bean;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	protected static Logger staticLog = Logger.getLogger(marketAnalyzerListener.class.getName());
	
	private static Thread t1, t2;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		//Capturing the point when Tomcat is done booting up
		log.debug("1.0 Tomcat has booted up");
		WebApplicationContextUtils
			.getRequiredWebApplicationContext(sce.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
		
		//After Tomcat is ready then spawn the Market Indices database initialization thread 
		log.trace("1.1 Creating the DB init thread");
		String thread1Name = PropCache.getCachedProps("threads.dbinit");;
		t1 = new Thread(maBean, thread1Name);
		log.trace("1.2 Starting the DB init thread");
		//t1.start();
		
		/*
		 * No longer subscribed to IBD50, therefore this feature is useless
		 
		//Also starting up the IBD50 thread
		String thread2Name = PropCache.getCachedProps("threads.IBD50");
		t2 = new Thread(i50Bean, thread2Name);
		t2.start();
		
		*/
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub	
	}
	
	public static boolean dbInitThreadIsAlive() {
		// java.lang.Thread.State can be NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
		String thread_name = PropCache.getCachedProps("threads.dbinit");
		staticLog.debug("Checking thread state. Thread name: " + thread_name + " State:" + t1.getState() + " and currently is alive? " + t1.isAlive());
		return t1.isAlive();
	}
}
