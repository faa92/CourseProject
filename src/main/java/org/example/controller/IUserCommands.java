package org.example.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public interface IUserCommands {
    void listExchangeRates(LocalDate date);

    void exchange(LocalDate date, BigDecimal cash, Currency startCurrency, Currency endCurrency);
}
