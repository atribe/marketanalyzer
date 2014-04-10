package com.atomrockets.marketanalyzer.plotting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.ui.TextAnchor;
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.dbManagers.IndexYahooDataTableManager;
import com.atomrockets.marketanalyzer.models.IndexCalcs;
import com.atomrockets.marketanalyzer.models.YahooIndexData;
import com.atomrockets.marketanalyzer.services.IndexCalcsService;

public class PlotOHLC {

	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(PlotOHLC.class.getName());
	
	public static JFreeChart createCombinedChart(String symbol)
	{
		IndexCalcsService a = new IndexCalcsService();
		
		//getting the first and last so I can get the first and last dates
		List<IndexCalcs> OHLCList = a.getRowsBetweenDatesBySymbol(symbol, new LocalDate().minusDays(40), new LocalDate());
		
		/*
		 * Code modified from:
		 * http://stackoverflow.com/questions/11330370/jfreechart-how-to-draw-the-moving-average-over-a-ohlc-chart
		 */
		
		//Getting the OHLC dataset
	    OHLCDataset OHLCdata = createOHLCDataset(symbol, OHLCList);

	    //setting up the renderer
	    XYItemRenderer renderer1 = new HighLowRenderer();
	    renderer1.setSeriesPaint(0, Color.GREEN);
	    renderer1.setSeriesPaint(1, Color.RED);
	    
	    /*
	     * tool tip generator not used when sending the chart through the output stream
	    renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
	        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
	        new SimpleDateFormat("d-MMM-yyyy"), 
	        new DecimalFormat("0.00")));
	    */
	    
	    DateAxis domainAxis = new DateAxis("Date");
	    NumberAxis rangeAxis = new NumberAxis("Price");
	    rangeAxis.setNumberFormatOverride(new DecimalFormat("$0"));
	    rangeAxis.setAutoRange(true);
	    rangeAxis.setAutoRangeIncludesZero(false);
	    
	    //creating the xy plot with the OHLC data
	    XYPlot plot1 = new XYPlot(OHLCdata, domainAxis, rangeAxis, renderer1);

	    //Adding D-Day annotations to the OHLC plot
	    plot1 = annotateOHLC_DDays(plot1,  OHLCList);
	    
	    //Overlay the Long-Term Trend Indicator
	    /*TimeSeries dataset3 = MovingAverage.createMovingAverage(t1, "LT", 49, 49);
	    TimeSeriesCollection collection = new TimeSeriesCollection();
	    collection.addSeries(dataset3);
	    plot1.setDataset(1, collection);*/
	    //plot1.setRenderer(1, new StandardXYItemRenderer());

	    //add a second dataset (volume)
	    IntervalXYDataset volumeData = createVolumeDataset(symbol, OHLCList);
	    
	    //create the second renderer
	    XYBarRenderer renderer2 = new XYBarRenderer();
	    renderer2.setDrawBarOutline(true); //this actually works!
	    renderer2.setShadowVisible(false);
	    renderer2.setSeriesPaint(0, Color.CYAN);
	    renderer2.setSeriesPaint(1, Color.MAGENTA);
	    
	    //creating the second plot
	    XYPlot plot2 = new XYPlot(volumeData, null, new NumberAxis("Volume"), renderer2);

	    //combining the plots
	    CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
	    cplot.add(plot1, 3);
	    cplot.add(plot2, 2);
	    cplot.setGap(8.0);
	    cplot.setDomainGridlinesVisible(true);
	    
	    //return the new combined chart
	    JFreeChart chart = new JFreeChart(symbol,
	        JFreeChart.DEFAULT_TITLE_FONT, cplot, false);

	    //ChartUtilities.applyCurrentTheme(chart);
	    
	    renderer2.setBarPainter(new StandardXYBarPainter());

	    return chart;
	}
	


	private static OHLCDataset createOHLCDataset(String symbol, List<IndexCalcs> OHLCList)
	{

	    OHLCSeries sHigher = new OHLCSeries(symbol + " Higher");
	    OHLCSeries sLower = new OHLCSeries(symbol + " Lower");
	    
	    double yesterdayClose=0;
		    
		for(IndexCalcs YID : OHLCList) {
			Day date       =  new Day(YID.getConvertedDate().toDate());
			double open     = YID.getOpen();
			double high     = YID.getHigh();
			double low      = YID.getLow();
			double close    = YID.getClose();
			
			if(close>yesterdayClose)
				sHigher.add(date, open, high, low, close);
			else
				sLower.add(date, open, high, low, close);
			
			yesterdayClose = close;
			/*t1.add(new Day(date), close);*/
		}

	    OHLCSeriesCollection dataset = new OHLCSeriesCollection();
	    dataset.addSeries(sHigher);
	    dataset.addSeries(sLower);
	    
	    return dataset;
	}
	
	//create volume dataset
	private static IntervalXYDataset createVolumeDataset(String filename, List<IndexCalcs> OHLCList)
	{
	    //create dataset 2...
	    TimeSeries sHigher = new TimeSeries("Volume Higher");
	    TimeSeries sLower = new TimeSeries("Volume Lower");
	    
	    double yesterdaysVolume = 0;

	    for(IndexCalcs YID : OHLCList) {
			Day date       =  new Day(YID.getConvertedDate().toDate());
			double volume = YID.getVolume();
			
			if(volume>yesterdaysVolume) {
				sHigher.add(date, volume);
			} else {
				sLower.add(date, volume);
			}
			yesterdaysVolume = volume;
			/*t1.add(new Day(date), close);*/
		}
	    TimeSeriesCollection volumeDataset = new TimeSeriesCollection();
	    volumeDataset.addSeries(sLower);
	    volumeDataset.addSeries(sHigher);
	    return volumeDataset;
	}

	private static XYPlot annotateOHLC_DDays(XYPlot plot, List<IndexCalcs> OHLCList) {
		// add some annotations...   
        XYTextAnnotation annotation = null;   
        Font font = new Font("SansSerif", Font.PLAIN, 9); 

        double x = 0;
        double y = 0;
        
        for(IndexCalcs YID : OHLCList) {
        	if(YID.getDistributionDay()) {
        		x = new Day(YID.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = YID.getLow();
        		annotation = new XYTextAnnotation("D Day", x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);   
                plot.addAnnotation(annotation); 
        	} else if (YID.getChurnDay()) {
        		x = new Day(YID.getConvertedDate().toDate()).getMiddleMillisecond();
        		y = YID.getLow();
        		annotation = new XYTextAnnotation("C Day", x, y);  
                annotation.setFont(font);   
                annotation.setTextAnchor(TextAnchor.HALF_ASCENT_LEFT);   
                plot.addAnnotation(annotation);
        	}
        }
		return plot;
	}
}
