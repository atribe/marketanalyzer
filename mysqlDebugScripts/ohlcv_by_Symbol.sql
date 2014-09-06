SELECT s.id, symbol, name, type, o.id, date, open, high, low, close, volume FROM marketpred.securities_ohlcv o
INNER JOIN marketpred.securities_symbols s ON o.symbol_id = s.id
WHERE s.symbol="^IXIC";