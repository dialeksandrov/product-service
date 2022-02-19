package com.aleksandrov.productservice.controller;

import com.aleksandrov.productservice.dao.entity.ProductEntity;
import com.aleksandrov.productservice.model.ProductDto;
import com.aleksandrov.productservice.model.ProductRequestDto;
import com.aleksandrov.productservice.model.response.RestResponse;
import com.aleksandrov.productservice.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dialeksandrov
 * @created 18/02/2022
 **/

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private final ProductService productService;

    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @ApiOperation(value = "Сохранение продукта")
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> saveProduct(@Valid @RequestBody ProductRequestDto dto){
        RestResponse response = new RestResponse(RestResponse.Status.FAIL, null);
        try {
            ProductDto product = productService.save(dto);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, product, "Продукт успешно сохарнён."), HttpStatus.OK);
        } catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Обновление продукта")
    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> updateProduct(@Valid @RequestBody ProductRequestDto dto){
        try {
            ProductEntity product = productService.update(dto);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, new ProductDto(product), "Продукт обновлён"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.FAIL, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Получение продукта по id")
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse> getProductById(@PathVariable(name = "id") Long id){
        try {
            ProductDto product = productService.getById(id);
            return new ResponseEntity<>(new RestResponse(RestResponse.Status.SUCCESS, product, "success"), HttpStatus.OK);
        } catch (Exception e){
            LOGGER.error(e.getMessage() + " for id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler
    public ResponseEntity<RestResponse> handleException(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getFieldErrors();
        String message = errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        LOGGER.error(message);
        return new ResponseEntity<>(new  RestResponse(RestResponse.Status.ERROR, null, message), HttpStatus.BAD_REQUEST);
    }
}
