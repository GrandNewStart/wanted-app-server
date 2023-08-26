package com.rcplus.wanted.models;

import lombok.Getter;

@Getter
public enum Industry {
    IT_CONTENTS("IT, 컨텐츠"),
    SALES_DISTRIBUTION("판매, 유통"),
    MANFACTURE("제조"),
    SERVICE("기타 서비스업"),
    SCIENCE_TECHNOLOGY("전문, 과학기술"),
    EDUCATION("교육"),
    FINANCE("금융"),
    ARTS_SPORTS_LEISURE("예술, 스포츠, 여가"),
    SHIPPING("물류, 운송"),
    REAL_ESTATES("부동산"),
    CONSTRUCTION("건설"),
    BUSNIESS_SUPPORT("사업지원"),
    ACCOMODATION_FOOD_BEVERAGE("숙박, 음식점"),
    HEALTH_WELFARE("보건, 사회복지"),
    ELECTRONIC_GAS("전기, 가스"),
    GAMES("게임"),
    WATERWORKS_ENVIRONMENTS("상수도, 환경"),
    AGRICULTURE_FISHERY("농림어업"),
    PUBLIC_SERVICE_MILITARY("공공행정, 국방"),
    MINING("광업"),
    HOUSEWORKS("가사, 가정"),
    INTERNATIONAL_AFFAIRS("국제, 외국기관");

    private final String name;
    Industry(String name) { this.name = name; }
}
