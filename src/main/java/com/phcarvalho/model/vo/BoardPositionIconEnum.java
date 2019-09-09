package com.phcarvalho.model.vo;

import java.util.Arrays;

public enum BoardPositionIconEnum {

    EMPTY(null, null),
    DISABLED(null, null),
    PIECE_BLUE("piece-blue.png", PieceColorEnum.BLUE),
    PIECE_GREEN("piece-green.png", PieceColorEnum.GREEN),
    PIECE_PINK("piece-pink.png", PieceColorEnum.PINK),
    PIECE_RED("piece-red.png", PieceColorEnum.RED),
    PIECE_TURQUOISE("piece-turquoise.png", PieceColorEnum.TURQUOISE),
    PIECE_YELLOW("piece-yellow.png", PieceColorEnum.YELLOW);

    private String fileName;
    private PieceColorEnum pieceColor;

    BoardPositionIconEnum(String fileName, PieceColorEnum pieceColor) {
        this.fileName = fileName;
        this.pieceColor = pieceColor;
    }

    public static BoardPositionIconEnum from(PieceColorEnum pieceColor){
        return Arrays.stream(values())
                .filter(boardPositionIcon -> boardPositionIcon.getPieceColor() != null)
                .filter(boardPositionIcon -> boardPositionIcon.getPieceColor().equals(pieceColor))
                .findFirst()
                .get();
    }

    public String getFileName() {
        return fileName;
    }

    public PieceColorEnum getPieceColor() {
        return pieceColor;
    }
}
