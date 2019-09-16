package com.phcarvalho.model.vo;

public enum GameStatusEnum {

    NOT_SELECTED("Not Selected"),
    SELECTED("Selected"),
    READY("Ready"),
    STARTED("Started");

    private String value;

    GameStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
