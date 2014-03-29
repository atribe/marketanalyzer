package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import com.atomrockets.marketanalyzer.models.IndexCalcs;

public class IndexCalcsDAO extends GenericDBSuperclass{

	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * table names
	 * 
	 */
	
	/*
	 * The columns for this class are defined in the LinkedHashMap below
	 * This is so I have one place that shows everything in an easily readable format.
	 */
	private final LinkedHashMap<String, String> m_mySQLColumnList = new LinkedHashMap<String, String>() {
		private final long serialVersionUID = 1L;
		{
			put("id", "INT");
			put("closeAvg50", "FLOAT");
			put("closeAvg100", "FLOAT");
			put("closeAvg200", "FLOAT");
			put("volumeAvg50", "BIGINT(50)");
			put("priceTrend35", "FLOAT");
			put("isDDay", "TINYINT(1)");//1 == true, 0 == false
			put("isChurnDay", "TINYINT(1)");//1 == true, 0 == false
			put("dDayCounter", "INT");
			put("isFollowThruDay", "TINYINT(1)");
			put("dayAction", "VARCHAR(4)");//buy, or sell, or hold
		}
	};
	
	public IndexCalcsDAO() {
		try {
			
			m_connection = getConnection();
			
		} catch (ClassNotFoundException e) {
			// Handles errors if the JDBC driver class not found.
			log.error("Database Driver not found in " + GenericDBSuperclass.class.getSimpleName() + ". Error as follows: "+e);
		} catch (SQLException ex){
			// Handles any errors from MySQL
			log.error("Did you forget to turn on Apache and MySQLL again? From Exception:");
			log.error("SQLException: " + ex.getMessage());
			log.error("SQLState: " + ex.getSQLState());
			log.error("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				m_connection.close();
			} catch (NullPointerException ne) {
				//do nothing, just means that the connection was never initialized
				log.debug("Connection didn't need to be closed, it was never initialized");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
			
	public IndexCalcsDAO(Connection connection) {
		log.debug("------------------------------Index Analysis Table Manager Created--------------------------");
		
		//m_connection is declared in GenericDBSuperclass, which this class extends, so it gets to use it
		m_connection = connection;
	}

	public void tableInitialization(String[] indexList) {
		log.info("Starting Index Analysis Database Initialization");
		
		/*
		 * Checking to see if a table with the index name exists
		 * If it does, print to the command prompt
		 * if not create the table
		 */
		log.info("     -Checking if table " + g_indexCalsTableName + " exists.");
		if(!tableExists(g_indexCalsTableName)) {
			// Table does not exist, so create it
			String createTableSQL = tableCreationString();
			createTable(createTableSQL, g_indexCalsTableName);
		}
		
	}

	public String tableCreationString() {
		 String creationString = "CREATE TABLE IF NOT EXISTS `" + g_indexCalsTableName + "` (" +
				" id INT not NULL AUTO_INCREMENT, ";
		 /*
		  * Using a LinkedHashMap in the creation and elsewhere allows for a centralized
		  * storage of all the column names and types. This should allow for an easy expansion
		  * of this table as more columns are calculated.
		  * I should really add an update table method so it doesn't have to be dropped and readded for
		  * changes to be applied.
		  */
		 for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			 if(entry.getKey() != "id")
			 {
				 creationString += entry.getKey() + " " + entry.getValue() + ", ";
			 }
		 }

		 creationString += "PRIMARY KEY (id)," +
				" FOREIGN KEY (id) REFERENCES `" + g_YahooIndexTableName + "`(id))";
		 return creationString;
	}
	
	public void addDDayStatus(PreparedStatement ps, int id, boolean isDDay) throws SQLException {
		ps.setInt(1, id);
		if(isDDay)
			ps.setInt(2, 1);
		else
			ps.setInt(2, 0);
		ps.execute();
	}

	public List<IndexCalcs> getAllDDayData() throws SQLException {
		
		String query = "SELECT A.PVD_id, I.Date, A.IsDDay" 
				+ " FROM `" + g_indexCalsTableName + "` I"
				+ " INNER JOIN `" + g_YahooIndexTableName + "` A ON I.id = A.PVD_id"
				+ " ORDER BY I.Date DESC";
		PreparedStatement ps = null;
		ps = m_connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<IndexCalcs> AnalysisRows = new ArrayList<IndexCalcs>();
		
		while (rs.next()) {
			IndexCalcs singleResult = new IndexCalcs();
			singleResult.setId(rs.getInt("PVD_id"));
			singleResult.setConvertedDate(rs.getDate("Date"));
			Boolean isDDay;
			if(rs.getInt("IsDDay")==1)
				isDDay=true;
			else
				isDDay=false;
			singleResult.setIsDDay(isDDay);
			
			AnalysisRows.add(singleResult);
		}
		
		return AnalysisRows;
	}

	public void updateRow(String indexTableName, IndexCalcs analysisRow) throws SQLException {
		
		String updateQuery = "UPDATE `" + g_indexCalsTableName + "`"
				+ " SET DDayCounter=?"
				+ " WHERE id=?";
		PreparedStatement ps = m_connection.prepareStatement(updateQuery);
		ps.setInt(1, analysisRow.getdDayCounter());
		ps.setLong(2, analysisRow.getId());
		int rowsUpdated = ps.executeUpdate();
		rowsUpdated = rowsUpdated;//just something to stop the debugging
	}
	
	public void addAllRowsToDB(String indexTableName, List<IndexCalcs> analysisRows) {
		
		String insertQuery = addOrUpdatePreparedString();
		
		String[] columnNames = getColumnNames();
		
		//Batch add
		//Follow the initalAddRecordsFromData method in the MarketIndexDB class
		PreparedStatement ps=null;
		int batchSize = 100;
		
		try {
			long counter = 0;
			//preparing the MySQL statement
			ps = m_connection.prepareStatement(insertQuery);
			//creating DbUtils QuerryRunner
			QueryRunner runner = new QueryRunner();
			
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (IndexCalcs row:analysisRows) {
				/*
				 * Old Manual method. Left as a referece to see how much better the new method is.
				ps.setLong(1, row.getId());
				ps.setDouble(2,  row.getCloseAvg50());
				ps.setDouble(3,  row.getCloseAvg100());
				ps.setDouble(4,  row.getCloseAvg200());
				ps.setLong(5,  row.getVolumeAvg50());
				ps.setDouble(6, row.getPriceTrend35());
				ps.setBoolean(7,  row.getIsDDay());
				ps.setBoolean(8,  row.getIsChurnDay());
				ps.setInt(9, row.getdDayCounter());
				ps.setBoolean(10, row.getIsFollowThruDay());
				ps.setString(11, row.getDayAction());
				*/
				
				/*
				 * New code using DbUtils
				 */
				runner.fillStatementWithBean(ps, row, columnNames);
				/*
				 * Wasn't that awesome
				 */
				ps.addBatch();
				counter++;
				
				if (counter % batchSize == 0) { //if i/batch size remainder == 0 execute batch
					ps.executeBatch();
					log.info("Executed at i="+counter);
				}
			}
			//execute the batch at the end for the leftovers that didn't hit counter%batch==0
			ps.executeBatch();
			log.info("Executed at i="+counter);
			
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
			log.info("SQLState: " + e.getSQLState());
			log.info("VendorError: " + e.getErrorCode());
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			log.info(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}

	}

	private String addOrUpdatePreparedString() {
		//******Start add string builder******
		String insertQuery = "INSERT INTO `" + g_indexCalsTableName + "` "
				+ "(";
		for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			insertQuery += entry.getKey() + ",";
		 }
		
		/*This line is to clean off the last "," added by the previous loop.
		So my code isn't perfect, but it is still pretty cool.*/
		insertQuery = insertQuery.substring(0, insertQuery.length()-1);
		
		insertQuery += ") VALUES"
				+ "(";
		for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			insertQuery += "?,";
		 }
		
		//Another last comma removal
		insertQuery = insertQuery.substring(0, insertQuery.length()-1);
		
		insertQuery += ") ON DUPLICATE KEY UPDATE ";
		
		for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			insertQuery += entry.getKey() + "=VALUES(" + entry.getKey() + "), ";
		}
		
		//Another last comma removal
		insertQuery = insertQuery.substring(0, insertQuery.length()-2);
		//******End add string builder******
		
		return insertQuery;
	}
	
	private String[] getColumnNames() {
		String[] columnNames = new String[m_mySQLColumnList.size()];
		int counter = 0;
		for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			columnNames[counter] = entry.getKey();
			counter++;
		 }
		
		return columnNames;
	}
	public String getTableName() {
		return g_indexCalsTableName;
	}
}
