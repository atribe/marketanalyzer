package com.ar.marketanalyzer.plotting.amstockcharts.enums;

/**
 * @author Allan
 * 
 * Prefixes which are used to make big numbers shorter: 2M instead of 2000000, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
 * Example:
 * [{number:1e+3,prefix:"k"},
 * {number:1e+6,prefix:"M"},
 * {number:1e+9,prefix:"G"},
 * {number:1e+12,prefix:"T"},
 * {number:1e+15,prefix:"P"},
 * {number:1e+18,prefix:"E"},
 * {number:1e+21,prefix:"Z"},
 * {number:1e+24,prefix:"Y"}]
 */
public class BigPrefix {
	private int power;
	private String prefix;
	
	public BigPrefix() {
		
	}
	public BigPrefix(int power, String prefix) {
		this.power = power;
		this.prefix = prefix;
	}
	
	@Override
	public String toString() {
		return "number:1e+" + power + ",prefix:\"" + prefix + "\"";
	}
}
