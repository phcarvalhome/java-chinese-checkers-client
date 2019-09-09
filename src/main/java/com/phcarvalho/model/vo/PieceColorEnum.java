package com.phcarvalho.model.vo;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum PieceColorEnum {

    BLUE("Blue", new Color(0, 0, 100)),
    GREEN("Green", new Color(0, 100, 0)),
    PINK("Pink", new Color(200, 0, 200)),
    RED("Red", new Color(100, 0, 0)),
    TURQUOISE("Turquoise", new Color(0, 200, 200 )),
    YELLOW("Yellow", new Color(200, 200, 0));

    private String value;
    private Color color;

    PieceColorEnum(String value, Color color) {
        this.value = value;
        this.color = color;
    }

    public static PieceColorEnum from(String value){
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

    public String getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }
}
