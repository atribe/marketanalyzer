package com.ar.marketanalyzer.backtest.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;

@Entity
@Table(name = "backtest_trade")
public class Trade extends PersistableEntityInt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 295920987530245454L;

	@ManyToOne(optional=false)
	@JoinColumn(name="model_id", referencedColumnName="id")
	private AbstractModel model;
	
	@Column(name="buy_date")
	private Date buyDate;
	
	@Column(name="sell_date")
	private Date sellDate;
	
	@Column(precision=12, scale=2, nullable=false)
	private BigDecimal buyPrice;
	
	@Column(precision=12, scale=2, nullable=false)
	private BigDecimal sellPrice;
	
	/*
	 * Constructors
	 */
	public Trade() {
	}
	
	/*
	 * Helper Methods
	 */
	public void sell(Date date, BigDecimal sellPrice) {
		setSellDate(date);
		setSellPrice(sellPrice);
	}
	public void buy(Date date, BigDecimal buyPrice) {
		setBuyDate(date);
		setBuyPrice(buyPrice);
	}
	
	@Override
	public String toString() {
		return "Buy:" + buyDate.toString() + " Sell:" + sellDate.toString();
	}
	
	/*
	 * Getters and Setters
	 */
	public AbstractModel getModel() {
		return model;
	}

	public void setModel(AbstractModel model) {
		this.model = model;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
}
