package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.Trade;
import com.ar.marketanalyzer.backtest.repo.TradeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TradeService {
    private final TradeRepo tradeRepo;

    @Transactional
    public Trade create(Trade trade) {
        return tradeRepo.save(trade);
    }

    @Transactional
    public void batchCreate(List<Trade> resultList) {
        tradeRepo.saveAll(resultList);
    }

    @Transactional
    public Trade update(Trade trade) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public Trade delete(int id) throws ModelNotFound {
        Trade resultToDelete = findById(id);

        // if the findById method fails, then exception thrown and this code not run
        tradeRepo.deleteById(id);

        return resultToDelete;
    }

    @Transactional
    public Trade findById(int id) throws ModelNotFound {
        return tradeRepo.findById(id).orElseThrow(ModelNotFound::new);
    }
}
