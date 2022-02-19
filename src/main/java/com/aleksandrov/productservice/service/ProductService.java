package com.aleksandrov.productservice.service;

import com.aleksandrov.productservice.dao.entity.ProductEntity;
import com.aleksandrov.productservice.exceptions.NoSuchProductException;
import com.aleksandrov.productservice.model.ProductDto;
import com.aleksandrov.productservice.model.ProductRequestDto;
import com.aleksandrov.productservice.model.enums.CurrencyEnum;
import com.aleksandrov.productservice.model.enums.LanguageEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

public interface ProductService {

    ProductDto save(ProductRequestDto dto);

    ProductEntity update(ProductRequestDto dto);

    ProductDto getById(Long id);

    Page<ProductDto> getAllSorted(CurrencyEnum currency, LanguageEnum languageEnum, Pageable paging);

    Page<ProductDto> findByNameOrDescription(String name, String description, CurrencyEnum currency, LanguageEnum language, Pageable paging);

    ProductDto findByIdAndSort(Long id, CurrencyEnum currency, LanguageEnum language) throws NoSuchProductException;
}
