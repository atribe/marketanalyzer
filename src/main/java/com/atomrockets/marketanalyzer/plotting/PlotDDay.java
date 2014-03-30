package com.atomrockets.marketanalyzer.plotting;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

import com.atomrockets.marketanalyzer.models.IndexCalcs;
import com.atomrockets.marketanalyzer.services.IndexCalcsService;
import com.atomrockets.marketanalyzer.threads.marketAnalyzerListener;

public class PlotDDay {

	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(PlotDDay.class.getName());
	
	public static JFreeChart createChart() {
		XYDataset dataset = createDataset("Nasdaq...Probably");
		
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

	        // Set plot styles
	        XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
	                
	        // Set series line styles
	        XYItemRenderer r = plot.getRenderer();
	        if (r instanceof XYLineAndShapeRenderer) {
	            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
	            //renderer.setShapesVisible(true);
	            //renderer.setShapesFilled(true);
	        }
	        
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
    private static XYDataset createDataset(String title) {

        TimeSeries s1 = new TimeSeries(title);
        
        //s1.add(new Day(10, 1, 2004), 10574);  
        if(!marketAnalyzerListener.dbInitThreadIsAlive()) {
        	log.debug("Db Init Thread is not running, pulling D-day info from the DB");
	        //Getting the d-dates from the database
	        IndexCalcsService  indexCalcsService = new IndexCalcsService();
	        if(indexCalcsService.isM_connectionAlive()) {
	        	List<IndexCalcs> dDayList = indexCalcsService.getLatestDDays();
	        	title = dDayList.get(1).getSymbol();
	        	for(IndexCalcs a:dDayList) {
	        		s1.add(new Day(a.getConvertedDate().toDateTimeAtStartOfDay().toDate()), a.getDistributionDayCounter());
	        	}
	        	
	        }
	        log.debug("");
        } else {
        	log.debug("Db Init Thread is running. Skipping D-day info from the DB");
        	//Maybe do something here. Like a boolean so that the whole table is skipped and replaced with something else in the jsp.
        	
        }
        s1.setDescription("D Day Chart for " + title);
        s1.setKey("D Day Chart for " + title);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);

        return dataset;
    }
}
