package com.ar.marketanalyzer.core.securities.services.interfaces;

import java.util.List;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;

public interface SymbolServiceInterface {

	/**
	 * Adds the provided ticker to the DB.
	 * Checks to see if the symbol is already in the db.
	 * 
	 * @param symbol to be added to the db
	 * @return the created ticker, now with the ID from the DB. 
	 * 			Or the ticker that was already in the DB.
	 */
	public Symbol createOrFindDuplicate(Symbol symbol);
	/**
	 * Deletes a ticker with the provided ID from the DB
	 * Checks to make sure the ID is valid.
	 * 
	 * @param id of the ticker to be deleted.
	 * @return The deleted ticker. Hey, maybe you'll want it for something and I'll look really smart.
	 * @throws SecuritiesNotFound If the id isn't in the db this exception will be thrown.
	 */
	public Symbol delete(int id) throws SecuritiesNotFound;
	/**
	 * Returns all symbols from the DB.
	 * 
	 * @return All symbols from the DB.
	 */
	public List<Symbol> findAll();
	/**
	 * Returns all symbols from the DB including ohlcv lists.
	 * 
	 * @return All symbols from the DB.
	 */
	public List<Symbol> findAllFetchOhlcv();
	/**
	 * @param symbol
	 * @return The updated ticker.
	 * @throws SecuritiesNotFound If this is thrown then something in your code is bad.
	 * 			You should have just got the ticker from the db, made some changes
	 * 			and now want to put it back. So the ID should be the same as the one
	 * 			from the DB.
	 */
	public Symbol update(Symbol symbol);
	/**
	 * Finds the ticker in the db with the provided ID
	 * 
	 * @param id
	 * @return the ticker found with the provided ID
	 * @throws SecuritiesNotFound This is thrown if no ticker exists with the provided ID.
	 */
	public Symbol findById(int id) throws SecuritiesNotFound;
	/**
	 * Looks up the ticker with the same symbol as the one provided.
	 * 
	 * @param symbol
	 * @return the ticker with the desired symbol.
	 * @throws SecuritiesNotFound
	 */
	public Symbol findBySymbol(String symbol) throws SecuritiesNotFound;
}
