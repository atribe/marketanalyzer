package com.ar.marketanalyzer.backtest.models;

import com.ar.marketanalyzer.backtest.models.comparables.DateCompImp;
import com.ar.marketanalyzer.backtest.models.models.AbstractModel;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "backtest_value")
public class DollarValue extends PersistableEntityInt implements DateCompImp {

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private AbstractModel model;

    private LocalDateTime date;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal dollarValue;

    public DollarValue(LocalDateTime date) {
        this.date = date;
    }

    /*
     * Helper Methods
     */
    @Override
    public String toString() {
        return "Date:" + date.toString() + " Value:" + dollarValue;
    }

    @Override
    public int compareTo(DateCompImp o) {
        if (o != null) {
            return this.date.compareTo(o.getDate());
        } else {
            return -1;
        }
    }
}
