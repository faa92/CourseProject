package org.example.commands;

import org.example.controller.IUserCommands;
import org.example.exceptions.BusinessException;
import org.example.managers.CSVDataManager;
import org.example.models.ExchangeRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class UserCommands implements IUserCommands {
    @Override
    public void listExchangeRates(LocalDate date) {
        CSVDataManager csvDataManager = new CSVDataManager(date);
        List<ExchangeRate> exchangeRateList = csvDataManager.read();
        if (exchangeRateList.isEmpty()){
            throw BusinessException.invalidDataNotPresent();
        }
        System.out.println();//todo implerment лист
    }

    @Override
    public void exchange(LocalDate date, BigDecimal cash, Currency startCurrency, Currency endCurrency) {
        CSVDataManager csvDataManager = new CSVDataManager(date);  // LJLTKFNM!!!!

    }
}
