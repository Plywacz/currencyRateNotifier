package m.plywacz.exchangeratesapi.dto;
/*
Author: BeGieU
Date: 09.02.2020
*/

import javax.validation.constraints.NotBlank;

public class NotificationDto {
    private final Long userId;
    private final int frequency;
    private final String currency;

    public NotificationDto(@NotBlank Long userId, int frequency, @NotBlank String currency) {
        this.userId = userId;
        this.frequency = frequency;
        this.currency = currency;
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
}
