package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.atomrockets.marketanalyzer.models.IndexCalcsModel;

public class IndexCalcsDAO extends GenericDBSuperclass{

	/*
	 * Important Stuff inherited from GenericDBSuperclass
	 * 
	 * m_connection
	 * log
	 * 
	 */
	
	//table names
	private final String m_marketIndexAnalysisTableName = "marketIndexAnalysis";
	private final String yDTname = "yahooDataTable";
	
	/*
	 * The columns for this class are defined in the LinkedHashMap below
	 * This is so I have one place that shows everything in an easily readable format.
	 */
	private final LinkedHashMap<String, String> m_mySQLColumnList = new LinkedHashMap<String, String>() {
		private final long serialVersionUID = 1L;
		{
			put("symbol", "VARCHAR(10)");
			put("closeAvg50", "FLOAT");
			put("closeAvg100", "FLOAT");
			put("closeAvg200", "FLOAT");
			put("volumeAvg50", "BIGINT(50)");
			put("priceTrend35", "FLOAT");
			put("isDDay", "TINYINT(1)");//1 == true, 0 == false
			put("isChurnDay", "TINYINT(1)");//1 == true, 0 == false
			put("DDayCounter", "INT");
			put("isFollowThruDay", "TINYINT(1)");
			put("dayAction", "VARCHAR(4)");//buy, or sell, or hold
		}
	};
	
	public IndexCalcsDAO() {
		m_connection = getConnection();
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
		log.info("     -Checking if table " + m_marketIndexAnalysisTableName + " exists.");
		if(!tableExists(m_marketIndexAnalysisTableName)) {
			// Table does not exist, so create it
			String createTableSQL = tableCreationString();
			createTable(createTableSQL, m_marketIndexAnalysisTableName);
		}
		
	}

	public String tableCreationString() {
		 String creationString = "CREATE TABLE IF NOT EXISTS `" + m_marketIndexAnalysisTableName + "` (" +
				" id INT not NULL AUTO_INCREMENT, ";
		 /*
		  * Using a LinkedHashMap in the creation and elsewhere allows for a centralized
		  * storage of all the column names and types. This should allow for an easy expansion
		  * of this table as more columns are calculated.
		  * I should really add an update table method so it doesn't have to be dropped and readded for
		  * changes to be applied.
		  */
		 for(Map.Entry<String, String> entry : m_mySQLColumnList.entrySet()) {
			 creationString += entry.getKey() + " " + entry.getValue() + ", ";
		 }

		 creationString += "PRIMARY KEY (id)," +
				" FOREIGN KEY (id) REFERENCES `" + yDTname + "`(yd_id))";
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

	public List<IndexCalcsModel> getAllDDayData() throws SQLException {
		
		String query = "SELECT A.PVD_id, I.Date, A.IsDDay" 
				+ " FROM `" + m_marketIndexAnalysisTableName + "` I"
				+ " INNER JOIN `" + yDTname + "` A ON I.id = A.PVD_id"
				+ " ORDER BY I.Date DESC";
		PreparedStatement ps = null;
		ps = m_connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<IndexCalcsModel> AnalysisRows = new ArrayList<IndexCalcsModel>();
		
		while (rs.next()) {
			IndexCalcsModel singleResult = new IndexCalcsModel();
			singleResult.setId(rs.getInt("PVD_id"));
			singleResult.setConvertedDate(rs.getDate("Date"));
			boolean isDDay;
			if(rs.getInt("IsDDay")==1)
				isDDay=true;
			else
				isDDay=false;
			singleResult.setDDay(isDDay);
			
			AnalysisRows.add(singleResult);
		}
		
		return AnalysisRows;
	}

	public void updateRow(String indexTableName, IndexCalcsModel analysisRow) throws SQLException {
		
		String updateQuery = "UPDATE `" + m_marketIndexAnalysisTableName + "`"
				+ " SET DDayCounter=?"
				+ " WHERE id=?";
		PreparedStatement ps = m_connection.prepareStatement(updateQuery);
		ps.setInt(1, analysisRow.getdDayCounter());
		ps.setLong(2, analysisRow.getId());
		int rowsUpdated = ps.executeUpdate();
		rowsUpdated = rowsUpdated;//just something to stop the debugging
	}
	
	public void addAllRowsToDB(String indexTableName, List<IndexCalcsModel> analysisRows) {
		
		String insertQuery = addOrUpdatePreparedString();
		
		//Batch add
		//Follow the initalAddRecordsFromData method in the MarketIndexDB class
		PreparedStatement ps=null;
		int batchSize = 200;
		
		try {
			ps = m_connection.prepareStatement(insertQuery);
			
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (int i = 0; i < analysisRows.size() ; i++) {
				//if the row is not in the DB prepare it for insertion
				ps.setLong(1, analysisRows.get(i).getId());
				ps.setDouble(2,  analysisRows.get(i).getCloseAvg50());
				ps.setDouble(3,  analysisRows.get(i).getCloseAvg100());
				ps.setDouble(4,  analysisRows.get(i).getCloseAvg200());
				ps.setLong(5,  analysisRows.get(i).getVolumeAvg50());
				ps.setDouble(6, analysisRows.get(i).getPriceTrend35());
				ps.setBoolean(7,  analysisRows.get(i).isDDay());
				ps.setBoolean(8,  analysisRows.get(i).isChurnDay());
				ps.setInt(9, analysisRows.get(i).getdDayCounter());
				ps.setBoolean(10, analysisRows.get(i).isFollowThruDay());
				ps.setString(11, analysisRows.get(i).getDayAction());
				ps.addBatch();
				
				if (i % batchSize == 0) //if i/batch size remainder == 0 execute batch
				{
					ps.executeBatch();
					log.info("Executed at i="+i);
				}
			}
			
			ps.executeBatch();
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
		String insertQuery = "INSERT INTO `" + m_marketIndexAnalysisTableName + "` "
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
	public String getTableName() {
		return m_marketIndexAnalysisTableName;
	}
}
