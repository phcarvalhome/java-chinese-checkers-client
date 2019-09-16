package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.vo.Player;

public class NotifyWithdrawalCommand extends AbstractCommand {

    private Integer gameId;
    private Player player;

    public NotifyWithdrawalCommand(Integer gameId, Player player) {
        this.gameId = gameId;
        this.player = player;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.NOTIFY_WITHDRAWAL;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "NotifyWithdrawalCommand{" +
                "gameId=" + gameId +
                ", player=" + player +
                '}';
    }
}
