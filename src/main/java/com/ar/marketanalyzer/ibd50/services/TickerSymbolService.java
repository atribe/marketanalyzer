package com.ar.marketanalyzer.ibd50.services;

import java.util.List;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50NotFound;
import com.ar.marketanalyzer.ibd50.models.TickerSymbol;

public interface TickerSymbolService {

	/**
	 * Adds the provided ticker to the DB.
	 * Checks to see if the symbol is already in the db.
	 * 
	 * @param tickerSymbol to be added to the db
	 * @return the created ticker, now with the ID from the DB. 
	 * 			Or the ticker that was already in the DB.
	 */
	public TickerSymbol createOrFindDuplicate(TickerSymbol tickerSymbol);
	/**
	 * Deletes a ticker with the provided ID from the DB
	 * Checks to make sure the ID is valid.
	 * 
	 * @param id of the ticker to be deleted.
	 * @return The deleted ticker. Hey, maybe you'll want it for something and I'll look really smart.
	 * @throws Ibd50NotFound If the id isn't in the db this exception will be thrown.
	 */
	public TickerSymbol delete(int id) throws Ibd50NotFound;
	/**
	 * Returns all tickers from the DB.
	 * 
	 * @return All TickerSymbols from the DB.
	 */
	public List<TickerSymbol> findAll();
	/**
	 * @param tickerSymbol
	 * @return The updated ticker.
	 * @throws Ibd50NotFound If this is thrown then something in your code is bad.
	 * 			You should have just got the ticker from the db, made some changes
	 * 			and now want to put it back. So the ID should be the same as the one
	 * 			from the DB.
	 */
	public TickerSymbol update(TickerSymbol tickerSymbol) throws Ibd50NotFound;
	/**
	 * Finds the ticker in the db with the provided ID
	 * 
	 * @param id
	 * @return the ticker found with the provided ID
	 * @throws Ibd50NotFound This is thrown if no ticker exists with the provided ID.
	 */
	public TickerSymbol findById(int id) throws Ibd50NotFound;
	/**
	 * Looks up the ticker with the same symbol as the one provided.
	 * 
	 * @param symbol
	 * @return the ticker with the desired symbol.
	 * @throws Ibd50NotFound
	 */
	public TickerSymbol findBySymbol(String symbol) throws Ibd50NotFound;
}
