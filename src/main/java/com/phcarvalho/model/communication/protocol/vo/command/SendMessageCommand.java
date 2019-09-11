package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.vo.Player;

public class SendMessageCommand extends AbstractCommand {

    private int gameId;
    private Player player;
    private String message;

    public SendMessageCommand(int gameId, Player player, String message) {
        this.gameId = gameId;
        this.player = player;
        this.message = message;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.SEND_MESSAGE;
    }

    public int getGameId() {
        return gameId;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SendMessageCommand{" +
                "gameId=" + gameId +
                ", player=" + player +
                ", message='" + message + '\'' +
                '}';
    }
}
