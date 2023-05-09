package org.example.models;

import com.opencsv.bean.CsvBindByPosition;

import java.math.BigDecimal;
import java.util.Currency;

public class ExchangeRate {
    @CsvBindByPosition(position = 0)
    private String currency;
    @CsvBindByPosition(position = 1)
    private BigDecimal rateBuy;
    @CsvBindByPosition(position = 2)
    private BigDecimal rateSell;

    public ExchangeRate() {
    }

    public ExchangeRate(Currency currency, BigDecimal rateBuy, BigDecimal rateSell) {
        this.currency = currency.getCurrencyCode();
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

}
