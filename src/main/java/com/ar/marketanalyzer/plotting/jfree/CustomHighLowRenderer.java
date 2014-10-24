package com.ar.marketanalyzer.plotting.jfree;

import org.jfree.chart.renderer.xy.HighLowRenderer;

public class CustomHighLowRenderer extends HighLowRenderer{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6915403821895486743L;

	public CustomHighLowRenderer() {
        super();

        setTickLength(7);
    }
}
