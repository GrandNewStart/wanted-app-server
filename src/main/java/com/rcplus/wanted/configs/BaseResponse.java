package com.rcplus.wanted.configs;

import lombok.Getter;
import lombok.Setter;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class BaseResponse {
    
    @JsonIgnore
    private HttpStatus status;
    private String result;
    private String message;
    private Object data;

    public BaseResponse(Object data) {
        this.status = HttpStatus.OK;
        this.result = SUCCESS.getResult();
        this.message = SUCCESS.getMessage();
        this.data = data;
    }

    public BaseResponse(BaseResponseStatus status) {
        this.status = status.getStatus();
        this.result = status.getResult();
        this.message = status.getMessage();
    }
}
