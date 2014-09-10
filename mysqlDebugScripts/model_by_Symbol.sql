SELECT 
    m.MODEL_NAME,
    m.id,
    m.defaultInitialInvestment,
    m.end_date,
    m.initial_investment,
    m.model_status,
    m.start_date,
    s.symbol
FROM
    marketpred.backtest_model m
        INNER JOIN
    marketpred.securities_symbols s ON m.symbol_id = s.id;
