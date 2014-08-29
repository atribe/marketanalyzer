package com.ar.marketanalyzer.ibd50.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50RankRepository;
import com.ar.marketanalyzer.ibd50.services.interfaces.Ibd50RankServiceInterface;
import com.ar.marketanalyzer.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.securities.models.Symbol;
import com.ar.marketanalyzer.securities.services.interfaces.SymbolServiceInterface;

@Service
public class Ibd50RankService implements Ibd50RankServiceInterface{

	@Resource
	private Ibd50RankRepository ibd50RankingRepo;
	
	@Autowired
	private SymbolServiceInterface tickerSymbolService;
	
	/**
	 * Looks up the provided ticker from the ticker db.
	 * First it looks to see if the ticker ID is set and uses it.
	 * If the ID is null it then looks up the symbol
	 * 
	 * @param ticker
	 * @return the ticker from the DB that matches the provided ticker.
	 * @throws SecuritiesNotFound This is thrown if the ticker is not found by either ID or symbol.
	 */
	private Symbol lookUpTicker(Symbol ticker) throws SecuritiesNotFound {
		Symbol foundTicker;
		
		try {
			if( ticker.getId() != null ) {
				foundTicker = tickerSymbolService.findById(ticker.getId());
			} else {
				foundTicker = tickerSymbolService.findBySymbol(ticker.getSymbol());
			}
		} catch (SecuritiesNotFound e) {
			throw e;
		}
				
		return foundTicker;
	}
	
	@Override
	@Transactional
	public Ibd50Rank create(Ibd50Rank ibd50Ranking) {
		Ibd50Rank createdIbd50Ranking = ibd50Ranking;
		return ibd50RankingRepo.save(createdIbd50Ranking);
	}

