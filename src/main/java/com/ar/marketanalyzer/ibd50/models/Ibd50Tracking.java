package com.ar.marketanalyzer.ibd50.models;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.ar.marketanalyzer.database.GenericDBSuperclass;

@Entity
@Table(name = "IBD50_TRACKING")
public class Ibd50Tracking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tracking_id", nullable=false, unique=true, length=8)
	private int trackingId;
	
	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="ticker_symbol_id")
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
		// TODO Auto-generated constructor stub
	}
	
	public Ibd50Tracking(int symbol_id) {
		//setSymbol_id( symbol_id );
		setJoinDate( newMonday() );
	}

	/*
	 * Helper Methods
	 */
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

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

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
		setJoinDate( new Date(join_date.toDate().getTime()) );
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