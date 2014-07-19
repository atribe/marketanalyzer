package com.ar.marketanalyzer.ibd50.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.ibd50.models.parents.AuditableEntity;

@Entity
@Table(name = "IBD50_TRACKING")
public class Ibd50Tracking extends AuditableEntity{

	private static final long serialVersionUID = -3420926876186272627L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private TickerSymbol ticker;
	
	@OneToMany(mappedBy = "tracker",cascade = CascadeType.ALL)
	private Collection<Ibd50Ranking> ranking;
	
	@Column
	private Boolean active;
	
	@Column(name="join_date")
	private Date joinDate;
	
	@Column(name="leave_date")
	private Date leaveDate;
	
	@Column(name="join_price")
	private BigDecimal joinPrice;
	
	@Column(name="leave_price")
	private BigDecimal lastPrice;
	
	@Column(name="percent_return")
	private double percentReturn; //% return while on the list
	
	/*
	 * Constructors
	 */
	public Ibd50Tracking() {
		setJoinDate( newMonday() );
	}

	/*
	 * Helper Methods
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	private LocalDate newMonday() {
		LocalDate today = new LocalDate();
		if(today.getDayOfWeek() == DateTimeConstants.MONDAY) {
			return today;
		} else {
			return today.withDayOfWeek(DateTimeConstants.MONDAY);
		}
	}
	/*
	 * Getters and Setters
	 */
	public TickerSymbol getTicker() {
		return ticker;
	}

	public void setTicker(TickerSymbol ticker) {
		this.ticker = ticker;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public void setJoinDate(LocalDate join_date) {
		setJoinDate( join_date.toDate() );
	}
	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	public BigDecimal getJoinPrice() {
		return joinPrice;
	}

	public void setJoinPrice(BigDecimal joinPrice) {
		this.joinPrice = joinPrice;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getPercentReturn() {
		return percentReturn;
	}

	public void setPercentReturn(double percentReturn) {
		this.percentReturn = percentReturn;
	}
	
}
