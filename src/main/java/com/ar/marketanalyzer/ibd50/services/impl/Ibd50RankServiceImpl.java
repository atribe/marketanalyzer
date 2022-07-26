package com.ar.marketanalyzer.ibd50.services.impl;

import com.ar.marketanalyzer.core.securities.exceptions.SecuritiesNotFound;
import com.ar.marketanalyzer.core.securities.models.Symbol;
import com.ar.marketanalyzer.core.securities.services.interfaces.SymbolServiceInterface;
import com.ar.marketanalyzer.ibd50.exceptions.Ibd50TooManyFound;
import com.ar.marketanalyzer.ibd50.models.Ibd50Rank;
import com.ar.marketanalyzer.ibd50.repositories.Ibd50RankRepository;
import com.ar.marketanalyzer.ibd50.services.Ibd50RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Ibd50RankServiceImpl implements Ibd50RankService {

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
            if (ticker.getId() != null) {
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
        return ibd50RankingRepo.findById(id).orElseThrow(SecuritiesNotFound::new);
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public Ibd50Rank delete(int id) throws SecuritiesNotFound {
        Ibd50Rank deletedIbd50Ranking = ibd50RankingRepo.findById(id).orElseThrow(SecuritiesNotFound::new);

        ibd50RankingRepo.deleteById(id);

        return deletedIbd50Ranking;
    }

    @Override
    @Transactional
    public List<Ibd50Rank> findAll() {
        return ibd50RankingRepo.findAll();
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public Ibd50Rank update(Ibd50Rank ibd50Ranking) throws SecuritiesNotFound {
        Ibd50Rank updatedIbd50Ranking = ibd50RankingRepo.findById(ibd50Ranking.getId()).orElseThrow(SecuritiesNotFound::new);

        //TODO Filler until I really write this code
        updatedIbd50Ranking.setRanking(ibd50Ranking.getRanking());

        return updatedIbd50Ranking;
    }

    @Override
    @Transactional(rollbackFor = SecuritiesNotFound.class)
    public List<Ibd50Rank> findByRankAndTicker(int rank, Symbol ticker) throws SecuritiesNotFound {
        Symbol foundTickerSymbol;
        try {
            foundTickerSymbol = lookUpTicker(ticker);                                // First check to see if the ticker is valid
        } catch (SecuritiesNotFound e) {                                                    // Catch if it is not
            throw e;                                                                // Throw that exception up to the next level to be dealt with.
        }

        List<Ibd50Rank> rankingList = ibd50RankingRepo.findByRankingAndTickerOrderByRankDateAsc(rank,
                                                                                                foundTickerSymbol);    //Using the found ticker look up for the provided rank

        if (rankingList.isEmpty()) {                                                    // Check if any were found with the desired rank and ticker
            throw new SecuritiesNotFound("The Ticker '"
                                                 + foundTickerSymbol.getSymbol()
                                                 // If not throw an exception with the problem explained in the message
                                                 + "' was not found with rank "
                                                 + rank
                                                 + " in the Rank DB");
        }

        return rankingList;                                                        // If results were found then return them
    }

    @Override
    public List<Ibd50Rank> findByModificationTimeAfter(LocalDateTime date) throws SecuritiesNotFound {
        List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByModificationTimeAfter(date);
        if (foundRanking == null) {
            throw new SecuritiesNotFound();
        }
        return foundRanking;
    }


    @Override
    @Transactional
    public List<Ibd50Rank> findByRankBetweenAndActiveTrue(int startRank, int endRank) throws SecuritiesNotFound {
        List<Ibd50Rank> foundRanking = ibd50RankingRepo.findByRankingBetweenAndActiveRankingTrue(startRank, endRank);

        if (foundRanking == null) {
            throw new SecuritiesNotFound();
        }

        return foundRanking;
    }

    @Override
    @Transactional
    public Ibd50Rank findByTickerAndCurrentRankTrue(Symbol ticker) throws SecuritiesNotFound, Ibd50TooManyFound {

        Symbol foundTickerSymbol;
        try {
            foundTickerSymbol = lookUpTicker(ticker);                                // First check to see if the ticker is valid
        } catch (SecuritiesNotFound e) {                                                    // Catch if it is not
            throw e;                                                                // Throw that exception up to the next level to be dealt with.
        }

        List<Ibd50Rank> rankingList = ibd50RankingRepo.findByTickerAndActiveRankingTrue(foundTickerSymbol);

        if (rankingList.isEmpty()) {                                                    // Check if any were found with the desired rank and ticker
            throw new SecuritiesNotFound("The Ticker '"
                                                 + foundTickerSymbol.getSymbol()
                                                 // If not throw an exception with the problem explained in the message
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
}
