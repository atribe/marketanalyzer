package com.ar.marketanalyzer.indexbacktest.plotting;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import com.ar.marketanalyzer.core.threads.marketAnalyzerListener;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;
import com.ar.marketanalyzer.indexbacktest.services.IndexCalcsService;
import com.ar.marketanalyzer.spring.init.PropCache;

public class PlotDDay {
	
	static Logger log = Logger.getLogger(PlotDDay.class.getName());
	
	public static JFreeChart createChart() {
		String[] indexList = PropCache.getCachedProps("index.names").split(",");
		XYDataset dataset = createDataset(indexList);
		
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
				"D Day Chart",	// title
	            "Date",			// x-axis label
	            "D-Days",		// y-axis label
	            dataset,		// data
	            true,			// create legend?
	            true,			// generate tooltips?
	            false			// generate URLs?
	        );

	        // Set chart styles
	        chart.setBackgroundPaint(Color.white);

	        // Get the plot from the chart
	        XYPlot plot = (XYPlot) chart.getPlot();
	        
	        // Set plot styles
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
	                
	        // Set series line styles
	        /*
	        XYItemRenderer r = plot.getRenderer();
	        if (r instanceof XYLineAndShapeRenderer) {
	            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
	            //renderer.setShapesVisible(true);
	            //renderer.setShapesFilled(true);
	        }
	        */
	        
	        // Set date axis style
	        DateAxis axis = (DateAxis) plot.getDomainAxis();
			axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
			axis.setVerticalTickLabels(true);
	        
	        return chart;
	}

	/**
     * Creates a dataset, consisting of two series of monthly data.
     *
     * @return the dataset.
     */
    @SuppressWarnings("unused")
	private static XYDataset createDataset(String symbol) {

        TimeSeries s1 = new TimeSeries(symbol);
        
        //s1.add(new Day(10, 1, 2004), 10574);  
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");
	        //Getting the d-dates from the database
	        IndexCalcsService  indexCalcsService = new IndexCalcsService();
	        if(indexCalcsService.isM_connectionAlive()) {
	        	List<IndexOHLCVCalcs> dDayList = indexCalcsService.getLatestDDays(symbol);
	        	for(IndexOHLCVCalcs a:dDayList) {
	        		s1.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getDistributionDayCounter());
	        	}
	        	
	        }
	        log.debug("");
        } else {
        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
        	
        }
        s1.setDescription("D Day Chart for " + symbol);
        s1.setKey("D Day Chart for " + symbol);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }
    private static XYDataset createDataset(String[] symbols) {

    	TimeSeriesCollection dataset = new TimeSeriesCollection();
    	
    	for(String symbol:symbols)
    	{
	        TimeSeries s1 = new TimeSeries(symbol);
	        
	        //s1.add(new Day(10, 1, 2004), 10574);  
	        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
	        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");
		        //Getting the d-dates from the database
		        IndexCalcsService  indexCalcsService = new IndexCalcsService();
		        if(indexCalcsService.isM_connectionAlive()) {
		        	List<IndexOHLCVCalcs> dDayList = indexCalcsService.getLatestDDays(symbol);
		        	for(IndexOHLCVCalcs a:dDayList) {
		        		s1.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getDistributionDayCounter());
		        	}
		        	
		        }
		        log.debug("");
	        } else {
	        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
	        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
	        	
	        }
	        s1.setDescription(symbol);
	        s1.setKey(symbol);
	        
	        dataset.addSeries(s1);
    	}
        return dataset;
    }
}
