package com.ar.marketanalyzer.spring.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/charts")
public class ChartsController {
	/* Get actual class name to be printed on */
	//private static final Logger logger = LogManager.getLogger(ChartsController.class.getName());

	@RequestMapping(value = "/dday", method = RequestMethod.GET)
	public void drawDDayChart(HttpServletResponse response) {
		
//		try {
//			JFreeChart chart = PlotDDay.createChart();
//			
//			if(chart!=null) {
//				//Setting the response type
//				response.setContentType("image/png");
//				ChartUtilities.writeChartAsPNG(response.getOutputStream(),  chart,  1400,  400);
//				response.getOutputStream().close();
//		    	
//			}
//		} catch (IOException ex) {
//    		ex.printStackTrace();
//    	}
	}
	
	@RequestMapping(value = "/OHLC", method = RequestMethod.GET)
	public void drawOHLCChart(HttpServletResponse response) {
		response.setContentType("image/png");
		
//		JFreeChart chart = PlotOHLC.createChart("^IXIC");
//		if(chart!=null) {
//			try {
//	    		ChartUtilities.writeChartAsPNG(response.getOutputStream(),  chart,  1400,  600);
//	    		response.getOutputStream().close();
//	    	} catch (IOException ex) {
//	    		ex.printStackTrace();
//	    	}
//		}
	}
	
	@RequestMapping(value = {"/backtest/{symbol}", "/backtest/{symbol}/"}, method = RequestMethod.GET)
	public void drawModelResultChart(HttpServletResponse response, @PathVariable String symbol) {
		response.setContentType("image/png");
		
//		JFreeChart chart = PlotModelResult.createChart(symbol);
//		if(chart!=null) {
//			try {
//	    		ChartUtilities.writeChartAsPNG(response.getOutputStream(),  chart,  1200,  600);
//	    		response.getOutputStream().close();
//	    	} catch (IOException ex) {
//	    		ex.printStackTrace();
//	    	}
//		}
	}
}
