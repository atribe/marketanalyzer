package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import javax.sql.DataSource;

import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.ibd50.beans.Ibd50RankingBean;
import com.ar.marketanalyzer.ibd50.dao.Ibd50RankingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50TrackingDao;
import com.ar.marketanalyzer.ibd50.dao.Ibd50WebDao;
import com.ar.marketanalyzer.ibd50.dao.stockOhlcvDao;

public class IBD50Service {
	private DataSource ds;
	
	private Ibd50WebDao webDao;
	private Ibd50RankingDao rankingDao;
	private stockOhlcvDao ohlcvDao;
	private Ibd50TrackingDao trackingDao;
	
	public IBD50Service() {
		ds = MarketPredDataSource.setDataSource();
		webDao = new Ibd50WebDao();
		rankingDao = new Ibd50RankingDao(ds);
		trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	public IBD50Service(DataSource passedds) {
		this.ds = passedds;
		webDao = new Ibd50WebDao();
		rankingDao = new Ibd50RankingDao(ds);
		trackingDao = new Ibd50TrackingDao(ds);
		ohlcvDao = new stockOhlcvDao(ds);
	}
	
	public void tableInit() {
		rankingDao.symbolTableInit();
		trackingDao.tableInit();
		rankingDao.tableInit();
		ohlcvDao.tableInit();
	}

	public void pullWebIbd50() {
		List<Ibd50RankingBean> webIbd50= webDao.grabIbd50();
		
	}
}
