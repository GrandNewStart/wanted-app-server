package com.rcplus.wanted.models;

import lombok.Getter;

@Getter
public enum Nation {
    ETC("기타"),
    KOREA("한국"),
    TAIWAN("대만"),
    SINGAPORE("싱가폴"),
    JAPAN("일본");

    private String name;
    Nation(String name) { this.name = name; }
}
