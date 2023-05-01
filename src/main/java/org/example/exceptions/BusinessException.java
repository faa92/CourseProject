package org.example.exceptions;

public class BusinessException extends RuntimeException {

    private BusinessException(String displayMessage) {
        super(displayMessage);
    }

    public static BusinessException illegalLocalCurrencyAction() {
        return new BusinessException("Невозможное действие для местной валюты");
    }

    public static BusinessException exchangeRateNotFound() {
        return new BusinessException("Записи не существует");
    }

    public static BusinessException invalidBuyRate() {
        return new BusinessException("Неверное значение курса");
    }

    public static BusinessException invalidSourceMoneyAmount() {
        return new BusinessException("Неверное значение исходной денежной суммы");
    }


    public static BusinessException invalidCommandFormat() {
        return new BusinessException("Неверный формат команды");
    }

    public static BusinessException commandNotFound() {
        return new BusinessException("Неизвестная команда");
    }

    public static BusinessException invalidDailyRatesDateFormat() {
        return new BusinessException("Неверный формат даты");
    }

    public static BusinessException invalidCurrencyCode() {
        return new BusinessException( "Неподдерживаемая валюта");
    }

    public static BusinessException invalidBuyRateFormat() {
        return new BusinessException("Неверный формат курса покупки");
    }

    public static BusinessException invalidSellRateFormat() {
        return new BusinessException("Неверный формат курса продажи");
    }

    public static BusinessException invalidSourceMoneyAmountFormat() {
        return new BusinessException("Неверный формат исходной денежной суммы");
    }

    public static BusinessException invalidSourceCurrencyCode() {
        return new BusinessException("Неподдерживаемая исходная валюта");
    }

    public static BusinessException invalidTargetCurrencyCode() {
        return new BusinessException("Неподдерживаемая целевая валюта");
    }
    public static BusinessException invalidDataNotPresent() {
        return new BusinessException("Данные отсутствуют");
    }
}
