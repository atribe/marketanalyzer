package com.ar.marketanalyzer.backtest.models.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleResult;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
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
	private List<RuleResult> ddays = new ArrayList<RuleResult>();
	@Transient
	private List<RuleResult> churnDays = new ArrayList<RuleResult>();
	
	/*
	 * Fields that are rule parameters
	 */
	// D Days
	double priceDrop;
	
	// Churn Days
	double churnVolRange;
	double churnPriceRange;
	boolean churnPriceCloseHigherOn;
	boolean churnAVG50On;
	boolean churnPriceTrend35On;
	double churnPriceTrend35;
	
	/*
	 * Constructors
	 */
	public RuleSellDDaysAndChurnDays() {
		super();
	}
	public RuleSellDDaysAndChurnDays(AbstractModel model) {
		super(model, RuleType.SELL);
	}
	
	@Override
	public void setDefaultParameters() {
		
		
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
	}
	
	private void runDdayAnalysis() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		int rowCount = ohlcvData.size();
		
		for(int i = 1; i < rowCount; i++) //Starting at i=1 so that i can use i-1 in the first calculation 
		{ 
			/*
			 * D day rules
			 * 1. Volume Higher than the previous day
			 * 2. Price drops by X% (IBD states .2%)
			 */
			
			// {{ pulling variables from List
			long todaysVolume = ohlcvData.get(i).getVolume();
			long previousDaysVolume = ohlcvData.get(i-1).getVolume();
			
			BigDecimal todaysClose = ohlcvData.get(i).getClose();
			BigDecimal previousDaysClose = ohlcvData.get(i-1).getClose();

			double closePercentChange = (todaysClose.doubleValue()/previousDaysClose.doubleValue()-1);
			// }}
			
			if( todaysVolume > previousDaysVolume /*This is rule #1*/ && closePercentChange < priceDrop /*This is rule #1*/)
			{
				ddays.add( new RuleResult(ohlcvData.get(i).getDate(), Boolean.TRUE ) );
			}
			else
			{
				ddays.add( new RuleResult(ohlcvData.get(i).getDate(), Boolean.FALSE ) );
			}
		}
	}
	
	private void runChurnDayAnalysis() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		List<FollowThruStats> stats = currentModel.getStats();
		int rowCount = ohlcvData.size();
		
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
			
			//Creating a result that is by default set to false
			RuleResult result = new RuleResult(ohlcvData.get(i).getDate(), Boolean.FALSE);
			// }}
			
			
			if( todaysClose.compareTo(todaysHigh.add(todaysLow).divide(new BigDecimal(2))) < 0 /*rule 1*/ &&
					todaysVolume >= previousDaysVolume*(1-churnVolRange) /*rule 2a*/ &&
					todaysVolume <= previousDaysVolume*(1+churnVolRange) /*rule 2b*/ &&
					todaysClose.compareTo(previousDaysClose.multiply(new BigDecimal(1+churnPriceRange))) <= 0/*rule 3*/)
			{
				result.setRuleResult(Boolean.TRUE);
				churnDays.set(i, result);
				//MarketIndexAnalysisDB.addDDayStatus(ps, ohlcvData.get(i).getPVD_id(), true);
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
					result.setRuleResult(Boolean.TRUE);
					churnDays.set(i, result);
				} else {
					result.setRuleResult(Boolean.FALSE);
					churnDays.set(i, result);
				}
			}
			//No need set to false because it was already done by the d-day method.
		}
	}
}
