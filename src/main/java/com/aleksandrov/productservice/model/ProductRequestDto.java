package com.aleksandrov.productservice.model;

import com.aleksandrov.productservice.model.enums.CurrencyEnum;
import com.aleksandrov.productservice.model.enums.LanguageEnum;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

public class ProductRequestDto {
    private Long id;
    @NotBlank(message = "Название не может быть пустым")
    private String name;
    private String description;
    @DecimalMin(value = "0.01", message = "Стоимость не может быть равна 0")
    private Double amount;
    private CurrencyEnum currency;
    private LanguageEnum language;

    public ProductRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }
}

