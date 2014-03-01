package com.atomrockets.marketanalyzer.analyzer;

import com.atomrockets.marketanalyzer.dbManagers.MarketIndexAnalysisDB;
import com.atomrockets.marketanalyzer.dbManagers.MarketIndexDB;
import com.atomrockets.marketanalyzer.dbManagers.MarketIndexParametersDB;
import com.atomrockets.marketanalyzer.beans.MarketIndexAnalysisObject;
import com.atomrockets.marketanalyzer.beans.YahooDataObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class IndexAnalyzer {
	/*
	 * These variables are class member properties and once set they can be used by 
	 * all methods of the class. However, because this is a static class they need to be
	 * set every time you enter into the class from outside.
	 */

	//TODO make a get method for each variable, then if it is called and the variable is null then throw an error so I can quickly catch it
	//Database Connection
	static Connection m_con;

	//Database names
	static String m_index;
	static String m_indexParametersDBName;

	//member variables related to dates or number of days
	static private int m_bufferDays;
	static private int m_loopBeginId;
	static private int m_loopEndId;
	
	//member variable for holding all the information for analysis
	static private List<MarketIndexAnalysisObject> m_analysisRows;

	public static void runIndexAnalysis(Connection connection, String index, String indexParametersDBName) {
		/*
		 * Future Index Analysis
		 * 
		 * 1.Determine what dates are needed based off the start and end date, as well as the
		 * 		extra days required by the buffer
		 * 
		 * 2.Calculates and stores D dates in the date range
		 * 
		 * 3.Calculates and stores churning dates in the date range
		 * 
		 * 4.Calculates and stores follow through dates
		 * 
		 * 5.Uses d-dates,churning dates, follow through dates to set buy and sell dates
		 * 
		 * 6.Calculates a return based off the buy and sell periods
		 * 
		 * 7.Saves needed data to DB so that it maybe displayed on the website.
		 */
		m_con = connection;
		m_index = index;
		m_indexParametersDBName = indexParametersDBName;
		
		System.out.println("");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Starting Index Analyzer for " + m_index);

		//1. Setting the number of buffer days needed to calc averages and such
		setBufferDays();

		//2. Calculating the id's of the start and end date of the loop
		setLoopBeginId();

		setLoopEndId();
		
		m_analysisRows = MarketIndexDB.getDataBetweenIds(m_con, m_index, m_loopBeginId, m_loopEndId);

		calcIndexStatistics();
		
		//2. Calculate d-dates
		distributionDayAnalysis();
		
		//followThruAnalysis();
		
		//MarketIndexAnalysisDB.addAllRowsToDB(m_con, m_index, m_analysisRows);
	}

	private static void setBufferDays(){
		/*
		 * Conditions should be put in order of most amount days to fewest.
		 * That way the if statements will progress through and stop on the longest one that is active.
		 */
		String bufferConditionCheck1 = "churnAVG50On";
		String bufferConditionCheck2 = "pivotTrend35On";
		if(MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, bufferConditionCheck1)) {
			m_bufferDays=50;
		} else if(MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, bufferConditionCheck2)) {
			m_bufferDays=35;
		} else {
			m_bufferDays=0;
		}
	}

	private static int getBufferDays(){
		return m_bufferDays;
	}

	/**
	 * Gets the loopEndId by pulling the end date from the parameter database and then looking that date up in the price/volume database
	 * 
	 */
	private static void setLoopEndId() {
		String keyOriginalEndDate = "endDate";
		LocalDate endDate = MarketIndexParametersDB.getDateValue(m_con, m_indexParametersDBName, keyOriginalEndDate);

		m_loopEndId = MarketIndexDB.getIdByDate(m_con, m_index, endDate, false);
	}

	private static void setLoopBeginId() {
		String keyStartDate = "startDate";
		LocalDate startDate = MarketIndexParametersDB.getDateValue(m_con, m_indexParametersDBName, keyStartDate);

		int beginId = MarketIndexDB.getIdByDate(m_con, m_index, startDate, true);
		if(beginId-m_bufferDays<1) {
			m_loopBeginId = 1;
		} else {
			m_loopBeginId = beginId-m_bufferDays;
		}
	}

	private static void calcIndexStatistics() {
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
		
		for(int i=m_analysisRows.size()-1; i>0; i--) {
			//loopDays is how far the current loop goes back
			/*
			 * Loop for 50 days
			 * Calculates the 50 day moving average
			 * and calculates the 50 day moving volume average
			 */
			int loopDays = 50;
			float priceCloseSum = 0;
			long volumeSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_analysisRows.get(j).getClose();

				//summing up for volumeAverage
				volumeSum+=m_analysisRows.get(j).getVolume();
			}
			m_analysisRows.get(i).setCloseAvg50(priceCloseSum/loopDays);
			m_analysisRows.get(i).setVolumeAvg50(volumeSum/loopDays);
			
			/*
			 * Loop for 100 days
			 * Calculates the 100 day moving average
			 */
			loopDays = 100;
			priceCloseSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_analysisRows.get(j).getClose();
			}
			m_analysisRows.get(i).setCloseAvg100(priceCloseSum/loopDays);
			
			/*
			 * Loop for 200 days
			 * Calculates the 200 day moving average
			 */
			loopDays = 200;
			priceCloseSum = 0;
			for(int j=i; j>i-loopDays && j>0; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				//Summing up for closePriceAvg
				priceCloseSum+=m_analysisRows.get(j).getClose();
			}
			m_analysisRows.get(i).setCloseAvg200(priceCloseSum/loopDays);
			
			/*
			 * Loop for 35 days
			 * Calculates Price Trend of the previous 35 days'
			 * 		This is the average percent gain over the last 35 days (excluding today)
			 */
			loopDays = 35;
			float closePercentChange = 0;
			for(int j=i; j>i-loopDays && j>2; j--) { //This loop starts at i and then goes back loopDays days adding up all the d days
				closePercentChange+=(m_analysisRows.get(j-1).getClose() - m_analysisRows.get(j-2).getClose()) / m_analysisRows.get(j-2).getClose();
			}
			m_analysisRows.get(i).setPriceTrend35(closePercentChange/loopDays);
			
		}
	}
	
	private static void distributionDayAnalysis(){
		System.out.println("     Starting D-Day Counting and recording");
		
		String tableName = MarketIndexAnalysisDB.getTableName();
		
		try {
			/* 
			 * TODO there should probably be a table that has the timestamp of when the pricevolume tables have been updated
			 * and when the parameter tables have been updated
			 * and when the analysis tables have been updated
			 * 
			 * Then when this method is run it would first check to see if there have been any changes to either table since the last update
			 */		

			//Reset the table so that the data can be reanalyzed
			MarketIndexAnalysisDB.resetTable(m_con, tableName);
			
			//Check and record all d days in the DB
			checkForDDays();
			
			//churning D Day finder
			checkForChurningDays();
			
			//Getting window length from parameter database
			String keydDayWindow = "dDayWindow";
			int dDayWindow = MarketIndexParametersDB.getIntValue(m_con, m_indexParametersDBName, keydDayWindow);
		
			//Counting up d-day that have fallen in a given window is handled in the following function
			countDDaysInWindow(dDayWindow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void checkForDDays() throws SQLException {
		System.out.println("          Checking to see if each day is a D-Day");
		
		int rowCount = m_analysisRows.size();
		int ddayCount=0;
		
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{ 
			/*
			 * D day rules
			 * 1. Volume Higher than the previous day
			 * 2. Price drops by X% (IBD states .2%)
			 */
			
			// {{ pulling variables from List
			long todaysVolume = m_analysisRows.get(i).getVolume();
			long previousDaysVolume = m_analysisRows.get(i-1).getVolume();
			
			float todaysClose = m_analysisRows.get(i).getClose();
			float previousDaysClose = m_analysisRows.get(i-1).getClose();

			float closePercentChange = (todaysClose/previousDaysClose-1);
			float closePercentRequiredDrop = (float) -0.002; //TODO make this come from the parameter database
			// }}
			
			if( todaysVolume > previousDaysVolume /*This is rule #1*/ && closePercentChange < closePercentRequiredDrop /*This is rule #1*/)
			{
				ddayCount++;
				m_analysisRows.get(i).setDDay(true);
				//MarketIndexAnalysisDB.addDDayStatus(ps, m_analysisRows.get(i).getPVD_id(), true);
			}
			else
			{
				m_analysisRows.get(i).setDDay(false);
				//MarketIndexAnalysisDB.addDDayStatus(ps, m_analysisRows.get(i).getPVD_id(), false);
			}
		}
	}
	
	private static void checkForChurningDays() {
		System.out.println("          Checking to see if each day is a Churning Day");
		
		int rowCount = m_analysisRows.size();
		int churningDayCount=0;
		
		// {{ Getting variables from the parameter database
		String keychurnVolRange = "churnVolRange";
		float churnVolRange = MarketIndexParametersDB.getFloatValue(m_con, m_indexParametersDBName, keychurnVolRange);
		String keychurnPriceRange = "churnPriceRange";
		float churnPriceRange = MarketIndexParametersDB.getFloatValue(m_con, m_indexParametersDBName, keychurnPriceRange);
		String keychurnPriceCloseHigherOn = "churnPriceCloseHigherOn";
		boolean churnPriceCloseHigherOn = MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, keychurnPriceCloseHigherOn);
		String keychurnAVG50On = "churnAVG50On";
		boolean churnAVG50On = MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, keychurnAVG50On);
		String keychurnPriceTrend35On = "churnPriceTrend35On";
		boolean churnPriceTrend35On = MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, keychurnPriceTrend35On);
		String keychurnPriceTrend35 = "churnPriceTrend35";
		float churnPriceTrend35 = MarketIndexParametersDB.getFloatValue(m_con, m_indexParametersDBName, keychurnPriceTrend35);
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
			 * 4. priceClose must be greater than or equal to previous day
			 * 5. volume must be greater than avg daily
			 * 6. price must be on upswing over  previous 35 days
			 */
			
			// {{ pulling variables from List, just to make the code below prettier
			float todaysHigh = m_analysisRows.get(i).getHigh();			
			float previousDaysHigh = m_analysisRows.get(i-1).getHigh();
			
			float todaysLow = m_analysisRows.get(i).getLow();			
			float previousDaysLow = m_analysisRows.get(i-1).getLow();
			
			float todaysClose = m_analysisRows.get(i).getClose();
			float previousDaysClose = m_analysisRows.get(i-1).getClose();
			
			long todaysVolume = m_analysisRows.get(i).getVolume();
			long previousDaysVolume = m_analysisRows.get(i-1).getVolume();
			
			long todaysVolumeAvg50 = m_analysisRows.get(i).getVolumeAvg50();
			
			float todaysPriceTrend35 = m_analysisRows.get(i).getPriceTrend35();
			
			// }}
			
			
			if( todaysClose < (todaysHigh + todaysLow)/2 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose <= previousDaysClose*(1+churnPriceRange) /*rule 3*/)
			{
				churningDayCount++;
				m_analysisRows.get(i).setChurnDay(true);
				//MarketIndexAnalysisDB.addDDayStatus(ps, m_analysisRows.get(i).getPVD_id(), true);
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
				
				if(conditionsRequired == conditionsMet)
					m_analysisRows.get(i).setChurnDay(true);
			}
			//No need set to false because it was already done by the d-day method.
		}
	}
	
	public static void countDDaysInWindow(int dDayWindow) {
		System.out.println("          Looking at each day to see how many D-Dates at in the current window (" + dDayWindow + " days).");
		
		/* 
		 * 		2. For each loop of all the data
		 * 		3. As the loop progresses through each row, look back in the data the number of days in the window
		 * 			and see how many d-days there are
		 * 		4. Write the results to the database 
		*/

		//This list starts with the newest date, which means the loop is goes back in time with each iteration 
		for(int i=m_analysisRows.size()-1; i>0; i--) {

			for(int j=i; j>i-dDayWindow && j>0; j--) { //This loop starts at i and then goes back dDayWindow days adding up all the d days

				if(m_analysisRows.get(j).isDDay() || m_analysisRows.get(j).isChurnDay())
					m_analysisRows.get(i).addDDayCounter();
			}
		}
	}

	private static void followThruAnalysis() {
		System.out.println("          Checking to see if each day is a Follow Through Day");
		
		int rowCount = m_analysisRows.size();
		int followThroughDayCount=0;
		
		// {{ Getting variables from the parameter database
		String keyrDaysMax = "rDaysMax";
		int rDaysMax = MarketIndexParametersDB.getIntValue(m_con, m_indexParametersDBName, keyrDaysMax);
		String keypivotTrend35On = "pivotTrend35On";
		boolean churnpivotTrend35On = MarketIndexParametersDB.getBooleanValue(m_con, m_indexParametersDBName, keypivotTrend35On);
		String keypivotTrend35 = "pivotTrend35";
		float pivotTrend35 = MarketIndexParametersDB.getFloatValue(m_con, m_indexParametersDBName, keypivotTrend35);
		// }}
		
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{
			/*
			 * this part gets followthrough days
			 * 
			 */
			float rallyPriceHigh = 0;
			
		}
	}
}
