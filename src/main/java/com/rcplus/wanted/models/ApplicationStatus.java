package com.rcplus.wanted.models;

import java.util.ArrayList;
import java.util.List;

public enum ApplicationStatus {
    
    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    REJECTED("REJECTED"),
    ADMITTED("ADMITTED");
    
    public final String value;

    private ApplicationStatus(String value) {
        this.value = value;
    }

    public static List<String> allValues() {
        List<String> list = new ArrayList<>();
        for (ApplicationStatus value : ApplicationStatus.values()) {
            list.add(value.value);
        }
        return list;
    }

}
