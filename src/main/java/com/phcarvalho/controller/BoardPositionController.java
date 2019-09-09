package com.phcarvalho.controller;

import com.phcarvalho.model.BoardPositionModel;
import com.phcarvalho.model.vo.BoardPositionIconEnum;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Position;
import com.phcarvalho.view.BoardPositionView;

public class BoardPositionController {

    private BoardPositionView view;
    private BoardPositionModel boardPositionModel;

    public BoardPositionController(BoardPositionView view, Integer row, Integer column) {
        this.view = view;
        boardPositionModel = new BoardPositionModel(this, row, column);
    }

    public BoardPositionIconEnum getBoardPositionIcon() {

        if(boardPositionModel.isEnabled())
            return BoardPositionIconEnum.EMPTY;
        else
            return BoardPositionIconEnum.DISABLED;
    }

    public boolean isEmpty() {
        return boardPositionModel.isEmpty();
    }

    public boolean isNotEmpty() {
        return boardPositionModel.isNotEmpty();
    }

    public void removePiece() {
        boardPositionModel.removePiece();
    }

    public Position getPosition() {
        return boardPositionModel.getPosition();
    }

    public Piece getPiece() {
        return boardPositionModel.getPiece();
    }

    public void setPiece(Piece piece) {
        boardPositionModel.setPiece(piece);
    }

    public BoardPositionModel getBoardPositionModel() {
        return boardPositionModel;
    }
}
