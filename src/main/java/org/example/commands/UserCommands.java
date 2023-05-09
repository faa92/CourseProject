package org.example.commands;

import org.example.exceptions.BusinessException;
import org.example.managers.CSVDataManager;
import org.example.models.ExchangeRate;
import org.example.utils.PropertiesReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCommands implements IUserCommands {

    private PropertiesReader propertiesReader = new PropertiesReader();

    @Override
    public void listExchangeRates(LocalDate date) {
        CSVDataManager csvDataManager = new CSVDataManager(date);
        List<ExchangeRate> exchangeRateList = csvDataManager.read();
        if (exchangeRateList.isEmpty()) {
            throw BusinessException.invalidDataNotPresent();
        } else {
            String pattern = "%-6s %-10s %-10s";
            System.out.println(pattern.formatted("Валюта", "Покупка", "Продажа"));
            for (ExchangeRate rate : exchangeRateList) {
                System.out.println(pattern.formatted(
                        rate.getCurrency(),
                        rate.getRateBuy(),
                        rate.getRateSell()
                ));
            }
        }
    }

    @Override
    public void exchange(LocalDate date, BigDecimal cash, Currency startCurrency, Currency endCurrency) {
        CSVDataManager csvDataManager = new CSVDataManager(date);
        List<ExchangeRate> exchangeRateList = csvDataManager.read();
        if (exchangeRateList.isEmpty()) {
            throw BusinessException.invalidDataNotPresent();
        }
        if (cash.compareTo(BigDecimal.ZERO) < 0) throw BusinessException.invalidSourceMoneyAmountFormat();
        String localCurrency = propertiesReader.getProperty(PropertiesReader.AppProperties.LOCAL_CURRENCY);
        Currency sourceLocalCurrency;
        if (localCurrency == null) {
            throw BusinessException.invalidSourceCurrencyCode();
        } else {
            try {
                sourceLocalCurrency = Currency.getInstance(localCurrency);
            } catch (IllegalArgumentException illegalArgumentException) {
                throw BusinessException.invalidSourceCurrencyCode();
            }
        }

        Map<Currency, ExchangeRate> currencyToRate = new HashMap<>();
        for (ExchangeRate rate : exchangeRateList) {
            currencyToRate.put(Currency.getInstance(rate.getCurrency()), rate);
        }

        BigDecimal sourceBuyRate = sourceLocalCurrency.equals(startCurrency)
                ? BigDecimal.ONE
                : currencyToRate.get(startCurrency).getRateBuy();

        BigDecimal targetSellRate = sourceLocalCurrency.equals(endCurrency)
                ? BigDecimal.ONE
                : currencyToRate.get(startCurrency).getRateSell();

        BigDecimal exchangedCash = cash.multiply(sourceBuyRate)
                .divide(targetSellRate, endCurrency.getDefaultFractionDigits(), RoundingMode.DOWN);

        System.out.println(exchangedCash);
    }
}
