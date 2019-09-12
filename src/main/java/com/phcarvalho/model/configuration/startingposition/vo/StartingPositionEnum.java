package com.phcarvalho.model.configuration.startingposition.vo;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum StartingPositionEnum {

    TOP("Top"),
    TOP_RIGHT("Top Right"),
    BOTTOM_RIGHT("Bottom Right"),
    BOTTOM("Bottom"),
    BOTTOM_LEFT("Bottom Left"),
    TOP_LEFT("Top Left");

    private String value;

    StartingPositionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StartingPositionEnum from(String value){
        return Arrays.stream(values())
                .filter(pieceColor -> pieceColor.getValue().equals(value))
                .findFirst()
                .get();
    }

    public static String[] getValueArray(){
        return Arrays.stream(values())
                .map(startingPosition -> startingPosition.getValue())
                .collect(Collectors.toList()).toArray(String[]::new);
    }
}
