package com.aleksandrov.productservice.model;

import com.aleksandrov.productservice.dao.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.aleksandrov.productservice.model.constants.DateTimePatterns.DATE_TIME_FORMAT;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

public class ProductDto {
    private Long id;
    @NotNull(message = "Название не может быть пустым")
    private String name;
    private String description;
    @DecimalMin(value = "0.01", message = "Стоимость не можеть быть равна нулю")
    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime created_dt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private LocalDateTime modifiedDt;
    private String currency;
    private String language;

    public ProductDto(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.amount = entity.getAmount();
        this.created_dt = entity.getCreatedDt();
        this.modifiedDt = entity.getModifiedDt();
        this.currency = entity.getCurrency().toString();
        this.language = entity.getLanguage().toString();
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

    public LocalDateTime getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(LocalDateTime created_dt) {
        this.created_dt = created_dt;
    }

    public LocalDateTime getModifiedDt() {
        return modifiedDt;
    }

    public void setModifiedDt(LocalDateTime modifiedDt) {
        this.modifiedDt = modifiedDt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currencyId) {
        this.currency = currencyId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
