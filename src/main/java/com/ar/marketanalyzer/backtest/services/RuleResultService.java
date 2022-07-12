package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.ruleresults.AbstractRuleResult;
import com.ar.marketanalyzer.backtest.repo.RuleResultRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SortedSet;

@RequiredArgsConstructor
@Service
public class RuleResultService {

    private final RuleResultRepo resultRepo;

    @Transactional
    public AbstractRuleResult create(AbstractRuleResult rule) {

        return resultRepo.save(rule);
    }

    @Transactional
    public void batchCreate(SortedSet<AbstractRuleResult> resultList) {
        resultRepo.saveAll(resultList);
    }

    @Transactional
    public AbstractRuleResult update(AbstractRuleResult rule) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public AbstractRuleResult delete(int id) throws ModelNotFound {
        AbstractRuleResult resultToDelete = findById(id);

        // if the findById method fails, then exception thrown and this code not run
        resultRepo.deleteById(id);

        return resultToDelete;
    }

    @Transactional
    public AbstractRuleResult findById(int id) throws ModelNotFound {
        return resultRepo.findById(id).orElseThrow(ModelNotFound::new);
    }
}
