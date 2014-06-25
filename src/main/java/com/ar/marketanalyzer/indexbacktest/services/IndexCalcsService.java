package com.ar.marketanalyzer.indexbacktest.services;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean;
import com.ar.marketanalyzer.indexbacktest.beans.IndexCalcs;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVCalcs;
import com.ar.marketanalyzer.indexbacktest.beans.IndexOHLCVData;
import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean.parametersTypeEnum;
import com.ar.marketanalyzer.indexbacktest.beans.IndexCalcs.dayActionType;
import com.ar.marketanalyzer.indexbacktest.dao.BacktestResultDAO;
import com.ar.marketanalyzer.indexbacktest.dao.IndexCalcsDAO;
import com.ar.marketanalyzer.indexbacktest.dao.OHLCVDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.joda.time.LocalDate;

public class IndexCalcsService extends IndexBacktestServiceSuperclass{
	/*
	 * These variables are class member properties and once set they can be used by 
	 * all methods of the class. However, because this is a static class they need to be
	 * set every time you enter into the class from outside.
	 */
	
	//Database Table Managers
	private OHLCVDao m_OHLCVDao;
	private BacktestResultDAO m_BacktestResultDAO;
	private IndexCalcsDAO m_indexCalcsDAO;
	
	private BacktestBean m_b;

	//Index names
	private String m_symbol;

	//member variables related to dates or number of days
	private int m_bufferDays;
	private LocalDate m_startDate;
	private LocalDate m_endDate;
	
	//member variable for holding all the information for analysis
	private List<IndexOHLCVData> m_yahooIndexDataList;
	private List<IndexOHLCVCalcs> m_IndexCalcList;

	public List<IndexOHLCVCalcs> getM_IndexCalcList() {
		return m_IndexCalcList;
	}

