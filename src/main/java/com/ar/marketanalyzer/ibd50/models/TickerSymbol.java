package com.ar.marketanalyzer.ibd50.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ar.marketanalyzer.ibd50.models.parents.PersitableEntity;

@Entity
@Table(name = "IBD50_TICKER_SYMBOLS")
public class TickerSymbol extends PersitableEntity {

	private static final long serialVersionUID = 8657512556970860218L;

	@Column(name="symbol", length=15, nullable=false)
	private String symbol;

	@Column(name="name", length=50, nullable=false)
	private String name;

	@Column(name="type", length=10, nullable=false)
	private String type; //stock, ETF, Mutual Fund, Index
	
	@OneToMany(mappedBy = "ticker",cascade = CascadeType.ALL)
	private Collection<Ibd50Ranking> rankings;
	
	@OneToMany(mappedBy = "ticker",cascade = CascadeType.ALL)
	private Collection<Ibd50Ranking> trackings;
	
	@OneToMany(mappedBy = "ticker",cascade = CascadeType.ALL)
	private Collection<Ibd50Ranking> ohlcvData;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
