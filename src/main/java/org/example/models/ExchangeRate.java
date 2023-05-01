package org.example.models;

import com.opencsv.bean.CsvBindByPosition;

import java.util.Currency;

public class ExchangeRate {
    @CsvBindByPosition(position = 0)
    private String currency;
    @CsvBindByPosition(position = 1)
    private double rateBuy;
    @CsvBindByPosition(position = 2)
    private double rateSell;

    public ExchangeRate() {
    }

    public ExchangeRate(Currency currency, double rateBuy, double rateSell) {
        this.currency = currency.getCurrencyCode();
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
    }
    public String getCurrency() {
        return currency;
    }

    public void setRateBuy(double rateBuy) {
        this.rateBuy = rateBuy;
    }

    public void setRateSell(double rateSell) {
        this.rateSell = rateSell;
    }
}
