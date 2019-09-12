package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.vo.Player;

public class AddPlayerCommand extends AbstractCommand {

    private Player player;
    private Game game;

    public AddPlayerCommand(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.ADD_PLAYER;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public String toString() {
        return "AddPlayerCommand{" +
                "player=" + player +
                ", game=" + game +
                '}';
    }
}
