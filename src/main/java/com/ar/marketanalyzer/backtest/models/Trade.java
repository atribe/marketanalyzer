package com.ar.marketanalyzer.backtest.models;

import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "backtest_trade")
public class Trade extends PersistableEntityInt {

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private AbstractModel model;

    @Column(name = "buy_date")
    private LocalDateTime buyDate;

    @Column(name = "sell_date")
    private LocalDateTime sellDate;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal buyPrice;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal sellPrice;

    /*
     * Constructors
     */
    public Trade(AbstractModel model) {
        this.model = model;
    }

    /*
     * Helper Methods
     */
    public void sell(LocalDateTime date, BigDecimal sellPrice) {
        setSellDate(date);
        setSellPrice(sellPrice);
    }

    public void buy(LocalDateTime date, BigDecimal buyPrice) {
        setBuyDate(date);
        setBuyPrice(buyPrice);
    }

    @Override
    public String toString() {
        return "Buy:" + buyDate.toString() + " Sell:" + sellDate.toString();
    }
}
