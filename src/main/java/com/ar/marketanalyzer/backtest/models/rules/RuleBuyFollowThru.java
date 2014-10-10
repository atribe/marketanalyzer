package com.ar.marketanalyzer.backtest.models.rules;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.models.enums.RuleType;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.backtest.models.ruleresults.RuleResultsFollowThru;
import com.ar.marketanalyzer.backtest.models.stats.FollowThruStats;
import com.ar.marketanalyzer.core.securities.models.SecuritiesOhlcv;

@Entity
@DiscriminatorValue("Buy:Follow Thru")
public class RuleBuyFollowThru extends AbstractRule {

	private static final long serialVersionUID = 6794964611277097413L;
	/*
	 * Fields specific to this rule
	 */
	
	/*
	 * Fields that are rule parameters
	 */
	
	// Pivot days
	@Transient
	private boolean churnPivotTrend35On;
	@Transient
	private final static String CHURN_PIVOT_TREND_35_ON = "churnPivotTrend35On";
	@Transient
	private double pivotTrend35;
	@Transient
	private final static String PIVOT_TREND_35 = "pivotTrend35";
	@Transient
	private int rDaysMin;
	@Transient
	private final static String R_DAYS_MIN = "rDaysMin";
	
	// Follow thru day
	@Transient
	private int rDaysMax;
	@Transient
	private final static String R_DAYS_MAX = "rDaysMax";
	@Transient
	private double priceMult;
	@Transient
	private final static String PRICE_MULT = "priceMult";
	@Transient
	private double volMult;
	@Transient
	private final static String VOL_MULT = "volMult";
	
	/*
	 * Constructors
	 */
	public RuleBuyFollowThru() {
		super();
	}
	public RuleBuyFollowThru(AbstractModel model) {
		super(model, RuleType.BUY);
		
		if( !parametersAlreadyExist() ) {
			setDefaultParameters();
		}
	}

	@Override
	public void setDefaultParameters() {
		/*
		 * Setting default values for rule parameters
		 */
		// Pivot days
		
		churnPivotTrend35On = false;
		pivotTrend35 = -0.004;
		rDaysMin = 3;
		
		// Follow thru day
		rDaysMax = 8;
		priceMult = 1.007;
		volMult = 1.11;
		
		/*
		 * Adding parameters to ruleParameter list
		 * Any parameters added to this list will be saved in the DB
		 */
		ruleParameters.add(new RuleParameter(CHURN_PIVOT_TREND_35_ON, churnPivotTrend35On));
		ruleParameters.add(new RuleParameter(PIVOT_TREND_35, pivotTrend35));
		ruleParameters.add(new RuleParameter(R_DAYS_MIN, rDaysMin));
		ruleParameters.add(new RuleParameter(R_DAYS_MAX, rDaysMax));
		ruleParameters.add(new RuleParameter(PRICE_MULT, priceMult));
		ruleParameters.add(new RuleParameter(VOL_MULT, volMult));			
	}
	
	@Override
	public void runRule() {
		initializeRuleResultList();
		
		findPivotDays();
		
		findFollowThruDays();
		
		findTriggerDays();
	}
	