	/*
	 * Converts the YahooIndexData object list into IndexOHLCVCalcs object list
	 */
	public void setM_IndexCalcList(List<IndexOHLCVData> yahooIndexDataList) {
		m_IndexCalcList = new ArrayList<IndexOHLCVCalcs>();
		for(IndexOHLCVData A:yahooIndexDataList)
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
	
	public void runNewIndexAnalysisFromBacktest(BacktestBean bt) {
		m_b = bt;
		
		clearOldIndexAnalysisDataFromDB(m_b.getSymbol());
		
		runIndexAnalysis(m_b.getSymbol());
	}
	
	private void clearOldIndexAnalysisDataFromDB(String symbol) {
		try {
			String getParametersQuery = 
					"DELETE i.* FROM `" + IndexCalcs.getTableName() + "` i" +
					" INNER JOIN `" + IndexOHLCVData.getTablename() + "` o ON o.id=i.OHLCid" +
					" WHERE o.symbol = ?";
			QueryRunner runner = new QueryRunner(m_ds);

			runner.update(getParametersQuery, symbol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void runIndexAnalysis(String symbol) {
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

		//m_b can be set
		if(m_b==null) {
			//0. Get parameters for the given index
			try {
				m_b = m_BacktestResultDAO.getSymbolParameters(getM_symbol(), parametersTypeEnum.BASE);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		
		followThruAnalysis();
		
		determineDayAction();
		
		m_indexCalcsDAO.addAllRowsToDB(m_symbol, m_IndexCalcList);
		
		//resetting m_b for the so when this method is called in a loop a new m_b will be loaded
		m_b=null;
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
		//always want to use the backtestResult parameter to today, so that the plot on the homepage will reflect it
		m_endDate = new LocalDate();
	}

	private void setM_startDate() {
		LocalDate startDate = m_b.getLocalDateStartDate();
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
			BigDecimal priceCloseSum = new BigDecimal(0.0);
			long volumeSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(m_IndexCalcList.get(j).getClose());

				//summing up for volumeAverage
				volumeSum+=m_IndexCalcList.get(j).getVolume();
			}
			m_IndexCalcList.get(i).setCloseAvg50(priceCloseSum.divide(new BigDecimal(loopDays)));
			m_IndexCalcList.get(i).setVolumeAvg50(volumeSum/loopDays);
			
			/*
			 * Loop for 100 days
			 * Calculates the 100 day moving average
			 */
			loopDays = 100;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(m_IndexCalcList.get(j).getClose());
			}
			m_IndexCalcList.get(i).setCloseAvg100(priceCloseSum.divide(new BigDecimal(loopDays)));
			
			/*
			 * Loop for 200 days
			 * Calculates the 200 day moving average
			 */
			loopDays = 200;
			priceCloseSum = new BigDecimal(0);
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum = priceCloseSum.add(m_IndexCalcList.get(j).getClose());
			}
			m_IndexCalcList.get(i).setCloseAvg200(priceCloseSum.divide(new BigDecimal(loopDays)));
			
			/*
			 * Loop for 35 days
			 * Calculates Price Trend of the previous 35 days'
			 * 		This is the average percent gain over the last 35 days (excluding today)
			 */
			loopDays = 35;
			double closePercentChange = 0;
			for(int j=i; j>i-loopDays && j>2; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				closePercentChange+=(m_IndexCalcList.get(j-1).getClose().subtract(m_IndexCalcList.get(j-2).getClose())).doubleValue() / m_IndexCalcList.get(j-2).getClose().doubleValue();
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

	private void checkForDDays() throws SQLException {
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
			
			BigDecimal todaysClose = m_IndexCalcList.get(i).getClose();
			BigDecimal previousDaysClose = m_IndexCalcList.get(i-1).getClose();

			double closePercentChange = (todaysClose.doubleValue()/previousDaysClose.doubleValue()-1);
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
			BigDecimal todaysHigh = m_IndexCalcList.get(i).getHigh();			
			//double previousDaysHigh = m_IndexCalcList.get(i-1).getHigh();
			
			BigDecimal todaysLow = m_IndexCalcList.get(i).getLow();			
			//double previousDaysLow = m_IndexCalcList.get(i-1).getLow();
			
			BigDecimal todaysClose = m_IndexCalcList.get(i).getClose();
			BigDecimal previousDaysClose = m_IndexCalcList.get(i-1).getClose();
			
			long todaysVolume = m_IndexCalcList.get(i).getVolume();
			long previousDaysVolume = m_IndexCalcList.get(i-1).getVolume();
			
			long todaysVolumeAvg50 = m_IndexCalcList.get(i).getVolumeAvg50();
			
			double todaysPriceTrend35 = m_IndexCalcList.get(i).getPriceTrend35();
			
			// }}
			
			
			if( todaysClose.compareTo(todaysHigh.add(todaysLow).divide(new BigDecimal(2))) < 0 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose.compareTo(previousDaysClose.multiply(new BigDecimal(1+churnPriceRange))) <= 0/*rule 3*/)
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
				
				if(churnPriceCloseHigherOn && todaysClose.compareTo(previousDaysClose) >= 0) //rule 4
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
	
	private void followThruAnalysis() {
		log.info("          Checking to see if each day is a Follow Through Day");
		
		findPivotDay();
		
		// {{ Getting variables from the parameter database
		int rDaysMax = m_b.getrDaysMax();
		int rDaysMin = m_b.getrDaysMin();
		double priceMult = m_b.getPriceMult();
		double volMult = m_b.getVolumeMult();
		// }}
		
		//loop starts at 2 because i-2 is accessed
		for(int i = 2; i < m_IndexCalcList.size(); i++) { //Starting at i=1 so that i can use i-1 in the first calculation 
			//if the day is a pivot day, as set by the findPivotDay method then...
			if(m_IndexCalcList.get(i).getPivotDay()) {
				BigDecimal rallyHigh = m_IndexCalcList.get(i).getHigh(); 
				BigDecimal support = m_IndexCalcList.get(i).getLow();
				
				int rallyDayCount = 1;
				/*
				 * When j = i that is the the pivot day
				 * Loop goes until rDaysMax days have been checked for a follow thru day
				 * or a follow thru day has been found
				 */
				for(int j = i; j < i + rDaysMax && j < m_IndexCalcList.size(); j++) {
					//Checking to see if a new high for the rally has been set has been set
					BigDecimal todaysHigh = m_IndexCalcList.get(j).getHigh();
					if( todaysHigh.compareTo(rallyHigh) > 0) {
						rallyHigh = todaysHigh;
					}
					
					BigDecimal todaysClose = m_IndexCalcList.get(j).getClose();
					BigDecimal previousClose = m_IndexCalcList.get(j-1).getClose();
					
					BigDecimal todaysLow = m_IndexCalcList.get(j).getLow();
					
					long todaysVolume = m_IndexCalcList.get(j).getVolume();
					long previousVolume = m_IndexCalcList.get(j-1).getVolume();
					long previousPreviousVolume = m_IndexCalcList.get(j-2).getVolume();
					
					if( todaysClose.compareTo(previousClose.multiply(new BigDecimal(priceMult))) > 0 && //price requirement 
							( todaysVolume > previousVolume * volMult || todaysVolume > previousPreviousVolume ) && //volume requirement
							rallyDayCount > rDaysMin && //follow thru days from pivot day requirement 1
							rallyDayCount < rDaysMax) { //follow thru days from pivot day requirement 2
						//if the above conditions are true the day is a follow thru day
						m_IndexCalcList.get(j).setFollowThruDaySafe(true);
						//and end checking conditions to see if the day is a follow thru day
						break;
					} else if( todaysLow.compareTo(support) < 0) { //if the low drops below the support the rally is over and the day is not a follow thru day
						m_IndexCalcList.get(j).setFollowThruDaySafe(false);
						break;
					} else { //the day must not 
						m_IndexCalcList.get(j).setFollowThruDaySafe(false);
					}
					rallyDayCount++;
				}
			}
			m_IndexCalcList.get(i).setFollowThruDaySafe(false);
		}
	}

	private void findPivotDay() {
		/*
		 * Deffinition: PivotDay = first day of a rally
		 */
		boolean potentialPivotDay = false;
		
		BigDecimal support = new BigDecimal(0); //if price goes below support the rally is broken
		
		/*
		 * Optional criteria: rally can't start unless the pivotTrend35 < -.1%
		 * I'm not going to implement this right away
		 */
		boolean churnpivotTrend35On = m_b.getPivotTrend35On();
		double pivotTrend35 = m_b.getPivotTrend35();
		
		int rDaysMin = m_b.getrDaysMin();
		
		//Loops starts on 2 because the object at i-2 is accessed
		for(int i = 2; i< m_IndexCalcList.size(); i++) {
			//Initializing variables for this loop run
			potentialPivotDay = false;
			BigDecimal todaysClose = m_IndexCalcList.get(i).getClose();
			BigDecimal previousClose = m_IndexCalcList.get(i-1).getClose();
			BigDecimal previousPreviousClose = m_IndexCalcList.get(i-2).getClose();
			
			/*
			 * Pivot day conditions:
			 * 1. Price dropped yesterday from the day before that
			 * 1. Price came up today from yesterday 
			 */
			if( previousPreviousClose.compareTo(previousClose) > 0 && todaysClose.compareTo(previousClose) > 0) {
				
				/*
				 * Optional Condition:
				 * PivotDayTrend35 < -.1%
				 */
				double todaysPivotTrend35 = m_IndexCalcList.get(i).getPriceTrend35();
				
				if(!churnpivotTrend35On) { //if churnPivotTrend35 is turned off then today is a potential pivotDay because it met the criteria in the if statement above
					potentialPivotDay = true;
				} else if( churnpivotTrend35On && todaysPivotTrend35 < pivotTrend35) { //if churnPivotTrend35 is on and pivotTrend35 criteria is met then the day is still a potential pivot day
					potentialPivotDay = true;
				} else { //if churnPivotTrend35 is on and pivotTrend35 criteria is not met then the day is no longer a potential pivot day
					potentialPivotDay = false;
				}
				
				//if the day is still a potentialPivotDay after the additional criteria then check to see if the rally achieves the minimum of rDaysMin days (typically 4)
				if( potentialPivotDay ) {
					support = m_IndexCalcList.get(i).getLow();
					
					/*
					 * Loop starts at the 2nd day in the rally from the potential pivot day (i+1)
					 * 
					 * Loop checks for rally atleast rDaysMin long. at rDaysMin is the soonest a follow thru day could occur
					 */
					for(int j = i + 1; j < i + rDaysMin && j < m_IndexCalcList.size(); j++) {
						BigDecimal nextDayInRallyLow = m_IndexCalcList.get(j).getLow();
						if( nextDayInRallyLow.compareTo(support) < 0) {
							//not a rally
							potentialPivotDay = false;
							break;
						}
					}
				}
			}
			
			if(potentialPivotDay == true) {
				m_IndexCalcList.get(i).setPivotDay(true);
			} else {
				m_IndexCalcList.get(i).setPivotDay(false);
			}
			
		}
	}

	private void determineDayAction() {
		for(IndexOHLCVCalcs c : m_IndexCalcList) {
			if(Boolean.TRUE.equals(c.getFollowThruDay())) {//written in this form because a Boolean could be null
				c.setDayAction(dayActionType.BUY);
			} else if (c.getDistributionDayCounter() > m_b.getdDayParam()) {
				c.setDayAction(dayActionType.SELL);
			} else {
				c.setDayAction(dayActionType.HOLD);
			}
		}
		
	}
	
	public synchronized List<IndexOHLCVCalcs> getLatestDDays(String symbol) {
		List<IndexOHLCVCalcs> dDayList = new ArrayList<IndexOHLCVCalcs>();
		
		if(m_indexCalcsDAO.tableExists(IndexCalcs.getTableName()))
		{
			/*
			 * 1. Select dates 
			 */
			m_symbol = symbol;
			
			LocalDate startDate = new LocalDate().minusDays(150);
			LocalDate today = new LocalDate();
	
			dDayList = getRowsBetweenDatesBySymbol(m_symbol, startDate, today);
		}
		return dDayList;
	}

	public List<IndexOHLCVCalcs> getRowsBetweenDatesBySymbol(String symbol, LocalDate startDate, LocalDate endDate) {
		List<IndexOHLCVCalcs> dDayList = new ArrayList<IndexOHLCVCalcs>();
		
		//Converting the dates into valid dates in the db
		//1. checks the provided date and then goes backwards looking for a valid date
		IndexOHLCVCalcs first = m_OHLCVDao.getValidDateAsCalc(symbol, startDate, false);
		if(first==null) {
			//2. if it didn't find a valid date (hence the IndexOHLCVCalcs returned is null) then look forward
			 first = m_OHLCVDao.getValidDateAsCalc(symbol, startDate, true);
		}
		//3. if the first is still null get the first value in the database for that symbol
		if(first==null) {
			first = m_OHLCVDao.getFirstBySymbol(symbol);
		}
		
		//1. checks the provided date and then goes backwards looking for a valid date
		IndexOHLCVCalcs last = m_OHLCVDao.getValidDateAsCalc(symbol, endDate, false);
		if(last == null) {
			//2. if it didn't find a valid date (hence the IndexOHLCVCalcs returned is null) then look forward
			last = m_OHLCVDao.getValidDateAsCalc(symbol, endDate, true);
		}
		//3. if the first is still null get the first value in the database for that symbol
		if(last==null) {
			last = m_OHLCVDao.getLastBySymbol(symbol);
		}
		String YahooTableName = IndexOHLCVData.getTablename();
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