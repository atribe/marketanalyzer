package com.atomrockets.marketanalyzer.dbManagers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.atomrockets.marketanalyzer.beans.BacktestResult;
import com.atomrockets.marketanalyzer.beans.BacktestResult.parametersTypeEnum;
import com.atomrockets.marketanalyzer.spring.init.PropCache;

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
		setM_ds(MarketPredDataSource.setDataSource());
	}
	
	public BacktestResultDAO(DataSource ds) {
		log.debug("------------------------------Index Parameter Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_ds = ds;
	}
	
	/**
	 * @param indexList
	 * @throws SQLException 
	 */
	public void tableInitialization(String[] indexList) throws SQLException {
		log.info("Starting Market Index Parameters Database Initialization");
		
		BacktestResult b = new BacktestResult();
		
		String tableName = BacktestResult.getTableName();
	
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
		BacktestResult b = new BacktestResult();
		
		PreparedStatement ps = null;
		String[] columnNames = b.getColumnNameList();
		String insertQuery = b.getInsertOrUpdateQuery();
		QueryRunner runner = new QueryRunner(m_ds);
		Connection con = m_ds.getConnection();
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
				b.setBalance(new BigDecimal(10000));
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
				b.setBalance(new BigDecimal(10000));
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
				b.setBalance(new BigDecimal(10000));
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
				b.setBalance(new BigDecimal(10000));
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
				b.setBalance(new BigDecimal(10000));
				break;
			}
	
			log.info("Populating variable database for " + BacktestResult.getTableName());
	
			//Add each entry to the DB
			runner.fillStatementWithBean(ps, b, columnNames);

			ps.execute();
		
			con.close();
			b = new BacktestResult();
		}

	}
	
	public BacktestResult getSymbolParameters(String symbol, parametersTypeEnum type) throws SQLException {
		BacktestResult b = new BacktestResult(symbol);
		
		String getParametersQuery = b.getParameterQuery();
		QueryRunner runner = new QueryRunner(m_ds);
		ResultSetHandler<BacktestResult> h = new BeanHandler<BacktestResult>(BacktestResult.class);
		
		b = runner.query(
				getParametersQuery,
				h,
				symbol,
				type.getValue()
				);
		return b;
	}
	
	public int insertOrUpdateBacktest(BacktestResult b) throws SQLException {
		
		/*
		 * inserting and/or updating b into the db
		 */
		String[] columnNames = b.getColumnNameList();
		String insertQuery = b.getInsertOrUpdateQuery();
		QueryRunner runner = new QueryRunner(m_ds);
		Connection con = m_ds.getConnection();
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
}