	@Override
	protected void initializeRuleResultList() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		
		for(SecuritiesOhlcv ohlcv: ohlcvData) {
			ruleResult.add(new RuleResultsFollowThru(ohlcv.getDate()));
		}
	}
	
	private void findPivotDays() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();
		List<FollowThruStats> stats = currentModel.getStats();
		
		/*
		 * Deffinition: PivotDay = first day of a rally
		 */
		boolean potentialPivotDay = false;
		

		
		/*
		 * Optional criteria: rally can't start unless the pivotTrend35 < -.1%
		 * I'm not going to implement this right away
		 */

		//Loops starts on 2 because the object at i-2 is accessed
		for(int i = 2; i< ohlcvData.size(); i++) {
			//Initializing variables for this loop run
			potentialPivotDay = false;
			BigDecimal todaysClose = ohlcvData.get(i).getClose();
			BigDecimal previousClose = ohlcvData.get(i-1).getClose();
			BigDecimal previousPreviousClose = ohlcvData.get(i-2).getClose();
			
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
				double todaysPivotTrend35 = stats.get(i).getPriceTrend35();
				
				if(!churnPivotTrend35On) { //if churnPivotTrend35 is turned off then today is a potential pivotDay because it met the criteria in the if statement above
					potentialPivotDay = true;
				} else if( churnPivotTrend35On && todaysPivotTrend35 < pivotTrend35) { //if churnPivotTrend35 is on and pivotTrend35 criteria is met then the day is still a potential pivot day
					potentialPivotDay = true;
				} else { //if churnPivotTrend35 is on and pivotTrend35 criteria is not met then the day is no longer a potential pivot day
					potentialPivotDay = false;
				}
				
				//if the day is still a potentialPivotDay after the additional criteria then check to see if the rally achieves the minimum of rDaysMin days (typically 4)
				if( potentialPivotDay ) {
					 BigDecimal support = ohlcvData.get(i).getLow();//if price goes below support the rally is broken
					
					/*
					 * Loop starts at the 2nd day in the rally from the potential pivot day (i+1)
					 * 
					 * Loop checks for rally atleast rDaysMin long. at rDaysMin is the soonest a follow thru day could occur
					 */
					for(int j = i + 1; j < i + rDaysMin && j < ohlcvData.size(); j++) {
						BigDecimal nextDayInRallyLow = ohlcvData.get(j).getLow();
						if( nextDayInRallyLow.compareTo(support) < 0) {
							//not a rally
							potentialPivotDay = false;
							break;
						}
					}
				}
			}
			
			if(potentialPivotDay == true) {
				RuleResultsFollowThru result = (RuleResultsFollowThru) ruleResult.get(i);
				result.setPivotDay( Boolean.TRUE );
				ruleResult.set(i, result);
			} else {
				RuleResultsFollowThru result = (RuleResultsFollowThru) ruleResult.get(i);
				result.setPivotDay( Boolean.FALSE );
				ruleResult.set(i, result);
			}
			
		}
	}
	
	private void findFollowThruDays() {
		List<SecuritiesOhlcv> ohlcvData = currentModel.getOhlcvData();

		//loop starts at 2 because i-2 is accessed
		for(int i = 2; i < ohlcvData.size(); i++) { //Starting at i=1 so that i can use i-1 in the first calculation 
			
			//if the day is a pivot day, as set by the findPivotDay method then...
			RuleResultsFollowThru result = (RuleResultsFollowThru) ruleResult.get(i);
			if( result.getPivotDay() ) {
				checkPivotDay(ohlcvData, i);
			}
			
			if(result.getFollowThruDay() == null) {
				result.setFollowThruDay(Boolean.FALSE);
			}
		}
	}
	
	private void checkPivotDay (List<SecuritiesOhlcv> ohlcvData, int i) {
		BigDecimal rallyHigh = ohlcvData.get(i).getHigh(); 
		BigDecimal support = ohlcvData.get(i).getLow();
		
		int rallyDayCount = 1;
		/*
		 * When j = i that is the the pivot day
		 * Loop goes until rDaysMax days have been checked for a follow 	thru day
		 * or a follow thru day has been found
		 */
		for(int j = i; j < i + rDaysMax && j < ohlcvData.size() && j < ruleResult.size(); j++) {
			RuleResultsFollowThru result = (RuleResultsFollowThru) ruleResult.get(j);
			
			//Checking to see if a new high for the rally has been set has been set
			BigDecimal todaysHigh = ohlcvData.get(j).getHigh();
			if( todaysHigh.compareTo(rallyHigh) > 0) {
				rallyHigh = todaysHigh;
			}
			
			BigDecimal todaysClose = ohlcvData.get(j).getClose();
			BigDecimal previousClose = ohlcvData.get(j-1).getClose();
			
			BigDecimal todaysLow = ohlcvData.get(j).getLow();
			
			long todaysVolume = ohlcvData.get(j).getVolume();
			long previousVolume = ohlcvData.get(j-1).getVolume();
			long previousPreviousVolume = ohlcvData.get(j-2).getVolume();
			
			if( todaysClose.compareTo(previousClose.multiply(new BigDecimal(priceMult))) > 0 && //price requirement 
					( todaysVolume > previousVolume * volMult || todaysVolume > previousPreviousVolume ) && //volume requirement
					rallyDayCount > rDaysMin && //follow thru days from pivot day requirement 1
					rallyDayCount < rDaysMax) { //follow thru days from pivot day requirement 2
				//if the above conditions are true the day is a follow thru day
				
				result.setFollowThruDay(Boolean.TRUE);
				ruleResult.set(j, result);
				//and end checking conditions to see if the day is a follow thru day
				break;
			} else if( todaysLow.compareTo(support) < 0) { //if the low drops below the support the rally is over and the day is not a follow thru day
				if( result.getFollowThruDay() == null || !result.getFollowThruDay() ) {	// This prevents overriding a previously set true value
					result.setFollowThruDay(Boolean.FALSE);
				}
				ruleResult.set(j, result);
				break;
			} else { //the day must not 
				if( result.getFollowThruDay() == null || !result.getFollowThruDay() ) { // This prevents overriding a previously set true value
					result.setFollowThruDay(Boolean.FALSE);
				}
			}
			ruleResult.set(j, result);
			rallyDayCount++;
		}
	}
	
	@Override
	protected void findTriggerDays() {
		
		for(int i=0; i<ruleResult.size(); i++) {
			RuleResultsFollowThru result = (RuleResultsFollowThru) ruleResult.get(i);
			if(result.getFollowThruDay()!=null && result.getFollowThruDay()) {
				result.setRuleResult(Boolean.TRUE);
			} else {
				result.setRuleResult(Boolean.FALSE);
			}
		}
	}
}
