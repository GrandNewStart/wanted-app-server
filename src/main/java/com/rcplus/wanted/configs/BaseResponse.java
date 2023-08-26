package com.rcplus.wanted.configs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponse {
    private String result;
    private String message;
    private Object data;
}
