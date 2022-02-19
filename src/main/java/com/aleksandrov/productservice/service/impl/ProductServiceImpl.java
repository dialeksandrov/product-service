package com.aleksandrov.productservice.service.impl;

import com.aleksandrov.productservice.dao.ProductRepo;
import com.aleksandrov.productservice.dao.entity.ProductEntity;
import com.aleksandrov.productservice.exceptions.NoSuchProductException;
import com.aleksandrov.productservice.model.ProductDto;
import com.aleksandrov.productservice.model.ProductRequestDto;
import com.aleksandrov.productservice.model.enums.CurrencyEnum;
import com.aleksandrov.productservice.model.enums.LanguageEnum;
import com.aleksandrov.productservice.model.response.RestResponse;
import com.aleksandrov.productservice.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto save(ProductRequestDto dto) {
        return new ProductDto(productRepo.save(new ProductEntity(dto)));
    }

    @Override
    public ProductEntity update(ProductRequestDto dto) {
        Optional<ProductEntity> productEntity = productRepo.findById(dto.getId());
        if (productEntity.isPresent()){
            productEntity.get().setName(dto.getName());
            productEntity.get().setDescription(dto.getDescription());
            productEntity.get().setAmount(dto.getAmount());
            productEntity.get().setModifiedDt(LocalDateTime.now());
            productEntity.get().setCurrency(dto.getCurrency());
            return productRepo.saveAndFlush(productEntity.get());
        }
        return null;
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<ProductEntity> entity = productRepo.findById(id);
        return new ProductDto(entity.get());
    }

    @Override
    public Page<ProductDto> getAllSorted(CurrencyEnum currency, LanguageEnum languageEnum, Pageable paging) {
        return productRepo.findAllSorted(currency.toString(), languageEnum.toString(), paging).map(ProductDto::new);
    }

    @Override
    public Page<ProductDto> findByNameOrDescription(String name, String description, CurrencyEnum currency, LanguageEnum language, Pageable paging) {
        if (name != null){
            return productRepo.findByName(name.toUpperCase(), currency.toString(), language.toString(), paging).map(ProductDto::new);
        } else {
            return productRepo.findByDescription(description.toUpperCase(), currency.toString(), language.toString(), paging).map(ProductDto::new);
        }
    }

    @Override
    public ProductDto findByIdAndSort(Long id, CurrencyEnum currency, LanguageEnum language) throws NoSuchProductException {
        ProductEntity entity = productRepo.findByIdAndSort(id, currency.toString(), language.toString());
        if (entity != null){
            return new ProductDto(entity);
        } else {
            throw new NoSuchProductException("Продукт не найден", RestResponse.ErrorCode.NOT_FOUND);
        }
    }
}
