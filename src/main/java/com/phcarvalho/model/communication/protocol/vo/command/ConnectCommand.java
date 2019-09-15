package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.User;

public class ConnectCommand extends AbstractCommand {

    private User remoteUser;

    public ConnectCommand(User remoteUser) {
        this.remoteUser = remoteUser;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.CONNECT;
    }

    public User getRemoteUser() {
        return remoteUser;
    }

    @Override
    public String toString() {
        return "ConnectCommand{" +
                "remoteUser=" + remoteUser +
                '}';
    }
}
