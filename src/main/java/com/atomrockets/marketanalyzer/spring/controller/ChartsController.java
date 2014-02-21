package com.atomrockets.marketanalyzer.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atomrockets.marketanalyzer.plotting.indexTimeSeriesPlot;

@Controller
@RequestMapping("/charts")
public class ChartsController {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(AccountController.class.getName());

    @RequestMapping(value = "/piechart", method = RequestMethod.GET)
    public void drawPieChart(HttpServletResponse response) {
    	response.setContentType("image/png");
    	//PieDataset pdSet = createDataSet();
    	
    	JFreeChart chart = indexTimeSeriesPlot.createChart();//createChart(pdSet, "My Pie Chart");
    	
    	try {
    		ChartUtilities.writeChartAsPNG(response.getOutputStream(),  chart,  750,  400);
    		response.getOutputStream().close();
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	}
    }
    
    private PieDataset createDataSet() {
    	DefaultPieDataset dpd = new DefaultPieDataset();
    	dpd.setValue("Mac", 21);
    	dpd.setValue("Linux", 30);
    	dpd.setValue("Window", 40);
    	dpd.setValue("Others", 9);
    	return dpd;
    }
    
    private JFreeChart createChart(PieDataset pdSet, String chartTitle) {
    	JFreeChart chart = ChartFactory.createPieChart3D(chartTitle,  pdSet, true, true, false);
    	PiePlot3D plot = (PiePlot3D) chart.getPlot();
    	plot.setStartAngle(290);
    	plot.setDirection(Rotation.CLOCKWISE);
    	plot.setForegroundAlpha(0.5f);
    	return chart;
    }
}
