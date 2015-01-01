package com.ar.marketanalyzer.core.threads;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
public class MarketAnalyzerListener implements ServletContextListener{

	@Autowired @Qualifier("IndexBacktestInit")
	private Runnable indexBacktestBean;
	
	//@Autowired @Qualifier("IBD50InitRunnable")
	//private Runnable i50Bean;
	
	@Value("${threads.backtest}")
	private String BACKTEST_THREAD_NAME;
	@Value("${threads.dbinit")
	private String DB_INIT_THREAD_NAME;
	
	private static final Logger logger = LogManager.getLogger(MarketAnalyzerListener.class.getName());
	
	@SuppressWarnings("unused")
	private static Thread t1, t2;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		//Capturing the point when Tomcat is done booting up
		logger.debug("1.0 Tomcat has booted up");
		WebApplicationContextUtils
			.getRequiredWebApplicationContext(sce.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
		
		//After Tomcat is ready then spawn the Market Indices database initialization thread 
		
		
		t1 = new Thread(indexBacktestBean, BACKTEST_THREAD_NAME);
		t1.start();
		
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
		//logger.debug("Checking thread state. Thread name: " + DB_INIT_THREAD_NAME + " State:" + t1.getState() + " and currently is alive? " + t1.isAlive());
		return t1.isAlive();
	}
}
