package com.aleksandrov.productservice.controller;

import com.aleksandrov.productservice.exceptions.NoSuchProductException;
import com.aleksandrov.productservice.model.ProductDto;
import com.aleksandrov.productservice.model.enums.CurrencyEnum;
import com.aleksandrov.productservice.model.enums.LanguageEnum;
import com.aleksandrov.productservice.model.response.RestResponse;
import com.aleksandrov.productservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

@RestController
@RequestMapping(value = "/api/v1/client")
public class ClientResource {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClientResource.class);
    private final ProductService productService;

    public ClientResource(ProductService productService){
        this.productService = productService;
    }

    @ApiOperation(value = "Получение списка продуктов")
    @GetMapping(value = "/get_by", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "20", required = false) int size,
                                                       @RequestParam(name = "currency") CurrencyEnum currency,
                                                       @RequestParam(name = "language") LanguageEnum language){
        Pageable paging = PageRequest.of(page, size, Sort.by("amount"));
        try {
            Page<ProductDto> products = productService.getAllSorted(currency, language, paging);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, products, "success"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse("Продукты не найдены", RestResponse.ErrorCode.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Поиск продукта по названию или описанию")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> searchProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "20", required = false) int size,
                                                       @RequestParam(name = "name", required = false) String name,
                                                       @RequestParam(name = "description", required = false) String description,
                                                       @RequestParam(name = "currency") CurrencyEnum currency,
                                                       @RequestParam(name = "language") LanguageEnum language){
        Pageable paging = PageRequest.of(page, size, Sort.by("amount"));
        if (name == null && description == null){
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.ERROR, null, "Заполните хотя бы одно поле"), HttpStatus.BAD_REQUEST);
        }

        try {
            Page<ProductDto> products = productService.findByNameOrDescription(name, description, currency, language, paging);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, products, "success"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse("Такие продукты не найдены", RestResponse.ErrorCode.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Поиск продукта по id")
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> findById(@RequestParam(name = "id") Long id,
                                                 @RequestParam(name = "currency") CurrencyEnum currency,
                                                 @RequestParam(name = "language") LanguageEnum language){
        try {
            ProductDto product = productService.findByIdAndSort(id, currency, language);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, product, "success"), HttpStatus.OK);
        } catch (NoSuchElementException | NoSuchProductException e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.FAIL, null, "Такой продукт не найден", RestResponse.ErrorCode.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(NoSuchElementException e){
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(NoSuchProductException e){
        LOGGER.error(e.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
