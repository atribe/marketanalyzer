package com.ar.marketanalyzer.backtest.models.rules;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsFollowThru;
import com.ar.marketanalyzer.backtest.models.stats.FollowThruStats;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;

@Entity
@DiscriminatorValue("Sell:D-Days And Churn Days")
public class RuleSellDDaysAndChurnDays extends AbstractRule {

	private static final long serialVersionUID = 8090046696312115053L;

	/*
	 * Fields specific to this rule
	 */
	
	/*
	 * Fields that are rule parameters
	 */
	// Sell Triggers
	@Transient
	int ddayCountSellTrigger;
	@Transient
	final static String DDAY_COUNT_SELL_TRIGGER = "d_day_count_sell_trigger";
	@Transient
	int ddayWindow;
	@Transient
	final static String DDAY_WINDOW = "d_day_window";
	
	// D Days
	@Transient
	double priceDrop;
	@Transient
	final static String PRICE_DROP = "priceDrop";
	
	// Churn Days
	@Transient
	double churnVolRange;
	@Transient
	final static String CHURN_VOL_RANGE = "churnVolRange";
	@Transient
	double churnPriceRange;
	@Transient
	final static String CHURN_PRICE_RANGE = "churnPriceRange";
	@Transient
	boolean churnPriceCloseHigherOn;
	@Transient
	final static String CHURN_PRICE_CLOSE_HIGHER = "churnPriceCloseHigher";
	@Transient
	boolean churnAvg50;
	@Transient
	final static String CHURN_AVG_50_ON = "churnAvg50On";
	@Transient
	boolean churnPriceTrend35On;
	@Transient
	final static String CHURN_PRICE_TREND_35_ON = "churnPriceTrend35On";
	@Transient
	double churnPriceTrend35;
	@Transient
	final static String CHURN_PRICE_TREND_35 = "churnPriceTrend35";
	
	/*
	 * Constructors
	 */
	public RuleSellDDaysAndChurnDays() {
		super();
	}
	public RuleSellDDaysAndChurnDays(AbstractModel model) {
		super(model, RuleType.SELL);
		
		if( !parametersAlreadyExist() ) {
			setDefaultParameters();
		}
	}
	
	@Override
	public void setDefaultParameters() {
		/*
		 * Setting default values for rule parameters
		 */
		// Sell Triggers
		ddayCountSellTrigger = 7;
		ddayWindow = 20;
		
		// D Days
		priceDrop=-.002;
		
		// Churn Days
		churnVolRange=.03;
		churnPriceRange=.02;
		churnPriceCloseHigherOn=false;
		churnAvg50=false;
		churnPriceTrend35On=false;
		churnPriceTrend35=.009;

		/*
		 * Adding parameters to ruleParameter list
		 * Any parameters added to this list will be saved in the DB
		 */
		ruleParameters.add(new RuleParameter(DDAY_WINDOW, ddayWindow));
		ruleParameters.add(new RuleParameter(DDAY_COUNT_SELL_TRIGGER, ddayCountSellTrigger));
		ruleParameters.add(new RuleParameter(PRICE_DROP, priceDrop));
		ruleParameters.add(new RuleParameter(CHURN_VOL_RANGE, churnVolRange));
		ruleParameters.add(new RuleParameter(CHURN_PRICE_RANGE, churnPriceRange));
		ruleParameters.add(new RuleParameter(CHURN_PRICE_CLOSE_HIGHER, churnPriceCloseHigherOn));
		ruleParameters.add(new RuleParameter(CHURN_AVG_50_ON, churnAvg50));
		ruleParameters.add(new RuleParameter(CHURN_PRICE_TREND_35_ON, churnPriceTrend35On));
		ruleParameters.add(new RuleParameter(CHURN_PRICE_TREND_35, churnPriceTrend35));
	}
	
	@Override
	public void runRule() {
		/*
		 * 1. Create a List<RuleResult> of D-days (RuleResult is a class with date and Boolean)
		 * 2. Create a List<RuleResult> of Churn days
		 * 3. Combine the two into the official List<RuleResult> for this rule
		 */
		initializeRuleResultList();
		
		runDdayAnalysis(); // sets ddays list
		
		runChurnDayAnalysis(); // sets churn day list
		
		countDDaysInWindow();
		
		findTriggerDays();
	}
	
	@Override
	protected void initializeRuleResultList() {
		SortedMap<Date, SecuritiesOhlcv> ohlcvData = (TreeMap<Date, SecuritiesOhlcv>)currentModel.getOhlcvData();
		
		for(Map.Entry<Date, SecuritiesOhlcv> ohlcv: ohlcvData.entrySet()) {
			ruleResult.put(ohlcv.getKey(), new RuleResultsDDaysAndChurnDays(ohlcv.getKey()));
		}
	}
	
	private void runDdayAnalysis() {
		SortedMap<Date, SecuritiesOhlcv> ohlcvData = (TreeMap<Date, SecuritiesOhlcv>)currentModel.getOhlcvData();
		
		ArrayList<Date> keys = new ArrayList<Date>(ohlcvData.keySet());
		for(int i = 1; i< keys.size(); i++) { //Starting at i=1 so that i can use i-1 in the first calculation 
			/*
			 * D day rules
			 * 1. Volume Higher than the previous day
			 * 2. Price drops by X% (IBD states .2%)
			 */
			
			// {{ pulling variables from List
			long todaysVolume = ohlcvData.get(keys.get(i)).getVolume();
			long previousDaysVolume = ohlcvData.get(keys.get(i-1)).getVolume();
			
			BigDecimal todaysClose = ohlcvData.get(keys.get(i)).getClose();
			BigDecimal previousDaysClose = ohlcvData.get(keys.get(i-1)).getClose();

			double closePercentChange = (todaysClose.doubleValue()/previousDaysClose.doubleValue()-1);
			// }}
			
			RuleResultsDDaysAndChurnDays result = (RuleResultsDDaysAndChurnDays) ruleResult.get(keys.get(i));
			if( todaysVolume > previousDaysVolume /*This is rule #1*/ && closePercentChange < priceDrop /*This is rule #1*/)
			{
				result.setDday(Boolean.TRUE);
			}
			else
			{
				result.setDday(Boolean.FALSE);
			}
			ruleResult.put(keys.get(i), result);
		}
	}
	
