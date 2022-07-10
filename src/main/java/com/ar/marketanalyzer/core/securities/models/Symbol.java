package com.ar.marketanalyzer.core.securities.models;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "securities_symbols") 
public class Symbol extends PersistableEntityInt {

	private static final long serialVersionUID = 8657512556970860218L;

	@Column(name="symbol", length=15, nullable=false)
	private String symbol;

	@Column(name="name", length=50, nullable=false)
	private String name;

	@Column(name="type", length=10, nullable=false)
	private String type; //stock, ETF, Mutual Fund, Index
	
	@Column(name="oldest_date_in_db")
	private Boolean oldestDateInDb;
	
	@OneToMany(mappedBy = "symbol",cascade = CascadeType.ALL)
	private List<SecuritiesOhlcv> ohlcv;
	
	@Override
	public String toString() {
		return "ID:" + id + ", " +symbol + ", " + name + ", " + type;
	}

	public Symbol(String symbol, String name, String type) {
		this.symbol = symbol;
		this.name = name;
		this.type = type;
	}
}
