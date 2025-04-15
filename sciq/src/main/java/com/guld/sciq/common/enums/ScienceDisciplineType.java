package com.guld.sciq.common.enums;

public enum ScienceDisciplineType {
    PHYSICS("physics", "물리학"),
    CHEMISTRY("chemistry", "화학"),
    BIOLOGY("biology", "생물학"),
    EARTH_SCIENCE("earth_science", "지구과학"),
    ASTRONOMY("astronomy", "천문학");

    private final String code;
    private final String displayName;

    ScienceDisciplineType(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public String getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
} 