package com.atomrockets.marketanalyzer.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atomrockets.marketanalyzer.plotting.PlotDDay;

@Controller
@RequestMapping("/charts")
public class ChartsController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(ChartsController.class.getName());

	@RequestMapping(value = "/dday", method = RequestMethod.GET)
	public void drawDDayChart(HttpServletResponse response) {
		response.setContentType("image/png");
		
		JFreeChart chart = PlotDDay.createChart();
		
		try {
    		ChartUtilities.writeChartAsPNG(response.getOutputStream(),  chart,  900,  400);
    		response.getOutputStream().close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
	}
}
