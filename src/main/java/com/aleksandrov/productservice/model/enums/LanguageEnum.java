package com.aleksandrov.productservice.model.enums;

/**
 * @author diale
 * @created 18/02/2022
 **/

public enum  LanguageEnum {
    RU("ru"),
    EN("en");

    private String value;

    LanguageEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
