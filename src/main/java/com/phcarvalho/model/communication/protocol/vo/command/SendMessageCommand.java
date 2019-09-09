package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;

public class SendMessageCommand extends AbstractCommand {

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.SEND_MESSAGE;
    }
}
