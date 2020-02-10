package m.plywacz.exchangeratesapi.model;
/*
Author: BeGieU
Date: 08.02.2020
*/

public enum Currency {
    PLN("PLN"),
    EUR("EUR"),
    USD("USD"),
    GBP("GBP"),
    CHF("CHF"),
    JPY("JPY");

    Currency(String strVal) {
        this.strVal = strVal;
    }

    private String strVal;

    public String getStrVal() {
        return strVal;
    }
}
