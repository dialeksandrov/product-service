package com.aleksandrov.productservice.exceptions;

import com.aleksandrov.productservice.model.response.RestResponse;

/**
 * @author dialeksandrov
 * @created 19/02/2022
 **/

public class NoSuchProductException extends Exception {
    private String message;
    private RestResponse.ErrorCode code;

    public NoSuchProductException(String message, RestResponse.ErrorCode errorCode){
        super(message);
        this.code = errorCode;
    }
}
