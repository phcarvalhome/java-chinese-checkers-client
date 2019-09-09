package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;

public class NotifyVictoryCommand extends AbstractCommand {

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.NOTIFY_VICTORY;
    }
}
