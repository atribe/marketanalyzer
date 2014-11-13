package com.ar.marketanalyzer.plotting.amstockcharts.enums;

/**
 * @author Allan
 *
 * Prefixes which are used to make small numbers shorter: 2u instead of 0.000002, etc. Prefixes are used on value axes and in the legend. To enable prefixes, set usePrefixes property to true.
 * Example:
 * [{number:1e-24, prefix:"y"},
 * {number:1e-21, prefix:"z"},
 * {number:1e-18, prefix:"a"},
 * {number:1e-15, prefix:"f"},
 * {number:1e-12, prefix:"p"},
 * {number:1e-9, prefix:"n"},
 * {number:1e-6, prefix:"u"},
 * {number:1e-3, prefix:"m"}]
 */
public class SmallPrefix {
	private int power;
	private String prefix;
	
	public SmallPrefix() {
		
	}
	public SmallPrefix(int power, String prefix) {
		this.power = power;
		this.prefix = prefix;
	}
	
	@Override
	public String toString() {
		return "number:1e-" + power + ",prefix:\"" + prefix + "\"";
	}
}
