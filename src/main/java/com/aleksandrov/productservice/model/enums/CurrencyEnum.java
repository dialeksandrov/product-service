package com.aleksandrov.productservice.model.enums;

/**
 * @author diale
 * @created 18/02/2022
 **/

public enum CurrencyEnum {
    RUB("ruble"),
    USD("dollar"),
    EUR("euro");

    private String value;

    CurrencyEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
