package com.phcarvalho.model.communication.commandtemplate.remote;

import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

public interface IChatRemoteCommandTemplate {

    void sendMessage(SendMessageCommand sendMessageCommand);
}
