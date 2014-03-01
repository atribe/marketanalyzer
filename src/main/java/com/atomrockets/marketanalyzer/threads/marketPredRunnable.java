package com.atomrockets.marketanalyzer.threads;

import com.atomrockets.marketanalyzer.analyzer.MarketAnalyzerMain;

public class marketPredRunnable implements Runnable {
	public static boolean m_continueRunning = true;
	public static Thread m_killerThread = null;
	
	public marketPredRunnable() {
		System.out.println("starting the marketPredRunnable constructor.");
	}
	
	public static void main (String[] args) {
		System.out.println("Starting marketPredRunnable main()");
		
		Thread oneThread = new Thread(new marketPredRunnable());
		oneThread.setDaemon(true);
		oneThread.start();

	}
	
	public void run() {
		System.out.println("Starting marketPredRunnable.run()");
		try {
			Thread.sleep(10000);
	    
			while (m_continueRunning)
			{
				/*Maybe add this later
				m_killerThread = new Thread(new ApplicationKillerThread());
				m_killerThread.setDaemon(true);
				m_killerThread.start();
				*/
				/*
				 * MarketIndicesModel class runs everything having to do with analyzing if
				 * the market indices are in a long or short condition
				 * This includes getting data, calculating d-days etc, and optimization
				 */
				MarketAnalyzerMain.main();
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception "+e);
		}
	}
}
