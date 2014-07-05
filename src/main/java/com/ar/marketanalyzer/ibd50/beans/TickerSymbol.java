package com.ar.marketanalyzer.ibd50.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TICKER_SYMBOLS")
public class TickerSymbol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TICKER_SYMBOL_ID", nullable=false, unique=true, length=6)
	private int id;

	@Column(name="SYMBOL", length=15, nullable=true)
	private String symbol;

	@Column(name="NAME", length=50, nullable=true)
	private String name;

	@Column(name="TYPE", length=10, nullable=true)
	private String type; //stock, ETF, Mutual Fund, Index

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
