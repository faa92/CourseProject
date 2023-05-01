package org.example.utils;

import org.example.exceptions.BusinessException;
import org.example.exceptions.DateFormatException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Currency;

public class ExchangeFormats {
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException dateTimeParseException) {
            throw BusinessException.invalidDailyRatesDateFormat();
        }
    }

    public static Currency parseCurrency(String currency) {
        try {
            return Currency.getInstance(currency);
        } catch (IllegalArgumentException exception) {
            throw BusinessException.exchangeRateNotFound();
        }
    }

    public static double parseExchangeRate(String rate) {
        try {
            double parsedRate = Double.parseDouble(rate);
            if (parsedRate <= 0){
                throw BusinessException.invalidBuyRate();
            }
            return parsedRate;
        } catch (NumberFormatException exception) {
            throw BusinessException.invalidBuyRateFormat();
        }
    }

    public static String formatDate(LocalDate date) {
        try {
            return date.format(dateFormat);
        } catch (DateTimeParseException dateTimeParseException) {
            throw BusinessException.invalidDailyRatesDateFormat();
        }
    }

    public static BigDecimal parseCash(String cash) {
        return null; //todo   парсиенг, ошибкибб отрицательное или нет
    }
}
