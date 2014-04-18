package com.atomrockets.marketanalyzer.services;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.IndexCalcs;
import com.atomrockets.marketanalyzer.beans.IndexOHLCVCalcs;
import com.atomrockets.marketanalyzer.beans.OHLCVData;
import com.atomrockets.marketanalyzer.dbManagers.IndexCalcsDAO;
import com.atomrockets.marketanalyzer.dbManagers.BacktestResultDAO;
import com.atomrockets.marketanalyzer.dbManagers.MarketPredDataSource;
import com.atomrockets.marketanalyzer.dbManagers.OHLCVDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.joda.time.LocalDate;

public class IndexCalcsService extends GenericServiceSuperclass{
	/*
	 * These variables are class member properties and once set they can be used by 
	 * all methods of the class. However, because this is a static class they need to be
	 * set every time you enter into the class from outside.
	 */
	
	//Database Table Managers
	private OHLCVDao m_OHLCVDao;
	private BacktestResultDAO m_BacktestResultDAO;
	private IndexCalcsDAO m_indexCalcsDAO;
	
	private BacktestResult m_b;

	//Index names
	private String m_symbol;

	//member variables related to dates or number of days
	private int m_bufferDays;
	private LocalDate m_startDate;
	private LocalDate m_endDate;
	
	//member variable for holding all the information for analysis
	private List<OHLCVData> m_yahooIndexDataList;
	private List<IndexOHLCVCalcs> m_IndexCalcList;

	public List<IndexOHLCVCalcs> getM_IndexCalcList() {
		return m_IndexCalcList;
	}

	/*
	 * Converts the YahooIndexData object list into IndexOHLCVCalcs object list
	 */
	public void setM_IndexCalcList(List<OHLCVData> yahooIndexDataList) {
		m_IndexCalcList = new ArrayList<IndexOHLCVCalcs>();
		for(OHLCVData A:yahooIndexDataList)
		{
			IndexOHLCVCalcs B = new IndexOHLCVCalcs();
			B.setOHLCid(A.getId());
			B.setDate(A.getDate());
			B.setOpen(A.getOpen());
			B.setHigh(A.getHigh());
			B.setLow(A.getLow());
			B.setClose(A.getClose());
			B.setVolume(A.getVolume());
			B.setSymbol(A.getSymbol());
			
			m_IndexCalcList.add(B);
		}
	}

	public IndexCalcsService() {
		m_ds = MarketPredDataSource.setDataSource();
		setM_connectionAlive(true);
		m_OHLCVDao = new OHLCVDao(m_ds);
		m_BacktestResultDAO = new BacktestResultDAO(m_ds);
		m_indexCalcsDAO = new IndexCalcsDAO(m_ds);
	}
	
	public IndexCalcsService(DataSource ds) {
		m_ds = ds;
		m_OHLCVDao = new OHLCVDao(ds);
		m_BacktestResultDAO = new BacktestResultDAO(ds);
		m_indexCalcsDAO = new IndexCalcsDAO(ds);
	}
	
