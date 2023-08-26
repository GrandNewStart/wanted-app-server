package com.rcplus.wanted.models;

import lombok.Getter;

@Getter
public enum JobField {
    ALL("선택안함"),
    DEVELOPMENT("개발"),
    BUSINESS("경영/비즈니스"),
    MARKETING_ADVERTISING("마케팅/광고"),
    DESIGN("디자인"),
    SALES("영업"),
    CUSTOMER_SERVICE_RETAIL("고객서비스/리테일"),
    MEDIA("미디어"),
    ENGINEERING("엔지니어링/설계"),
    GAMES("게임 제작"),
    HR("HR"),
    FINANCE("금융"),
    MANFACTURE("제조/생산"),
    SHIPPING_TRADES("물류/무역");

    private String name;
    JobField(String name){ this.name = name; }
}
