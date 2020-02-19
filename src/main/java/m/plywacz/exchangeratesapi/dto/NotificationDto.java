package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import m.plywacz.exchangeratesapi.constraints.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class NotificationDto {
    @NotNull
    @Min(value = 0, message = "User's id must be positive integer")
    private final Long userId;

    @NotNull
    @Min(value = 0, message = "Frequency of currency rate verification must be positive integer, [hours]")
    private final int frequency;

    @NotNull
    @Currency(message = "Given currency value is invalid either unsupported. Supported values: EUR, USD, GBP, CHF, JPY")
    private final String currency;

    @NotNull
    @Positive(message = "Sending notification value should be positive number")
    private final Double sendingValue;

    public NotificationDto(Long userId, int frequency,
                           String currency, double sendingValue) {
        this.userId = userId;
        this.frequency = frequency;
        this.currency = currency;
        this.sendingValue = sendingValue;
    }

    public Long getUserId() {
        return userId;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCurrency() {
        return currency;
    }

    public m.plywacz.exchangeratesapi.model.Currency getCurrencyEnum() {
        return m.plywacz.exchangeratesapi.model.Currency.valueOf(currency);
    }

    public double getCurrencyVal() {
        return sendingValue;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        NotificationDto that = (NotificationDto) o;
        return frequency == that.frequency &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(sendingValue, that.sendingValue);
    }

    @Override public int hashCode() {
        return Objects.hash(userId, frequency, currency, sendingValue);
    }

    @Override public String toString() {
        return "NotificationDto{" +
                "userId=" + userId +
                ", frequency=" + frequency +
                ", currency='" + currency + '\'' +
                ", sendingValue=" + sendingValue +
                '}';
    }
}
