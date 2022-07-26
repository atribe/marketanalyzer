package com.ar.marketanalyzer.core.securities.models;

import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityLong;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "securities_ohlcv")
public class SecuritiesOhlcv extends PersistableEntityLong {

    @ManyToOne(optional = false)//optional=false makes this an inner join, true would be Outer join
    @JoinColumn(name = "symbol_id", referencedColumnName = "id")
    private Symbol symbol;

    @Column
    private LocalDateTime date;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal open;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal high;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal low;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal close;

    @Column(precision = 0)
    private long volume;

    @Column(name = "adjusted_close", precision = 12, scale = 2)
    private BigDecimal adjClose;

    /*
     * Constructors
     */
    public SecuritiesOhlcv(YahooOHLCV y, Symbol ticker) {
        symbol = ticker;                                                //Setting the ticker to the passed ticker

        setDate(y.getDate());

        open = BigDecimal.valueOf(y.getOpen());
        high = BigDecimal.valueOf(y.getHigh());
        low = BigDecimal.valueOf(y.getLow());
        close = BigDecimal.valueOf(y.getClose());
        setVolume(y.getVolume());
        adjClose = BigDecimal.valueOf(y.getAdjClose());
    }

    public SecuritiesOhlcv(String symbol,
                           LocalDateTime date,
                           BigDecimal open,
                           BigDecimal high,
                           BigDecimal low,
                           BigDecimal close,
                           long volume,
                           BigDecimal adjClose) {
        //setSymbol(symbol);
        setDate(date);
        setOpen(open);
        setHigh(high);
        setLow(low);
        setClose(close);
        setVolume(volume);
        setAdjClose(adjClose);
    }
    //End Constructors

    /*
     * Getters and Setters
     */
    @Override
    public String toString() {
        return symbol.getSymbol()
                + "("
                + date.toString()
                + " O:"
                + open
                + " H:"
                + high
                + " L:"
                + low
                + " C:"
                + close
                + " V:"
                + volume
                + ")";
        //return ToStringBuilder.reflectionToString(this);
    }

    public void setDate(String date) {
        this.date = LocalDateTime.parse(date);//Not the most elegant way to do it
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setVolume(double volume) {
        this.volume = Math.round(volume);
    }
}
