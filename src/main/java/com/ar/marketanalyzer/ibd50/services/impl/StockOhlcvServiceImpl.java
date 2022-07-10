package com.ar.marketanalyzer.ibd50.services.impl;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import com.ar.marketanalyzer.ibd50.models.StockOhlcv;
import com.ar.marketanalyzer.ibd50.repositories.StockOhlcvRepository;
import com.ar.marketanalyzer.ibd50.services.StockOhlcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class StockOhlcvServiceImpl implements StockOhlcvService {

    @Resource
    private StockOhlcvRepository stockOhlcvRepo;
    @Autowired
    private SymbolServiceInterface tickerSymbolService;

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public StockOhlcv delete(int id) throws SecuritiesNotFound {
        StockOhlcv deletedStockOhlcv = stockOhlcvRepo.findById(id).orElseThrow(SecuritiesNotFound::new);

        stockOhlcvRepo.deleteById(id);

        return deletedStockOhlcv;
    }

    @Override
    @Transactional
    public StockOhlcv create(StockOhlcv stockOhlcv) {

        StockOhlcv createdStockOhlcv = stockOhlcv;
        Symbol ticker = createdStockOhlcv.getTicker();                                //Get the ticker from the ohlcvList

        Symbol foundTickerSymbol = getOrCreateSymbol(ticker);                    //create a variable to hold the ticker from the db

        createdStockOhlcv.setTicker(foundTickerSymbol);

        return stockOhlcvRepo.save(createdStockOhlcv);
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public void batchInsert(List<StockOhlcv> ohlcvList) {

        Symbol ticker = ohlcvList.get(0).getTicker();                                //Get the ticker from the ohlcvList

        Symbol foundTickerSymbol = getOrCreateSymbol(ticker);                        //create a variable to hold the ticker from the db

        for (StockOhlcv ohlcv : ohlcvList) {
            ohlcv.setTicker(foundTickerSymbol);                                            //cycle through the list of ohlcv and add the ticker from the db
        }

        stockOhlcvRepo.saveAll(ohlcvList);                                                    //batch add
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public StockOhlcv update(StockOhlcv stockOhlcv) throws SecuritiesNotFound {
        StockOhlcv updatedStockOhlcv = stockOhlcvRepo.findById(stockOhlcv.getId()).orElseThrow(SecuritiesNotFound::new);

        if (updatedStockOhlcv == null) {
            throw new SecuritiesNotFound();
        }

        //TODO Filler until I really write this code
        //updatedStockOhlcv.setRank(StockOhlcv.getRank());

        return updatedStockOhlcv;
    }

    @Override
    @Transactional
    public List<StockOhlcv> findAll() {
        return stockOhlcvRepo.findAll();
    }

    @Override
    @Transactional
    public StockOhlcv findById(int id) {
        return stockOhlcvRepo.findById(id).orElseThrow(SecuritiesNotFound::new);
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public List<StockOhlcv> findByTicker(Symbol ticker) throws SecuritiesNotFound {
        Symbol foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());

        if (foundTickerSymbol == null) {
            throw new SecuritiesNotFound("By Ticker search for "
                                                 + ticker.getSymbol()
                                                 + " failed because the Ticker was not found in the Ticker DB.");
        }

        List<StockOhlcv> ohlcvList = stockOhlcvRepo.findByTicker(ticker);

        if (ohlcvList.isEmpty()) {
            throw new SecuritiesNotFound("The Ticker '" + ticker.getSymbol() + "' was not found in the stock ohlcv db.");
        }

        return ohlcvList;
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public StockOhlcv findByTickerAndDate(Symbol ticker, LocalDateTime date) throws SecuritiesNotFound {
        StockOhlcv ohlcv = stockOhlcvRepo.findByTickerAndDate(ticker, date);

        if (ohlcv == null) {
            throw new SecuritiesNotFound("The Ticker '"
                                                 + ticker.getSymbol()
                                                 + "' was not found with date "
                                                 + date.toString()
                                                 + " in the stock ohlcv db.");
        }

        return ohlcv;
    }

    @Override
    public List<StockOhlcv> findByTickerAndDateAfter(Symbol ticker, LocalDateTime date) throws SecuritiesNotFound {

        List<StockOhlcv> ohlcvList = stockOhlcvRepo.findByTickerAndDateAfterOrderByDateDesc(ticker, date);

        if (ohlcvList.isEmpty()) {
            throw new SecuritiesNotFound("The Ticker '"
                                                 + ticker.getSymbol()
                                                 + "' was not found after date "
                                                 + date.toString()
                                                 + " in the stock ohlcv db.");
        }

        return ohlcvList;
    }

    /*
     * Helper Methods
     */
    private Symbol getOrCreateSymbol(Symbol ticker) {
        Symbol foundTickerSymbol;                                                        //create a variable to hold the ticker from the db
        try {
            foundTickerSymbol = tickerSymbolService.findBySymbol(ticker.getSymbol());        //find the ticker in the db
        } catch (SecuritiesNotFound e) {
            foundTickerSymbol = tickerSymbolService.createOrFindDuplicate(ticker);
        }

        return foundTickerSymbol;
    }
}
