package com.rcplus.wanted.configs;

import lombok.Getter;
import lombok.Setter;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@Getter
@Setter
public class BaseResponse {
    private String result;
    private String message;
    private Object data;

    public BaseResponse(Object data) {
        this.result = SUCCESS.getResult();
        this.message = SUCCESS.getMessage();
        this.data = data;
    }

    public BaseResponse(BaseResponseStatus status) {
        this.result = status.getResult();
        this.message = status.getMessage();
    }
}
