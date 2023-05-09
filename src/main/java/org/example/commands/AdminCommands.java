package org.example.commands;

import org.example.managers.CSVDataManager;
import org.example.models.ExchangeRate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AdminCommands implements IAdminCommands {

    public void putExchangeRate(LocalDate date, ExchangeRate exchangeRate) {
        CSVDataManager csvDataManager = new CSVDataManager(date);
        List<ExchangeRate> exchangeRateList = csvDataManager.read();
        Optional<ExchangeRate> foundedRate = exchangeRateList.stream().filter(rate ->
                rate.getCurrency().equals(exchangeRate.getCurrency())).findFirst();

        if (foundedRate.isPresent()) {
            exchangeRateList.remove(foundedRate.get());
        } else {
            exchangeRateList.add(exchangeRate);
        }

        csvDataManager.write(exchangeRateList);

    }

    public void deleteExchangeRate(LocalDate date, String currency) {
        CSVDataManager csvDataManager = new CSVDataManager(date);
        List<ExchangeRate> exchangeRateList = csvDataManager.read();
        Optional<ExchangeRate> foundedRate = exchangeRateList.stream().filter(exchangeRate ->
                exchangeRate.getCurrency().equals(currency)).findFirst();

        foundedRate.ifPresent(exchangeRateList::remove);
        csvDataManager.write(exchangeRateList);
    }

}
