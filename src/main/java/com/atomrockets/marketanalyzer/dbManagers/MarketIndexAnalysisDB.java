package com.atomrockets.marketanalyzer.dbManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.atomrockets.marketanalyzer.beans.IndexAnalysisRow;

public class MarketIndexAnalysisDB extends GenericDBSuperclass{

	private static final LinkedHashMap<String, String> m_mySQLColumnList = new LinkedHashMap<String, String>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	{
		//put("id", "INT"); id is not included in this because it is autogenerated.
		put("PVD_id", "INT");
		put("closeAvg50", "FLOAT");
		put("closeAvg100", "FLOAT");
		put("closeAvg200", "FLOAT");
		put("volumeAvg50", "BIGINT(50)");
		put("priceTrend35", "FLOAT");
		put("isDDay", "TINYINT(1)");
		put("isChurnDay", "TINYINT(1)");
		put("DDayCounter", "INT");		
	}};
			
	public static void IndexAnalysisTableInitialization(Connection connection,	String[] indexList) {
		System.out.println("");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Starting Index Analysis Database Initialization");
		
		//Iteration tracking variable for System.out.printing and debugging
		int interationCounter = 0;
		
		//Loop for each Price Volume DBs for each index
		for(String index:indexList) {
			interationCounter++;
			System.out.println("Loop Iteration " + interationCounter + ":");
			/*
			 * Checking to see if a table with the index name exists
			 * If it does, print to the command prompt
			 * if not create the table
			 */
			
			String indexAnalysisTableName = getTableName(index);
			
			System.out.println("     -Checking if table " + indexAnalysisTableName + " exists.");
			if(!tableExists(indexAnalysisTableName, connection)) {
				// Table does not exist, so create it
				String createTableSQL = tableCreationString(index, indexAnalysisTableName);
				createTable(createTableSQL, connection, indexAnalysisTableName);
			}
		}
	}
	
	public static String getTableName(String index) {
		return index +"DataAnalysis";
	}

	public static String tableCreationString(String index, String indexAnalysisTableName) {
		 String creationString = "CREATE TABLE IF NOT EXISTS `" + indexAnalysisTableName + "` (" +
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
				" FOREIGN KEY (PVD_id) REFERENCES `" + index + "`(id))";
		 return creationString;
	}
	
	public static void addDDayStatus(PreparedStatement ps, int id, boolean isDDay) throws SQLException {
		ps.setInt(1, id);
		if(isDDay)
			ps.setInt(2, 1);
		else
			ps.setInt(2, 0);
		ps.execute();
	}

	public static List<IndexAnalysisRow> getAllDDayData(Connection connection, String indexTableName) throws SQLException {
		String tableName = getTableName(indexTableName);
		
		String query = "SELECT A.PVD_id, I.Date, A.IsDDay" 
				+ " FROM `" + indexTableName + "` I"
				+ " INNER JOIN `" + tableName + "` A ON I.id = A.PVD_id"
				+ " ORDER BY I.Date DESC";
		PreparedStatement ps = null;
		ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<IndexAnalysisRow> AnalysisRows = new ArrayList<IndexAnalysisRow>();
		
		while (rs.next()) {
			IndexAnalysisRow singleResult = new IndexAnalysisRow();
			singleResult.setPVD_id(rs.getInt("PVD_id"));
			singleResult.setDate(rs.getDate("Date"));
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

	public static void updateRow(Connection connection, String indexTableName, IndexAnalysisRow analysisRow) throws SQLException {
		String tableName = getTableName(indexTableName);
		
		String updateQuery = "UPDATE `" + tableName + "`"
				+ " SET DDayCounter=?"
				+ " WHERE PVD_id=?";
		PreparedStatement ps = connection.prepareStatement(updateQuery);
		ps.setInt(1, analysisRow.getdDayCounter());
		ps.setInt(2, analysisRow.getPVD_id());
		int rowsUpdated = ps.executeUpdate();
		rowsUpdated = rowsUpdated;//just something to stop the debugging
	}
	
	public static void addAllRowsToDB(Connection connection, String indexTableName, List<IndexAnalysisRow> analysisRows) {
		String tableName = getTableName(indexTableName);
		
		//******Start add string builder******
		String insertQuery = "INSERT IGNORE INTO `" + tableName + "` "
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
		
		insertQuery += ")";
		//******End add string builder******
		
		//Batch add
		//Follow the initalAddRecordsFromData method in the MarketIndexDB class
		PreparedStatement ps=null;
		int batchSize = 200;
		
		try {
			ps = connection.prepareStatement(insertQuery);
			
			//Iterate through the list backwards. I want the oldest date in first and this achieves that
			for (int i = 0; i < analysisRows.size() ; i++) {
				//if the row is not in the DB prepare it for insertion
				ps.setInt(1, analysisRows.get(i).getPVD_id());
				ps.setDouble(2,  analysisRows.get(i).getCloseAvg50());
				ps.setDouble(3,  analysisRows.get(i).getCloseAvg100());
				ps.setDouble(4,  analysisRows.get(i).getCloseAvg200());
				ps.setLong(5,  analysisRows.get(i).getVolumeAvg50());
				ps.setDouble(6, analysisRows.get(i).getPriceTrend35());
				ps.setBoolean(7,  analysisRows.get(i).isDDay());
				ps.setBoolean(8,  analysisRows.get(i).isChurnDay());
				ps.setInt(9, analysisRows.get(i).getdDayCounter());
				ps.addBatch();
				
				if (i % batchSize == 0) //if i/batch size remainder == 0 execute batch
				{
					ps.executeBatch();
					System.out.println("Executed at i="+i);
				}
			}
			
			ps.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException sqlEx) { } // ignore

				ps = null;
			}
		}

	}
}
