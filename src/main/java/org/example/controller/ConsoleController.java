package org.example.controller;

import org.example.commands.AdminCommands;
import org.example.commands.IAdminCommands;
import org.example.commands.IUserCommands;
import org.example.commands.UserCommands;
import org.example.exceptions.BusinessException;
import org.example.models.ExchangeRate;
import org.example.utils.ExchangeFormats;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class ConsoleController implements ApplicationController {
    private IAdminCommands adminCommands = new AdminCommands();
    private IUserCommands userCommands = new UserCommands();
    @Override
    public void execute(String command, List<String> arguments) {
        switch (command) {
            case "admin/putExchangeRate" -> {
                LocalDate date = ExchangeFormats.parseDate(arguments.get(0));
                Currency currency = ExchangeFormats.parseCurrency(arguments.get(1));
                BigDecimal rateBuy = ExchangeFormats.parseExchangeRate(arguments.get(2));
                BigDecimal rateSell = ExchangeFormats.parseExchangeRate(arguments.get(3));
                ExchangeRate exchangeRate = new ExchangeRate(currency, rateBuy, rateSell);

                adminCommands.putExchangeRate(date, exchangeRate);
            }
            case "admin/removeExchangeRate" -> {
                LocalDate date = ExchangeFormats.parseDate(arguments.get(0));
                Currency currency = ExchangeFormats.parseCurrency(arguments.get(1));
                adminCommands.deleteExchangeRate(date, currency.getCurrencyCode());
            }
            case "listExchangeRates" -> {
                LocalDate date = ExchangeFormats.parseDate(arguments.get(0));
                userCommands.listExchangeRates(date);
            }
            case "exchange" -> {
                LocalDate date = ExchangeFormats.parseDate(arguments.get(0));
                BigDecimal cash = ExchangeFormats.parseCash(arguments.get(1));
                Currency startCurrency = ExchangeFormats.parseCurrency(arguments.get(2));
                Currency endCurrency = ExchangeFormats.parseCurrency(arguments.get(3));
                userCommands.exchange(date, cash, startCurrency, endCurrency);
            }
            default -> throw BusinessException.invalidCommandFormat();
        }

    }
}
