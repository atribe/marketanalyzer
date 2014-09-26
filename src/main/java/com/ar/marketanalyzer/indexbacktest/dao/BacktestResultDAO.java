package com.ar.marketanalyzer.indexbacktest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ar.marketanalyzer.database.GenericDBSuperclass;
import com.ar.marketanalyzer.database.MarketPredDataSource;
import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean;
import com.ar.marketanalyzer.indexbacktest.beans.BacktestBean.parametersTypeEnum;
import com.ar.marketanalyzer.spring.init.PropCache;

/**
 * This subclass handles all database operations involving the price and volume data for each index
 * @author Allan
 *
 */
public class BacktestResultDAO extends GenericDBSuperclass{
	
	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */
	private static final String startDate = PropCache.getCachedProps("analysis.startDate");
	private static final String endDate = PropCache.getCachedProps("analysis.endDate");
	
	//Constructor
	public BacktestResultDAO() {
		setDs(MarketPredDataSource.setDataSource());
	}
	
	public BacktestResultDAO(DataSource newDs) {
		log.debug("------------------------------Index Parameter Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		//ds = newDs;
	}
	
	/**
	 * @param indexList
	 * @throws SQLException 
	 */
	public void tableInitialization(String[] indexList) throws SQLException {
		log.info("Starting Market Index Parameters Database Initialization");
		
		BacktestBean b = new BacktestBean();
		
		String tableName = BacktestBean.getTableName();
	
		/*
		 * Checking to see if a table with the indexParams name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + tableName+ " exists.");
		if(!tableExists(tableName)) {
			// Table does not exist, so create it
			String createTableSQL = b.tableCreationString();
			createTable(createTableSQL, tableName);
		}

		/*
		 * Checking to see if the tables are empty
		 * If they are populate them from Yahoo
		 * If not, check if they are up to date
		 * 		If not, update them
		 */
		log.info("     -Checking if table " + tableName + " is empty.");
		if(tableEmpty(tableName)){
			//if table is empty
			//populate it
			populateFreshParamDB(indexList);
		}
		log.info("-------------------------End of Index Parameters Table Setup------------------------------");
	}

	/**
	 * Primary method to populate a price volume database after it is newly created
	 * @param connection
	 * @param indexList 
	 * @param indexParams
	 * @throws SQLException 
	 * 
	 */
	private void populateFreshParamDB(String[] indexList) throws SQLException{
		BacktestBean b = new BacktestBean();
		
		PreparedStatement ps = null;
		String[] columnNames = b.getColumnNameList();
		String insertQuery = b.getInsertOrUpdateQuery();
		QueryRunner runner = new QueryRunner(ds);
		Connection con = ds.getConnection();
		ps = con.prepareStatement(insertQuery);
		
		for(String index:indexList)
		{
			//this parameter is true for every row set in this method
			b.setParametersType(parametersTypeEnum.BASE);
			
			switch(index){
			case "^IXIC":
				/*
				 * Parameters with comments have been used. The rest are not yet used.
				 */
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf(startDate));
				b.setEndDate(java.sql.Date.valueOf(endDate));
				b.setdDayWindow(20);
				b.setdDayParam(9);
				b.setdDayPriceDrop(-0.002);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(false);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.009);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.11);
				b.setVolMultTop(1.09);
				b.setVolMultBot(1.01);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.007);
				b.setPriceMultTop(1.009);
				b.setPriceMultBot(1.007);
				b.setrDaysMin(3);
				b.setrDaysMax(8);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.004);
				b.setRallyVolAVG50On(true);
				b.setRallyPriceHighOn(true);
				break;
			case "^GSPC":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf(startDate));
				b.setEndDate(java.sql.Date.valueOf(endDate));
				b.setdDayWindow(20);
				b.setdDayParam(10);
				b.setdDayPriceDrop(-0.002);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(false);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.007);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.1);
				b.setVolMultTop(1.1);
				b.setVolMultBot(1.1);
				b.setPriceVolatilityOn(true);
				b.setPriceMult(1.013);
				b.setPriceMultTop(1.014);
				b.setPriceMultBot(1.012);
				b.setrDaysMin(4);
				b.setrDaysMax(18);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.003);
				b.setRallyVolAVG50On(false);
				b.setRallyPriceHighOn(true);
				break;
			case "^SML":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf(startDate));
				b.setEndDate(java.sql.Date.valueOf(endDate));
				b.setdDayWindow(20);
				b.setdDayParam(10);
				b.setdDayPriceDrop(-0.002);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(false);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.007);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.1);
				b.setVolMultTop(1.1);
				b.setVolMultBot(1.1);
				b.setPriceVolatilityOn(true);
				b.setPriceMult(1.013);
				b.setPriceMultTop(1.014);
				b.setPriceMultBot(1.012);
				b.setrDaysMin(4);
				b.setrDaysMax(18);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.003);
				b.setRallyVolAVG50On(false);
				b.setRallyPriceHighOn(true);
				break;
			case "^MID":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf(startDate));
				b.setEndDate(java.sql.Date.valueOf(endDate));
				b.setdDayWindow(20);
				b.setdDayParam(10);
				b.setdDayPriceDrop(-0.002);
				b.setChurnVolRange(0.03);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(false);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.007);
				b.setVolVolatilityOn(false);
				b.setVolumeMult(1.1);
				b.setVolMultTop(1.1);
				b.setVolMultBot(1.1);
				b.setPriceVolatilityOn(true);
				b.setPriceMult(1.013);
				b.setPriceMultTop(1.014);
				b.setPriceMultBot(1.012);
				b.setrDaysMin(4);
				b.setrDaysMax(18);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.003);
				b.setRallyVolAVG50On(false);
				b.setRallyPriceHighOn(true);
				break;
			case "^DJI":
				b.setSymbol(index);
				b.setStartDate(java.sql.Date.valueOf(startDate));
				b.setEndDate(java.sql.Date.valueOf(endDate));
				b.setdDayWindow(20);
				b.setdDayParam(10);
				b.setdDayPriceDrop(-0.002);
				b.setChurnVolRange(0.05);
				b.setChurnPriceRange(0.02);
				b.setChurnPriceCloseHigherOn(false);
				b.setChurnAVG50On(false);
				b.setChurnPriceTrend35On(false);
				b.setChurnPriceTrend35(0.003);
				b.setVolVolatilityOn(true);
				b.setVolumeMult(1.3);
				b.setVolMultTop(1.18);
				b.setVolMultBot(1.05);
				b.setPriceVolatilityOn(false);
				b.setPriceMult(1.011);
				b.setPriceMultTop(1.011);
				b.setPriceMultBot(1.008);
				b.setrDaysMin(5);
				b.setrDaysMax(18);
				b.setPivotTrend35On(false);
				b.setPivotTrend35(-0.003);
				b.setRallyVolAVG50On(false);
				b.setRallyPriceHighOn(true);
				break;
			}
	
			log.info("Populating variable database for " + BacktestBean.getTableName());
	
			//Add each entry to the DB
			runner.fillStatementWithBean(ps, b, columnNames);

			ps.execute();
		
			con.close();
			b = new BacktestBean();
		}

	}
	
	public BacktestBean getSymbolParameters(String symbol, parametersTypeEnum type) throws SQLException {
		BacktestBean b = new BacktestBean(symbol);
		
		String getParametersQuery = b.getParameterQuery();
		QueryRunner runner = new QueryRunner(ds);
		ResultSetHandler<BacktestBean> h = new BeanHandler<BacktestBean>(BacktestBean.class);
		
		b = runner.query(
				getParametersQuery,
				h,
				symbol,
				type.getValue()
				);
		return b;
	}
	
	public int insertOrUpdateBacktest(BacktestBean b) throws SQLException {
		
		//if the new backtest to be inserted is the new current
		if(checkNewCurrent(b)) {
			//then make the old current a previous
			BacktestBean oldBacktest = getSymbolParameters(b.getSymbol(),parametersTypeEnum.CURRENT);
			//if the oldBacktest isn't null then make it a previous backtest and update it
			if(oldBacktest != null) {
				oldBacktest.setParametersType(parametersTypeEnum.PREVIOUS);
				
				insertBacktest(oldBacktest); 
			}
			//if oldBacktest is null then do nothing and move on to the next part of the method
		}
		
		//insert the new backtest
		int insertedId = insertBacktest(b);
		
		return insertedId;
	}

	private int insertBacktest(BacktestBean b) throws SQLException {
		/*
		 * inserting and/or updating b into the db
		 */
		String[] columnNames = b.getColumnNameList();
		String insertQuery = b.getInsertOrUpdateQuery();
		QueryRunner runner = new QueryRunner(ds);
		Connection con = ds.getConnection();
		PreparedStatement ps = con.prepareStatement(insertQuery);

		runner.fillStatementWithBean(ps, b, columnNames);
		
		ps.executeUpdate();
		
		/*
		 * Getting the id of the new row from the db
		 */
		int insertedId = 0;
		
		ps = con.prepareStatement("SELECT LAST_INSERT_ID();");
		ResultSet rs = ps.executeQuery();
		if(rs != null && rs.next()) {
			insertedId = rs.getInt(1);
		}
		 
		con.close();
		
		return insertedId;
	}

	private boolean checkNewCurrent(BacktestBean b) {
		if(b.getParametersType() == parametersTypeEnum.CURRENT.getValue()) {
			return true;
		} else {
			return false;
		}
	}
}
