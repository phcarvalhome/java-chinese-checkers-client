package com.phcarvalho.model;

import com.phcarvalho.controller.BoardPositionController;
import com.phcarvalho.model.configuration.StartingBoardConfiguration;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Position;

import java.util.Objects;

public class BoardPositionModel {

    private BoardPositionController controller;
    private Position position;
    private Piece piece;
    private boolean enabled;

    public BoardPositionModel(BoardPositionController controller, Integer row, Integer column) {
        this.controller = controller;
        this.position = new Position(row, column);
        this.enabled = StartingBoardConfiguration.getSingleton().getBoardRowConfiguration(row).containsPosition(column);
    }

    public boolean isEmpty(){
        return piece == null;
    }

    public boolean isNotEmpty(){
        return !isEmpty();
    }

    public void removePiece(){
        piece = null;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {

        if(piece == null)
            return;

        if(!isEnabled())
            return;

        this.piece = piece;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardPositionModel that = (BoardPositionModel) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "BoardPosition{" +
                "position=" + position +
                ", piece=" + piece +
                ", enabled=" + enabled +
                '}';
    }
}
