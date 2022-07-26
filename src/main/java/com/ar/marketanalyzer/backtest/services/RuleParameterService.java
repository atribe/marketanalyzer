package com.ar.marketanalyzer.backtest.services;

import com.ar.marketanalyzer.backtest.exceptions.ModelNotFound;
import com.ar.marketanalyzer.backtest.models.RuleParameter;
import com.ar.marketanalyzer.backtest.repo.RuleParameterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RuleParameterService {

    private final RuleParameterRepo paramRepo;

    @Transactional
    public RuleParameter create(RuleParameter rule) {
        return paramRepo.save(rule);
    }

    @Transactional
    public void batchCreate(List<RuleParameter> paramList) {
        paramRepo.saveAll(paramList);
    }

    @Transactional
    public RuleParameter update(RuleParameter rule) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public RuleParameter delete(int id) throws ModelNotFound {
        RuleParameter resultToDelete = findById(id);

        // if the findById method fails, then exception thrown and this code not run
        paramRepo.deleteById(id);

        return resultToDelete;
    }

    @Transactional
    public RuleParameter findById(int id) throws ModelNotFound {
        return paramRepo.findById(id).orElseThrow(ModelNotFound::new);
    }
}
