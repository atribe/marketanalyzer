package com.ar.marketanalyzer.ibd50.models;

import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.ibd50.models.parents.AuditableEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;

@Entity
@Table(name = "IBD50_TRACKING")
public class Ibd50Tracking extends AuditableEntity{

	private static final long serialVersionUID = -3420926876186272627L;

	@ManyToOne(optional=false)//optional=false makes this an inner join, true would be Outer join
	@JoinColumn(name="symbol_id", referencedColumnName="id")
	private Symbol ticker;

	@OneToMany(mappedBy = "tracker",cascade = CascadeType.ALL)
	private Collection<Ibd50Rank> ranking;

	@Column
	private Boolean active;

	@Column(name="join_date")
	private LocalDateTime joinDate;

	@Column(name="leave_date")
	private LocalDateTime leaveDate;

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

	public LocalDateTime newMonday() {
		LocalDateTime today = LocalDateTime.now();
		if(today.getDayOfWeek() == DayOfWeek.MONDAY) {
			return today;
		} else {
			return today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		}
	}

	public void deactivate(BigDecimal leavePrice) {
		setActive(Boolean.FALSE);
		setLeaveDate(newMonday());
		setLastPrice(leavePrice);
		calcPercentReturn();
	}
	private void calcPercentReturn() {
		setPercentReturn(lastPrice.subtract(joinPrice).doubleValue()/joinPrice.doubleValue());
	}

	/*
	 * Getters and Setters
	 */
	public Symbol getTicker() {
		return ticker;
	}
	public void setTicker(Symbol ticker) {
		this.ticker = ticker;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime join_date) {
		this.joinDate = join_date;
	}
	public LocalDateTime getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(LocalDateTime leaveDate) {
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