	private void runChurnDayAnalysis() {
		SortedMap<Date, SecuritiesOhlcv> ohlcvData = (TreeMap<Date, SecuritiesOhlcv>)currentModel.getOhlcvData();
		SortedMap<Date, FollowThruStats> stats = currentModel.getStats();
		
		ArrayList<Date> keys = new ArrayList<Date>(ohlcvData.keySet());
		for(int i = 1; i< keys.size(); i++) { //Starting at i=1 so that i can use i-1 in the first calculation 
		
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
			
			RuleResultsDDaysAndChurnDays result = (RuleResultsDDaysAndChurnDays) ruleResult.get(i);
			
			// {{ pulling variables from List, just to make the code below prettier
			BigDecimal todaysHigh = ohlcvData.get(keys.get(i)).getHigh();			
			//double previousDaysHigh = ohlcvData.get(i-1).getHigh();
			
			BigDecimal todaysLow = ohlcvData.get(keys.get(i)).getLow();			
			//double previousDaysLow = ohlcvData.get(i-1).getLow();
			
			BigDecimal todaysClose = ohlcvData.get(keys.get(i)).getClose();
			BigDecimal previousDaysClose = ohlcvData.get(keys.get(i-1)).getClose();
			
			long todaysVolume = ohlcvData.get(keys.get(i)).getVolume();
			long previousDaysVolume = ohlcvData.get(keys.get(i-1)).getVolume();
			
			long todaysVolumeAvg50 = stats.get(keys.get(i)).getVol50DayAvg();
			
			double todaysPriceTrend35 = stats.get(keys.get(i)).getPriceTrend35();
			
			if( todaysClose.compareTo(todaysHigh.add(todaysLow).divide(new BigDecimal(2))) < 0 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose.compareTo(previousDaysClose.multiply(new BigDecimal(1+churnPriceRange))) <= 0/*rule 3*/)
			{
				result.setChurnDay(Boolean.TRUE);
				//MarketIndexAnalysisDB.addDDayStatus(ps, ohlcvData.get(i).getPVD_id(), true);
			} else {
				// {{ Churn day conditions set by the parameter db
				int conditionsRequired = 0;
				int conditionsMet = 0;
				if (churnPriceCloseHigherOn)
					conditionsRequired++;
				if (churnAvg50)
					conditionsRequired++;
				if (churnPriceTrend35On)
					conditionsRequired++;
				// }}
				
				if(churnPriceCloseHigherOn && todaysClose.compareTo(previousDaysClose) >= 0) //rule 4
					conditionsMet++;
								 
				if(churnAvg50 && todaysVolume > todaysVolumeAvg50 ) //rule 5
					conditionsMet++;
				
				if(churnPriceTrend35On && todaysPriceTrend35 > churnPriceTrend35) //rule 6
					conditionsMet++;
				
				if(conditionsRequired == conditionsMet && conditionsRequired != 0)
				{
					result.setChurnDay(Boolean.TRUE);
				} else {
					result.setChurnDay(Boolean.FALSE);
				}
			}
			ruleResult.put(keys.get(i), result);
		}
	}

	private void countDDaysInWindow() {
		/* 
		 * 		2. For each loop of all the data
		 * 		3. As the loop progresses through each row, look back in the data the number of days in the window
		 * 			and see how many d-days there are
		 * 		4. Write the results to the database 
		*/
		
		//This list starts with the newest date, which means the loop is goes back in time with each iteration 
		ArrayList<Date> keys = new ArrayList<Date>(ruleResult.keySet());
		for(int i = keys.size()-1; i>0; i--) {
			
			int dDayCount=0;
			
			RuleResultsDDaysAndChurnDays result = (RuleResultsDDaysAndChurnDays) ruleResult.get(keys.get(i));
			
			for(int j=i; j>i-ddayWindow && j>0; j--) { //This loop starts at i and then goes back dDayWindow days adding up all the d days
				RuleResultsDDaysAndChurnDays innerResult = (RuleResultsDDaysAndChurnDays) ruleResult.get(keys.get(j));
				if( Boolean.TRUE.equals(innerResult.getDday()) || Boolean.TRUE.equals(innerResult.getChurnDay()) ) {
					dDayCount++;
				}
			}
			
			result.setDdaysInWindow(dDayCount);
			ruleResult.put(keys.get(i), result);
		}
	}

	@Override
	protected void findTriggerDays() {
		/*
		 * The DDay and Churn rule determines sell dates by when the ddays+churndays > ddayCountSellTrigger
		 */
		for(Map.Entry<Date,AbstractRuleResult> entry : ruleResult.entrySet()) {
			RuleResultsDDaysAndChurnDays result = (RuleResultsDDaysAndChurnDays) entry.getValue();
			if(result.getDdaysInWindow()>=ddayCountSellTrigger) {
				result.setRuleResult(Boolean.TRUE);
			} else {
				result.setRuleResult(Boolean.FALSE);
			}
		}
	}
}
