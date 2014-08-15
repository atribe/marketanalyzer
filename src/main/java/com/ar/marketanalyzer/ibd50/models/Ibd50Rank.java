package com.ar.marketanalyzer.ibd50.models;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.ar.marketanalyzer.ibd50.models.parents.PersistableEntity;
import com.ar.marketanalyzer.securities.models.Symbol;

@Entity
@Table(name = "IBD50_RANKING")
public class Ibd50Rank extends PersistableEntity {
		
	private static final long serialVersionUID = 5791875306977524480L;

	@Column(name="rank_date", nullable=false)
	private Date rankDate;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private Symbol ticker;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="tracking_id", referencedColumnName="id")
	private Ibd50Tracking tracker;
	
	@Column(nullable=false)
	private Integer rank;
	
	@Column(name="current_price", nullable=false, precision=10, scale=2)
	private BigDecimal currentPrice;
	
	@Column(name="price_change", nullable=false, precision=10, scale=2)
	private BigDecimal priceChange;
	
	@Column(name="price_percent_change")
	private Double pricePercentChange;
	
	@Column(name="percent_off_high")
	private Double percentOffHigh;
	
	@Column
	private Long volume; //need to add 000 to this number
	
	@Column(name="volume_percent_change")
	private Double volumePercentChange;
	
	@Column(name="composite_rating")
	private Double compositeRating;
	
	@Column(name="eps_rating")
	private Double epsRating;
	
	@Column(name="rs_rating")
	private Double rsRating;
	
	@Column(name="smr_rating")
	private String smrRating;
	
	@Column(name="acc_dis_rating")
	private String accDisRating;
	
	@Column(name="group_relative_strength_rating")
	private String groupRelStrRating;
	
	@Column(name="eps_percent_change_last_qtr")
	private Double epsPercentChangeLastQtr;
	
	@Column(name="eps_percent_change_prior_qtr")
	private Double epsPercentChangePriorQtr;
	
	@Column(name="eps_percent_change_current_qtr")
	private Double epsPercentChangeCurrentQtr;
	
	@Column(name="eps_estimated_percent_change_current_year")
	private Double epsEstPercentChangeCurrentYear;
	
	@Column(name="sale_percent_change_last_qtr")
	private Double salesPercentChangeLastQtr;
	
	@Column(name="annual_roe_last_year")
	private Double annualROELastYear;
	
	@Column(name="annual_profit_margin_latest_year")
	private Double annualProfitMarginLatestYear;
	
	@Column(name="management_own_percent")
	private Double managmentOwnPercent;
	
	@Column(name="qtrs_rising_sponsorship")
	private Integer qtrsRisingSponsorship;
	
	@Column(name="current_ranking")
	private Boolean activeRanking;
	
	@OneToMany(mappedBy = "ranking",cascade = CascadeType.ALL)
	private Collection<Ibd50IndexShares> shareCounts;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_time", nullable = false)
	private Date creationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modification_time", nullable = false)
	private Date modificationTime;
	
	@PreUpdate
    public void preUpdate() {
        modificationTime = new LocalDateTime().toDate();
    }
     
    @PrePersist
    public void prePersist() {
        Date now = new LocalDateTime().toDate();
        creationTime = now;
        modificationTime = now;
    }
    
    public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}
	public LocalDate getLocalDateModificationTime() {
		return new LocalDate(modificationTime);
	}
	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}
	
	/*
	 * Constructor
	 */
	public Ibd50Rank() {
		rankDate =  findMondayRankDate(); //aka today
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Collection<Ibd50IndexShares> getShareCounts() {
		return shareCounts;
	}

	public void setShareCounts(Collection<Ibd50IndexShares> shareCounts) {
		this.shareCounts = shareCounts;
	}

	private Date findMondayRankDate() {
		LocalDate today = new LocalDate();
		
		LocalDate dateToReturn = null;
		
		if(today.dayOfWeek().equals(DateTimeConstants.MONDAY)) {
			dateToReturn = today;
		} else {
			dateToReturn = today.withDayOfWeek(1);
		}
		
		return dateToReturn.toDate();
	}
	
	/*
	 * Getters and Setters
	 */
	public Date getRankDate() {
		return rankDate;
	}
	public LocalDate getLocalDateRankDate() {
		return new LocalDate(rankDate);
	}
	public void setRankDate(Date rankDate) {
		this.rankDate = rankDate;
	}
	public Ibd50Tracking getTracker() {
		return tracker;
	}
	public void setTracker(Ibd50Tracking tracker) {
		this.tracker = tracker;
	}
	public Symbol getTicker() {
		return ticker;
	}
	public void setTicker(Symbol ticker) {
		this.ticker = ticker;
	}
	public String getSymbol() {
		return ticker.getSymbol();
	}
	public void setSymbol(String symbol) {
		ticker.setSymbol(symbol);
	}
	public String getCompanyName() {
		return ticker.getName();
	}
	public void setCompanyName(String companyName) {
		ticker.setName(companyName);
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public void setRank(int rank) {
		this.rank = new Integer(rank);
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getPriceChange() {
		return priceChange;
	}
	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}
	public Double getPricePercentChange() {
		return pricePercentChange;
	}
	public void setPricePercentChange(Double pricePercentChange) {
		this.pricePercentChange = pricePercentChange;
	}
	public void setPricePercentChange(double pricePercentChange) {
		this.pricePercentChange = new Double(pricePercentChange);
	}
	public Double getPercentOffHigh() {
		return percentOffHigh;
	}
	public void setPercentOffHigh(Double percentOffHigh) {
		this.percentOffHigh = percentOffHigh;
	}
	public void setPercentOffHigh(double percentOffHigh) {
		this.percentOffHigh = new Double(percentOffHigh);
	}
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	public void setVolume(long volume) {
		this.volume = new Long(volume);
	}
	public Double getVolumePercentChange() {
		return volumePercentChange;
	}
	public void setVolumePercentChange(Double volumePercentChange) {
		this.volumePercentChange = volumePercentChange;
	}
	public void setVolumePercentChange(double volumePercentChange) {
		this.volumePercentChange = new Double(volumePercentChange);
	}
	public Double getCompositeRating() {
		return compositeRating;
	}
	public void setCompositeRating(Double compositeRating) {
		this.compositeRating = compositeRating;
	}
	public void setCompositeRating(double compositeRating) {
		this.compositeRating = new Double(compositeRating);
	}
	public Double getEpsRating() {
		return epsRating;
	}
	public void setEpsRating(Double epsRating) {
		this.epsRating = epsRating;
	}
	public void setEpsRating(double epsRating) {
		this.epsRating = new Double(epsRating);
	}
	public Double getRsRating() {
		return rsRating;
	}
	public void setRsRating(Double rsRating) {
		this.rsRating = rsRating;
	}
	public void setRsRating(double rsRating) {
		this.rsRating = new Double(rsRating);
	}
	public String getSmrRating() {
		return smrRating;
	}
	public void setSmrRating(String smrRating) {
		this.smrRating = smrRating;
	}
	public String getAccDisRating() {
		return accDisRating;
	}
	public void setAccDisRating(String accDisRating) {
		this.accDisRating = accDisRating;
	}
	public String getGroupRelStrRating() {
		return groupRelStrRating;
	}
	public void setGroupRelStrRating(String groupRelStrRating) {
		this.groupRelStrRating = groupRelStrRating;
	}
	public Double getEpsPercentChangeLastQtr() {
		return epsPercentChangeLastQtr;
	}
	public void setEpsPercentChangeLastQtr(Double epsPercentChangeLastQtr) {
		this.epsPercentChangeLastQtr = epsPercentChangeLastQtr;
	}
	public void setEpsPercentChangeLastQtr(double epsPercentChangeLastQtr) {
		this.epsPercentChangeLastQtr = new Double(epsPercentChangeLastQtr);
	}
	public Double getEpsPercentChangePriorQtr() {
		return epsPercentChangePriorQtr;
	}
	public void setEpsPercentChangePriorQtr(Double epsPercentChangePriorQtr) {
		this.epsPercentChangePriorQtr = epsPercentChangePriorQtr;
	}
	public void setEpsPercentChangePriorQtr(double epsPercentChangePriorQtr) {
		this.epsPercentChangePriorQtr = new Double(epsPercentChangePriorQtr);
	}
	public Double getEpsPercentChangeCurrentQtr() {
		return epsPercentChangeCurrentQtr;
	}
	public void setEpsPercentChangeCurrentQtr(Double epsPercentChangeCurrentQtr) {
		this.epsPercentChangeCurrentQtr = epsPercentChangeCurrentQtr;
	}
	public void setEpsPercentChangeCurrentQtr(double epsPercentChangeCurrentQtr) {
		this.epsPercentChangeCurrentQtr = new Double(epsPercentChangeCurrentQtr);
	}
	public Double getEpsEstPercentChangeCurrentYear() {
		return epsEstPercentChangeCurrentYear;
	}
	public void setEpsEstPercentChangeCurrentYear(Double epsEstPercentChangeCurrentYear) {
		this.epsEstPercentChangeCurrentYear = epsEstPercentChangeCurrentYear;
	}
	public void setEpsEstPercentChangeCurrentYear(double epsEstPercentChangeCurrentYear) {
		this.epsEstPercentChangeCurrentYear = new Double(epsEstPercentChangeCurrentYear);
	}
	public Double getSalesPercentChangeLastQtr() {
		return salesPercentChangeLastQtr;
	}
	public void setSalesPercentChangeLastQtr(Double salesPercentChangeLastQtr) {
		this.salesPercentChangeLastQtr = salesPercentChangeLastQtr;
	}
	public void setSalesPercentChangeLastQtr(double salesPercentChangeLastQtr) {
		this.salesPercentChangeLastQtr = new Double(salesPercentChangeLastQtr);
	}
	public Double getAnnualROELastYear() {
		return annualROELastYear;
	}
	public void setAnnualROELastYear(Double annualROELastYear) {
		this.annualROELastYear = annualROELastYear;
	}
	public void setAnnualROELastYear(double annualROELastYear) {
		this.annualROELastYear = new Double(annualROELastYear);
	}
	public Double getAnnualProfitMarginLatestYear() {
		return annualProfitMarginLatestYear;
	}
	public void setAnnualProfitMarginLatestYear(Double annualProfitMarginLatestYear) {
		this.annualProfitMarginLatestYear = annualProfitMarginLatestYear;
	}
	public void setAnnualProfitMarginLatestYear(double annualProfitMarginLatestYear) {
		this.annualProfitMarginLatestYear = new Double(annualProfitMarginLatestYear);
	}
	public Double getManagmentOwnPercent() {
		return managmentOwnPercent;
	}
	public void setManagmentOwnPercent(Double managmentOwnPercent) {
		this.managmentOwnPercent = managmentOwnPercent;
	}
	public void setManagmentOwnPercent(double managmentOwnPercent) {
		this.managmentOwnPercent = new Double(managmentOwnPercent);
	}
	public Integer getQtrsRisingSponsorship() {
		return qtrsRisingSponsorship;
	}
	public void setQtrsRisingSponsorship(Integer qtrsRisingSponsorship) {
		this.qtrsRisingSponsorship = qtrsRisingSponsorship;
	}
	public void setQtrsRisingSponsorship(int qtrsRisingSponsorship) {
		this.qtrsRisingSponsorship = new Integer(qtrsRisingSponsorship);
	}

	public Boolean getActiveRanking() {
		return activeRanking;
	}

	public void setActiveRanking(Boolean activeRanking) {
		this.activeRanking = activeRanking;
	}
	
}
