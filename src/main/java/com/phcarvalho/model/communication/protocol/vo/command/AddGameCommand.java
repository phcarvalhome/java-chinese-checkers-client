package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.Game;

public class AddGameCommand extends AbstractCommand {

    private Game game;
    private boolean delete;

    public AddGameCommand(Game game) {
        this(game, false);
    }

    public AddGameCommand(Game game, boolean delete) {
        this.game = game;
        this.delete = delete;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.ADD_GAME;
    }

    public Game getGame() {
        return game;
    }

    public boolean isDelete() {
        return delete;
    }

    @Override
    public String toString() {
        return "AddGameCommand{" +
                "game=" + game +
                ", delete=" + delete +
                '}';
    }
}
