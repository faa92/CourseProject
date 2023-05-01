package org.example.commands;

import org.example.models.ExchangeRate;

import java.time.LocalDate;

public interface IAdminCommands {
    void putExchangeRate(LocalDate date, ExchangeRate exchangeRate);

    void deleteExchangeRate(LocalDate date, String currency);
}
