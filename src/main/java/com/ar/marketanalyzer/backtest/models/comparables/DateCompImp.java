package com.ar.marketanalyzer.backtest.models.comparables;

import java.time.LocalDateTime;

public interface DateCompImp extends Comparable<DateCompImp> {
    public LocalDateTime getDate();

    public void setDate(LocalDateTime date);
}
