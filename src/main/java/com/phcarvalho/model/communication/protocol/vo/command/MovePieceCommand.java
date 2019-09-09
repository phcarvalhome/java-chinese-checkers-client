package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Position;

public class MovePieceCommand extends AbstractCommand {

    private Position sourcePosition;
    private Position targetPosition;
    private Piece piece;
    private Game game;

    public MovePieceCommand(Position sourcePosition, Position targetPosition, Piece piece, Game game) {
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.piece = piece;
        this.game = game;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.MOVE_PIECE;
    }

    public Position getSourcePosition() {
        return sourcePosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public Piece getPiece() {
        return piece;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "MovePieceCommand{" +
                "sourcePosition=" + sourcePosition +
                ", targetPosition=" + targetPosition +
                ", piece=" + piece +
                ", game=" + game +
                '}';
    }
}
