package com.ar.marketanalyzer.backtest.models.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsDDaysAndChurnDays;
import com.ar.marketanalyzer.backtest.models.stats.FollowThruStats;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;

@Entity
@DiscriminatorValue("Sell:D-Days And Churn Days")
public class RuleSellDDaysAndChurnDays extends AbstractRule {

	private static final long serialVersionUID = 8090046696312115053L;

	/*
	 * Fields specific to this rule
	 */
	@Transient
	private List<RuleResultsDDaysAndChurnDays> ddaysResults = new ArrayList<RuleResultsDDaysAndChurnDays>();

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
		runDdayAnalysis(); // sets ddays list
		
		runChurnDayAnalysis(); // sets churn day list
		
		countDDaysInWindow();
		
		setSellDates();
	}
	
	private void runDdayAnalysis() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		
		for(int i = 1; i < ohlcvData.size(); i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{ 
			/*
			 * D day rules
			 * 1. Volume Higher than the previous day
			 * 2. Price drops by X% (IBD states .2%)
			 */
			
			RuleResultsDDaysAndChurnDays result =  new RuleResultsDDaysAndChurnDays(ohlcvData.get(i).getDate() );
			ddaysResults.add(result);
			
			// {{ pulling variables from List
			long todaysVolume = ohlcvData.get(i).getVolume();
			long previousDaysVolume = ohlcvData.get(i-1).getVolume();
			
			BigDecimal todaysClose = ohlcvData.get(i).getClose();
			BigDecimal previousDaysClose = ohlcvData.get(i-1).getClose();

			double closePercentChange = (todaysClose.doubleValue()/previousDaysClose.doubleValue()-1);
			// }}
			
			if( todaysVolume > previousDaysVolume /*This is rule #1*/ && closePercentChange < priceDrop /*This is rule #1*/)
			{
				ddaysResults.get(i).setDday(Boolean.TRUE);
			}
			else
			{
				ddaysResults.get(i).setDday(Boolean.FALSE);
			}
		}
	}
	
	private void runChurnDayAnalysis() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		List<FollowThruStats> stats = currentModel.getStats();
		int rowCount = ohlcvData.size();
		
		for(int i = 1; i < rowCount-1; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
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
			BigDecimal todaysHigh = ohlcvData.get(i).getHigh();			
			//double previousDaysHigh = ohlcvData.get(i-1).getHigh();
			
			BigDecimal todaysLow = ohlcvData.get(i).getLow();			
			//double previousDaysLow = ohlcvData.get(i-1).getLow();
			
			BigDecimal todaysClose = ohlcvData.get(i).getClose();
			BigDecimal previousDaysClose = ohlcvData.get(i-1).getClose();
			
			long todaysVolume = ohlcvData.get(i).getVolume();
			long previousDaysVolume = ohlcvData.get(i-1).getVolume();
			
			long todaysVolumeAvg50 = stats.get(i).getVol50DayAvg();
			
			double todaysPriceTrend35 = stats.get(i).getPriceTrend35();
			
			if( todaysClose.compareTo(todaysHigh.add(todaysLow).divide(new BigDecimal(2))) < 0 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose.compareTo(previousDaysClose.multiply(new BigDecimal(1+churnPriceRange))) <= 0/*rule 3*/)
			{
				ddaysResults.get(i).setChurnDay(Boolean.TRUE);
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
					ddaysResults.get(i).setChurnDay(Boolean.TRUE);
				} else {
					ddaysResults.get(i).setChurnDay(Boolean.FALSE);
				}
			}
		}
	}

	private void setSellDates() {
		/*
		 * 
		 */
		for( RuleResultsDDaysAndChurnDays result: ddaysResults) {
			if(result.getDdaysInWindow() >= ddayCountSellTrigger) {
				result.setRuleResult(Boolean.TRUE);
			} else {
				result.setRuleResult(Boolean.FALSE);
			}
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
		for(int i=ddaysResults.size(); i>0; i--) {
			
			int dDayCount=0;
			
			for(int j=i; j>i-ddayWindow && j>0; j--) { //This loop starts at i and then goes back dDayWindow days adding up all the d days
				
				if( Boolean.TRUE.equals(ddaysResults.get(j).getDday()) || Boolean.TRUE.equals(ddaysResults.get(j).getChurnDay()) ) {
					dDayCount++;
				}
			}
			
			ddaysResults.get(i).setDdaysInWindow(dDayCount);
		}
	}
}
