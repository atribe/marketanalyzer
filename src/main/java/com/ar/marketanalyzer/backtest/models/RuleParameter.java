package com.ar.marketanalyzer.backtest.models;

import com.ar.marketanalyzer.backtest.models.rules.AbstractRule;
import com.ar.marketanalyzer.core.securities.models.parents.PersistableEntityInt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "backtest_rule_parameters")
public class RuleParameter extends PersistableEntityInt {

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_value")
    private String value;

    @ManyToOne
    private AbstractRule rule;

    /*
     * Constructors
     */

    /**
     * Optional parameters: String name, whatever value
     */
    public RuleParameter(AbstractRule rule) {
        this.rule = rule;
    }

    /**
     * @param name
     * @param value
     */
    public RuleParameter(AbstractRule rule, String name, String value) {
        this.rule = rule;
        setParameterName(name);
        setValue(value);
    }

    public RuleParameter(AbstractRule rule, String name, double value) {
        this.rule = rule;
        setParameterName(name);
        setValue(value);
    }

    public RuleParameter(AbstractRule rule, String name, int value) {
        this.rule = rule;
        setParameterName(name);
        setValue(value);
    }

    public RuleParameter(AbstractRule rule, String name, boolean value) {
        this.rule = rule;
        setParameterName(name);
        setValue(value);
    }

    @Override
    public String toString() {
        return parameterName + ": " + value;
    }

    /*
     * Additional Getters and Setters
     */

    public int getValueInt() {
        return Integer.getInteger(value);
    }

    public double getValueDouble() {
        return Double.parseDouble(value);
    }

    public BigDecimal getValueBigDecimal() {
        return new BigDecimal(value);
    }

    private void setValue(String value) {
        this.value = value;
    }

    public void setValue(boolean value) {
        this.value = String.valueOf(value);
    }

    public void setValue(double value) {
        this.value = String.valueOf(value);
    }

    public void setValue(int value) {
        this.value = String.valueOf(value);
    }
}
