package com.atomrockets.marketanalyzer.threads;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atomrockets.marketanalyzer.beans.MarketAnalyzerBean;

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
		t = new Thread(maBean, "marketAnalyzerListenerThread");
		
		t.start();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}


}