	public void init(String[] indexList) {
		
		m_indexCalcsDAO.tableInitialization(indexList);
		String tableName = IndexCalcs.getTableName();
		//Reset the table so that the data can be reanalyzed
		try {
			m_indexCalcsDAO.resetTable(tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String symbol:indexList)
		{
			runIndexAnalysis(symbol);//1 is the S&P500
		}
	}
	
	public void runIndexAnalysis(String symbol) {
		/*
		 * Future Index Analysis
		 * 
		 * 1.Determine what dates are needed based off the start and end date, as well as the
		 * 		extra days required by the buffer
		 * 2.Calculates and stores D dates in the date range
		 * 3.Calculates and stores churning dates in the date range
		 * 4.Calculates and stores follow through dates
		 * 5.Uses d-dates,churning dates, follow through dates to set buy and sell dates
		 * 6.Calculates a return based off the buy and sell periods
		 * 7.Saves needed data to DB so that it maybe displayed on the website.
		 */
		
		setM_symbol(symbol);
		
		log.info("--------------------------------------------------------------------");
		log.info("Starting Index Analyzer for " + m_symbol);

		//0. Get parameters for the given index
		try {
			m_b = m_BacktestResultDAO.getSymbolParameters(getM_symbol());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1. Setting the number of buffer days needed to calc averages and such
		setBufferDays();

		//2. Calculating the id's of the start and end date of the loop
		setM_startDate();
		setM_endDate();
		
		m_yahooIndexDataList = m_OHLCVDao.getRowsBetweenDatesBySymbol(m_symbol, m_startDate, m_endDate);

		setM_IndexCalcList(m_yahooIndexDataList);
		
		calcIndexStatistics();
		
		//2. Calculate d-dates
		distributionDayAnalysis();
		
		//followThruAnalysis();
		
		m_indexCalcsDAO.addAllRowsToDB(m_symbol, m_IndexCalcList);
	}

	public String getM_symbol() {
		return m_symbol;
	}

	public void setM_symbol(String m_symbol) {
		this.m_symbol = m_symbol;
	}

	private void setBufferDays(){
		/*
		 * Conditions should be put in order of most amount days to fewest.
		 * That way the if statements will progress through and stop on the longest one that is active.
		 */
		
		if(m_b.getChurnAVG50On()) {
			m_bufferDays=50;
		} else if(m_b.getPivotTrend35On()) {
			m_bufferDays=35;
		} else {
			m_bufferDays=0;
		}
	}

	private int getBufferDays(){
		return m_bufferDays;
	}

	/**
	 * Gets the loopEndId by pulling the end date from the parameter database and then looking that date up in the price/volume database
	 * 
	 */
	private void setM_endDate() {
		m_endDate = new LocalDate();
	}

	private void setM_startDate() {
		LocalDate startDate = new LocalDate(m_b.getStartDate());
		IndexOHLCVCalcs beginDataPoint = m_OHLCVDao.getFirstBySymbol(m_symbol);
		LocalDate symbolBeginDate = beginDataPoint.getConvertedDate();
		
		if( symbolBeginDate.isBefore( startDate.minusDays( getBufferDays() ) ) )
		{
			m_startDate = startDate;
		} else {
			m_startDate = symbolBeginDate;
		}
	}

	private void calcIndexStatistics() {
		/*
		 * Statistics calculated
		 * 1. 50 day close price average
		 * 2. 100 day close price average
		 * 3. 200 day close price average
		 * 4. 50 day volume average
		 * 5. Price Trend for the last 35 days
		 * 		This is the average percent gain over the last 35 days (excluding today)
		 */

		//This lists starts with the newest date, which means the loop is goes back in time with each iteration
		
		for(int i=m_IndexCalcList.size()-1; i>0; i--) {
			//loopDays is how far the current loop goes back
			/*
			 * Loop for 50 days
			 * Calculates the 50 day moving average
			 * and calculates the 50 day moving volume average
			 */
			int loopDays = 50;
			double priceCloseSum = 0;
			long volumeSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_IndexCalcList.get(j).getClose();

				//summing up for volumeAverage
				volumeSum+=m_IndexCalcList.get(j).getVolume();
			}
			m_IndexCalcList.get(i).setCloseAvg50(priceCloseSum/loopDays);
			m_IndexCalcList.get(i).setVolumeAvg50(volumeSum/loopDays);
			
			/*
			 * Loop for 100 days
			 * Calculates the 100 day moving average
			 */
			loopDays = 100;
			priceCloseSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_IndexCalcList.get(j).getClose();
			}
			m_IndexCalcList.get(i).setCloseAvg100(priceCloseSum/loopDays);
			
			/*
			 * Loop for 200 days
			 * Calculates the 200 day moving average
			 */
			loopDays = 200;
			priceCloseSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_IndexCalcList.get(j).getClose();
			}
			m_IndexCalcList.get(i).setCloseAvg200(priceCloseSum/loopDays);
			
			/*
			 * Loop for 35 days
			 * Calculates Price Trend of the previous 35 days'
			 * 		This is the average percent gain over the last 35 days (excluding today)
			 */
			loopDays = 35;
			double closePercentChange = 0;
			for(int j=i; j>i-loopDays && j>2; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				closePercentChange+=(m_IndexCalcList.get(j-1).getClose() - m_IndexCalcList.get(j-2).getClose()) / m_IndexCalcList.get(j-2).getClose();
			}
			m_IndexCalcList.get(i).setPriceTrend35(closePercentChange/loopDays);
			
		}
	}
	
	private void distributionDayAnalysis(){
		log.info("     Starting D-Day Counting and recording");
		
		
		try {
			/* 
			 * TODO there should probably be a table that has the timestamp of when the pricevolume tables have been updated
			 * and when the parameter tables have been updated
			 * and when the analysis tables have been updated
			 * 
			 * Then when this method is run it would first check to see if there have been any changes to either table since the last update
			 */		

			
			
			//Check and record all d days in the DB
			checkForDDays();
			
			//churning D Day finder
			checkForChurningDays();
		
			//Counting up d-day that have fallen in a given window is handled in the following function
			countDDaysInWindow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkForDDays() throws SQLException {
		log.info("          Checking to see if each day is a D-Day");
		
		int rowCount = m_IndexCalcList.size();
		
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{ 
			/*
			 * D day rules
			 * 1. Volume Higher than the previous day
			 * 2. Price drops by X% (IBD states .2%)
			 */
			
			// {{ pulling variables from List
			long todaysVolume = m_IndexCalcList.get(i).getVolume();
			long previousDaysVolume = m_IndexCalcList.get(i-1).getVolume();
			
			double todaysClose = m_IndexCalcList.get(i).getClose();
			double previousDaysClose = m_IndexCalcList.get(i-1).getClose();

			double closePercentChange = (todaysClose/previousDaysClose-1);
			double closePercentRequiredDrop = m_b.getdDayPriceDrop();
			// }}
			
			if( todaysVolume > previousDaysVolume /*This is rule #1*/ && closePercentChange < closePercentRequiredDrop /*This is rule #1*/)
			{
				m_IndexCalcList.get(i).setDistributionDay(Boolean.valueOf(true));
			}
			else
			{
				m_IndexCalcList.get(i).setDistributionDay(Boolean.valueOf(false));
			}
		}
	}
	
	private void checkForChurningDays() {
		log.info("          Checking to see if each day is a Churning Day");
		
		int rowCount = m_IndexCalcList.size();
		
		// {{ Getting variables from the parameter database
		
		double churnVolRange = m_b.getChurnVolRange();
		double churnPriceRange = m_b.getChurnPriceRange();
		boolean churnPriceCloseHigherOn = m_b.getChurnPriceCloseHigherOn();
		boolean churnAVG50On = m_b.getChurnAVG50On();
		boolean churnPriceTrend35On = m_b.getChurnPriceTrend35On();
		double churnPriceTrend35 = m_b.getChurnPriceTrend35();
		// }}
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{
			/*
			 * this part gets churning ddays
			 * churning is defined as
			 * 1. price must close in the bottom half of its range
			 * 2. volume must be within 3% of the previous days volume
			 * 3. priceClose must be less than 102% of the previous day
			 * The next rules can be turned on or off from the parameter DB
			 * 4. priceClose must be greater than or equal to previous day (should not be turned on by itself)
			 * 5. volume must be greater than avg daily
			 * 6. price must be on upswing over  previous 35 days
			 */
			
			// {{ pulling variables from List, just to make the code below prettier
			double todaysHigh = m_IndexCalcList.get(i).getHigh();			
			//double previousDaysHigh = m_IndexCalcList.get(i-1).getHigh();
			
			double todaysLow = m_IndexCalcList.get(i).getLow();			
			//double previousDaysLow = m_IndexCalcList.get(i-1).getLow();
			
			double todaysClose = m_IndexCalcList.get(i).getClose();
			double previousDaysClose = m_IndexCalcList.get(i-1).getClose();
			
			long todaysVolume = m_IndexCalcList.get(i).getVolume();
			long previousDaysVolume = m_IndexCalcList.get(i-1).getVolume();
			
			long todaysVolumeAvg50 = m_IndexCalcList.get(i).getVolumeAvg50();
			
			double todaysPriceTrend35 = m_IndexCalcList.get(i).getPriceTrend35();
			
			// }}
			
			
			if( todaysClose < (todaysHigh + todaysLow)/2 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose <= previousDaysClose*(1+churnPriceRange) /*rule 3*/)
			{
				m_IndexCalcList.get(i).setChurnDay(true);
				//MarketIndexAnalysisDB.addDDayStatus(ps, m_IndexCalcList.get(i).getPVD_id(), true);
			} else {
				// {{ Churn day conditions set by the parameter db
				int conditionsRequired = 0;
				int conditionsMet = 0;
				if (churnPriceCloseHigherOn)
					conditionsRequired++;
				if (churnAVG50On)
					conditionsRequired++;
				if (churnPriceTrend35On)
					conditionsRequired++;
				// }}
				
				if(churnPriceCloseHigherOn && todaysClose >= previousDaysClose) //rule 4
					conditionsMet++;
								 
				if(churnAVG50On && todaysVolume > todaysVolumeAvg50 ) //rule 5
					conditionsMet++;
				
				if(churnPriceTrend35On && todaysPriceTrend35 > churnPriceTrend35) //rule 6
					conditionsMet++;
				
				if(conditionsRequired == conditionsMet && conditionsRequired != 0)
				{
					m_IndexCalcList.get(i).setChurnDay(Boolean.valueOf(true));
				} else {
					m_IndexCalcList.get(i).setChurnDay(Boolean.valueOf(false));
				}
			}
			//No need set to false because it was already done by the d-day method.
		}
	}
	
	public void countDDaysInWindow() {
		
		//Getting window length from parameter database
		int dDayWindow = m_b.getdDayWindow();
		//getting the sell threshold
		int dDayParam = m_b.getdDayParam();
		log.info("          Looking at each day to see how many D-Dates at in the current window (" + dDayWindow + " days).");
		
		/* 
		 * 		2. For each loop of all the data
		 * 		3. As the loop progresses through each row, look back in the data the number of days in the window
		 * 			and see how many d-days there are
		 * 		4. Write the results to the database 
		*/
		

		//This list starts with the newest date, which means the loop is goes back in time with each iteration 
		for(int i=m_IndexCalcList.size()-1; i>0; i--) {

			for(int j=i; j>i-dDayWindow && j>0; j--) { //This loop starts at i and then goes back dDayWindow days adding up all the d days

				if( Boolean.TRUE.equals(m_IndexCalcList.get(j).getDistributionDay()) || Boolean.TRUE.equals(m_IndexCalcList.get(j).getChurnDay()) ) {
					m_IndexCalcList.get(i).addDDayCounter();
				}
				
				if( m_IndexCalcList.get(i).getDistributionDayCounter() > dDayParam) {
					m_IndexCalcList.get(i).setDayAction(IndexCalcs.dayActionType.SELL);
				}
			}
		}
	}
	/*

	private void followThruAnalysis() {
		log.info("          Checking to see if each day is a Follow Through Day");
		
		int rowCount = m_IndexCalcList.size();
		int followThroughDayCount=0;
		
		// {{ Getting variables from the parameter database
		int rDaysMax = m_b.getrDaysMax();
		boolean churnpivotTrend35On = m_b.getPivotTrend35On();
		double pivotTrend35 = m_b.getPivotTrend35();
		// }}
		
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{
			
			// this part gets followthrough days
			 
			double rallyPriceHigh = 0;
			
		}
	}
	*/
	public synchronized List<IndexOHLCVCalcs> getLatestDDays(String symbol) {
		List<IndexOHLCVCalcs> dDayList = new ArrayList<IndexOHLCVCalcs>();
		
		if(m_indexCalcsDAO.tableExists(IndexCalcs.getTableName()))
		{
			/*
			 * 1. Select dates 
			 */
			m_symbol = symbol;
			
			LocalDate startDate = new LocalDate().minusDays(120);
			LocalDate today = new LocalDate();
	
			dDayList = getRowsBetweenDatesBySymbol(m_symbol, startDate, today);
		}
		return dDayList;
	}

	public List<IndexOHLCVCalcs> getRowsBetweenDatesBySymbol(String symbol, LocalDate startDate, LocalDate endDate) {
		List<IndexOHLCVCalcs> dDayList = new ArrayList<IndexOHLCVCalcs>();
		
		//Converting the dates into valid dates in the db
		//1. checks the provided date and then goes backwards looking for a valid date
		IndexOHLCVCalcs first = m_OHLCVDao.getValidDate(symbol, startDate, false);
		if(first==null) {
			//2. if it didn't find a valid date (hence the IndexOHLCVCalcs returned is null) then look forward
			 first = m_OHLCVDao.getValidDate(symbol, startDate, true);
		}
		//3. if the first is still null get the first value in the database for that symbol
		if(first==null) {
			first = m_OHLCVDao.getFirstBySymbol(symbol);
		}
		
		//1. checks the provided date and then goes backwards looking for a valid date
		IndexOHLCVCalcs last = m_OHLCVDao.getValidDate(symbol, endDate, false);
		if(last == null) {
			//2. if it didn't find a valid date (hence the IndexOHLCVCalcs returned is null) then look forward
			last = m_OHLCVDao.getValidDate(symbol, endDate, true);
		}
		//3. if the first is still null get the first value in the database for that symbol
		if(last==null) {
			last = m_OHLCVDao.getLastBySymbol(symbol);
		}
		String YahooTableName = OHLCVData.getTablename();
		String IndexCalcTableName = IndexCalcs.getTableName();
		
		String query = "SELECT *"
				+ " FROM `" + YahooTableName + "` Y"
				+ " INNER JOIN `" + IndexCalcTableName + "` C"
				+ " ON C.OHLCid = Y.id"
				+ " WHERE Y.date BETWEEN ? and ?"
				+ " AND Y.symbol = ?"
				+ " ORDER BY Y.date ASC";

		/*
		 * Beginning of DbUtils code
		 */
		QueryRunner run = new QueryRunner();
		// Use the BeanListHandler implementation to convert all
		// ResultSet rows into a List of Person JavaBeans.
		ResultSetHandler<List<IndexOHLCVCalcs>> h = new BeanListHandler<IndexOHLCVCalcs>(IndexOHLCVCalcs.class);
		
		try{
			Connection con = m_ds.getConnection();
			dDayList = run.query(
		    		con, //connection
		    		query, //query (in the same form as for a prepared statement
		    		h, //ResultSetHandler
		    		// an arg should be entered for every ? in the query
		    		startDate.toString(),
		    		endDate.toString(),
		    		symbol);

			con.close();
		        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return dDayList;
		
	}

	
	public void updateIndexCalcs(String[] indexList) {
		String tableName = IndexCalcs.getTableName();
		//Reset the table so that the data can be reanalyzed
		try {
			m_indexCalcsDAO.resetTable(tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String symbol:indexList)
		{
			runIndexAnalysis(symbol);//1 is the S&P500
		}
	}
}
