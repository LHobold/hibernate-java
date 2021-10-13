package com.example.hibernatejava.enumeration;

public enum StatusEnum {
    SUCCESS("SUCCESS"), ERROR("ERROR");

    private String value;

    private StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusEnum getEnum(String value) {

        for (StatusEnum t : values()) {
            if (value.equals(t.getValue())) {
                return t;
            }
        }

        throw new RuntimeException("Type not found.");
    }

}
