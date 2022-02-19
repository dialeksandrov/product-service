package com.aleksandrov.productservice.model.response;

import org.apache.log4j.spi.ErrorCode;

import java.io.Serializable;

/**
 * @author diale
 * @created 19/02/2022
 **/

public class RestResponse implements Serializable {

    private Status status;
    private Object data;
    private String message;
    private ErrorCode error;

    public RestResponse(Object data){
        this.status = Status.SUCCESS;
        this.data = data;
    }

    public RestResponse(Status status, Object data){
        this.status = status;
        this.data = data;
    }

    public RestResponse(Status status, Object data, String message){
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public RestResponse(Status status, Object data, String message, ErrorCode error) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public RestResponse(String message, ErrorCode error){
        this.message = message;
        this.error = error;
    }

    public enum ErrorCode{
        NOT_FOUND,
        INTERNAL_SERVER_ERROR,
        BAD_REQUEST;
    }

    public enum Status{
        SUCCESS("success"),
        FAIL("fail"),
        ERROR("error");

        private final String name;

        Status(String name){
            this.name = name;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getError() {
        return error;
    }

    public void setError(ErrorCode error) {
        this.error = error;
    }
}
