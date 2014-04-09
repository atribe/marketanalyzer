package com.atomrockets.marketanalyzer.plotting;

import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
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
import org.joda.time.LocalDate;

import com.atomrockets.marketanalyzer.dbManagers.IndexYahooDataTableManager;
import com.atomrockets.marketanalyzer.models.IndexCalcs;
import com.atomrockets.marketanalyzer.models.YahooIndexData;

public class PlotOHLC {

	private static final long serialVersionUID = 1L;
	
	static Logger log = Logger.getLogger(PlotOHLC.class.getName());
	
	public static JFreeChart createCombinedChart(String symbol)
	{
		IndexYahooDataTableManager a = null;
		try {
			a = new IndexYahooDataTableManager();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//getting the first and last so I can get the first and last dates
		IndexCalcs first = a.getFirstBySymbol(symbol);
		IndexCalcs last = a.getLastBySymbol(symbol);
		List<YahooIndexData> yahooOHLCList = a.getRowsBetweenDatesBySymbol(symbol, first.getConvertedDate(), last.getConvertedDate());
		
		//Getting the OHLC dataset
	    OHLCDataset OHLCdata = createOHLCDataset(symbol, yahooOHLCList);

	    //setting up the renderer
	    XYItemRenderer renderer1 = new HighLowRenderer();
	    renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
	        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
	        new SimpleDateFormat("d-MMM-yyyy"), 
	        new DecimalFormat("0.00")));
	    renderer1.setSeriesPaint(0, Color.blue);
	    DateAxis domainAxis = new DateAxis("Date");
	    NumberAxis rangeAxis = new NumberAxis("Price");
	    rangeAxis.setNumberFormatOverride(new DecimalFormat("$0.00"));
	    rangeAxis.setAutoRange(true);
	    rangeAxis.setAutoRangeIncludesZero(false);
	    
	    //creating the xy plot
	    XYPlot plot1 = new XYPlot(OHLCdata, domainAxis, rangeAxis, renderer1);
	    plot1.setBackgroundPaint(Color.lightGray);
	    plot1.setDomainGridlinePaint(Color.white);
	    plot1.setRangeGridlinePaint(Color.white);
	    plot1.setRangePannable(true);

	    //Overlay the Long-Term Trend Indicator
	    /*TimeSeries dataset3 = MovingAverage.createMovingAverage(t1, "LT", 49, 49);
	    TimeSeriesCollection collection = new TimeSeriesCollection();
	    collection.addSeries(dataset3);
	    plot1.setDataset(1, collection);*/
	    plot1.setRenderer(1, new StandardXYItemRenderer());

	    //add a second dataset (volume)
	    IntervalXYDataset volumeData = createVolumeDataset(symbol, yahooOHLCList);
	    
	    //create the second renderer
	    XYBarRenderer renderer2 = new XYBarRenderer();
	    renderer2.setDrawBarOutline(false);
	    renderer2.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
	        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
	        new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
	    renderer2.setSeriesPaint(0, Color.red);

	    //creating the second plot
	    XYPlot plot2 = new XYPlot(volumeData, null, new NumberAxis("Volume"), renderer2);
	    plot2.setBackgroundPaint(Color.lightGray);
	    plot2.setDomainGridlinePaint(Color.white);
	    plot2.setRangeGridlinePaint(Color.white);

	    //combining the plots
	    CombinedDomainXYPlot cplot = new CombinedDomainXYPlot(domainAxis);
	    cplot.add(plot1, 3);
	    cplot.add(plot2, 2);
	    cplot.setGap(8.0);
	    cplot.setDomainGridlinePaint(Color.white);
	    cplot.setDomainGridlinesVisible(true);
	    cplot.setDomainPannable(true);


	    //return the new combined chart
	    JFreeChart chart = new JFreeChart(symbol,
	        JFreeChart.DEFAULT_TITLE_FONT, cplot, false);

	    ChartUtilities.applyCurrentTheme(chart);
	    renderer2.setShadowVisible(false);
	    renderer2.setBarPainter(new StandardXYBarPainter());

	    return chart;
	}
	
	private static OHLCDataset createOHLCDataset(String symbol, List<YahooIndexData> yahooOHLCList)
	{

	    OHLCSeries s1 = new OHLCSeries(symbol);
		    
		for(YahooIndexData YID : yahooOHLCList) {
			Day date       =  new Day(YID.getConvertedDate().toDate());
			double open     = YID.getOpen();
			double high     = YID.getHigh();
			double low      = YID.getLow();
			double close    = YID.getClose();
			
			s1.add(date, open, high, low, close);
			
			/*t1.add(new Day(date), close);*/
		}

	    OHLCSeriesCollection dataset = new OHLCSeriesCollection();
	    dataset.addSeries(s1);

	    return dataset;
	}
	
	//create volume dataset
	private static IntervalXYDataset createVolumeDataset(String filename, List<YahooIndexData> yahooOHLCList)
	{
	    //create dataset 2...
	    TimeSeries s2 = new TimeSeries("Volume");

	    for(YahooIndexData YID : yahooOHLCList) {
			Day date       =  new Day(YID.getConvertedDate().toDate());
			double volume = YID.getVolume();
			
			s2.add(date, volume);
			
			/*t1.add(new Day(date), close);*/
		}

	    return new TimeSeriesCollection(s2);
	}
}