	@Override
	@Transactional
	public Ibd50Rank findById(int id) {
		return ibd50RankingRepo.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50Rank delete(int id) throws SecuritiesNotFound {
		Ibd50Rank deletedIbd50Ranking = ibd50RankingRepo.findOne(id);
		
		if(deletedIbd50Ranking == null) {
			throw new SecuritiesNotFound();
		} 

		ibd50RankingRepo.delete(id);
		
		return deletedIbd50Ranking;	
	}

	@Override
	@Transactional
	public List<Ibd50Rank> findAll() {
		return ibd50RankingRepo.findAll();
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws SecuritiesNotFound {
		Ibd50Rank updatedIbd50Ranking = ibd50RankingRepo.findOne(ibd50Ranking.getId());
		
		if( updatedIbd50Ranking == null) {
			throw new SecuritiesNotFound();
		}
		
		//TODO Filler until I really write this code
		updatedIbd50Ranking.setRank(ibd50Ranking.getRank());
		
		return updatedIbd50Ranking;
	}

	@Override
	@Transactional(rollbackFor=SecuritiesNotFound.class)
	public List<Ibd50Rank> findByRankAndTicker(int rank, Symbol ticker) throws SecuritiesNotFound {
		Symbol foundTickerSymbol;
		try {
			foundTickerSymbol = lookUpTicker(ticker);								// First check to see if the ticker is valid
		} catch (SecuritiesNotFound e) {													// Catch if it is not
			throw e;																// Throw that exception up to the next level to be dealt with.
		}
		
		List<Ibd50Rank> rankingList = ibd50RankingRepo.findByRankAndTickerOrderByRankDateAsc(rank, foundTickerSymbol);	//Using the found ticker look up for the provided rank
		
		if(rankingList.isEmpty()) {													// Check if any were found with the desired rank and ticker
			throw new SecuritiesNotFound("The Ticker '" + foundTickerSymbol.getSymbol()	// If not throw an exception with the problem explained in the message 
					+ "' was not found with rank " + rank + " in the Rank DB");
		}
		
		return rankingList ;														// If results were found then return them
	}

	@Override
	public List<Ibd50Rank> findByModificationTimeAfter(Date date) throws SecuritiesNotFound {
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByModificationTimeAfter(date);
		if( foundRanking == null) {
			throw new SecuritiesNotFound();
		}
		return foundRanking;
	}

	
	@Override
	@Transactional
	public List<Ibd50Rank> findByRankBetweenAndActiveTrue(int startRank,	int endRank) throws SecuritiesNotFound{
		List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByRankBetweenAndActiveRankingTrue(startRank, endRank);
		
		if( foundRanking == null) {
			throw new SecuritiesNotFound();
		}
		
		return foundRanking;
	}
	
	@Override
	@Transactional
	public Ibd50Rank findByTickerAndCurrentRankTrue(Symbol ticker) throws SecuritiesNotFound, Ibd50TooManyFound {
		
		Symbol foundTickerSymbol;
		try {
			foundTickerSymbol = lookUpTicker(ticker);								// First check to see if the ticker is valid
		} catch (SecuritiesNotFound e) {													// Catch if it is not
			throw e;																// Throw that exception up to the next level to be dealt with.
		}
		
		List<Ibd50Rank> rankingList = ibd50RankingRepo.findByTickerAndActiveRankingTrue(foundTickerSymbol);
		
		if(rankingList.isEmpty()) {													// Check if any were found with the desired rank and ticker
			throw new SecuritiesNotFound("The Ticker '" + foundTickerSymbol.getSymbol()	// If not throw an exception with the problem explained in the message 
					+ "' was not found with any current ranks.");
		} else if (rankingList.size() > 1) {
			throw new Ibd50TooManyFound("To many current=true for ticker " + foundTickerSymbol.getSymbol() 
					+ ". Go check your code to make sure they are being deactivated properly.");
		}
		
		return rankingList.get(0);
	}

	@Override
	@Transactional
	public void deactivateAllCurrentRankings() {
		ibd50RankingRepo.deactivateActiveRanking();
	}

	public List<Ibd50Rank> parseIbd50HTMLToBeanList(InputStream downloadedFileInputStream) throws IOException {
		List<Ibd50Rank> rowsFromIBD50 = new ArrayList<Ibd50Rank>();
		
		Document doc = Jsoup.parse(downloadedFileInputStream, "UTF-8", "");
		Elements rows = doc.getElementsByTag("table").get(0).getElementsByTag("tr");
		
		//loop throw all the rows of the table
		for(Element row : rows) {
			//Get all the cells in the row
			Elements cells = row.getElementsByTag("td");
			List<String> rowOfStrings = new ArrayList<String>();
			
			if( cells.hasText()) {			//if the list cells is not empty (aka it isn't the header row)
				//Loop through all the cells in the row
				for(Element cell : cells) {
					//Add every cell of the row to a List<String>
					String cellContents = cell.getElementsByTag("td").text();
					if( !cellContents.equalsIgnoreCase("N/A") ) {
						rowOfStrings.add(cellContents);
					} else {
						rowOfStrings.add(null);
					}
				}
				//parse the List<String> into a bean and then add it to the list<bean>
				rowsFromIBD50.add(parseListToBean(rowOfStrings));
			}
		}
		
		//return the list of beans
		return rowsFromIBD50;
	}
	
	/**
	 * Turns a IBD50 List of Strings into a IBD50 Ranking Bean
	 * 
	 * @param ibd50tokenizedList
	 * @return IBD50 Ranking Bean
	 */
	private Ibd50Rank parseListToBean(List<String> ibd50tokenizedList) {
		Ibd50Rank ibdRow = new Ibd50Rank();
		
		Symbol company = new Symbol(ibd50tokenizedList.get(0),ibd50tokenizedList.get(1) , "Stock");
				
		ibdRow.setSymbol(company);
		ibdRow.setActiveRanking(Boolean.TRUE);
		ibdRow.setRank(parseIntOrNull(ibd50tokenizedList.get(2)));
		ibdRow.setCurrentPrice(new BigDecimal(ibd50tokenizedList.get(3)));
		ibdRow.setPriceChange(new BigDecimal(ibd50tokenizedList.get(4)));
		ibdRow.setPricePercentChange(parseDoubleOrNull(ibd50tokenizedList.get(5)));
		ibdRow.setPercentOffHigh(parseDoubleOrNull(ibd50tokenizedList.get(6)));
		ibdRow.setVolume(parseLongOrNull(ibd50tokenizedList.get(7)));
		ibdRow.setVolumePercentChange(parseDoubleOrNull(ibd50tokenizedList.get(8)));
		ibdRow.setCompositeRating(parseDoubleOrNull(ibd50tokenizedList.get(9)));
		ibdRow.setEpsRating(parseDoubleOrNull(ibd50tokenizedList.get(10)));
		ibdRow.setRsRating(parseDoubleOrNull(ibd50tokenizedList.get(11)));
		ibdRow.setSmrRating(ibd50tokenizedList.get(12));
		ibdRow.setAccDisRating(ibd50tokenizedList.get(13));
		ibdRow.setGroupRelStrRating(ibd50tokenizedList.get(14));
		ibdRow.setEpsPercentChangeLastQtr(parseDoubleOrNull(ibd50tokenizedList.get(15)));
		ibdRow.setEpsPercentChangePriorQtr(parseDoubleOrNull(ibd50tokenizedList.get(16)));
		ibdRow.setEpsPercentChangeCurrentQtr(parseDoubleOrNull(ibd50tokenizedList.get(17)));
		ibdRow.setEpsEstPercentChangeCurrentYear(parseDoubleOrNull(ibd50tokenizedList.get(18)));
		ibdRow.setSalesPercentChangeLastQtr(parseDoubleOrNull(ibd50tokenizedList.get(19)));
		ibdRow.setAnnualROELastYear(parseDoubleOrNull(ibd50tokenizedList.get(20)));
		ibdRow.setAnnualProfitMarginLatestYear(parseDoubleOrNull(ibd50tokenizedList.get(21)));
		ibdRow.setManagmentOwnPercent(parseDoubleOrNull(ibd50tokenizedList.get(22)));
		ibdRow.setQtrsRisingSponsorship(parseIntOrNull(ibd50tokenizedList.get(23)));
		
		return ibdRow;
	}
	private Double parseDoubleOrNull(String str) {
		return str != null ? Double.parseDouble(str) : null;
	}
	
	private Integer parseIntOrNull(String str) {
		return str != null ? Integer.parseInt(str) : null;
	}
	
	private Long parseLongOrNull(String str) {
		return str != null ? Long.parseLong(str) : null;
	}
}
