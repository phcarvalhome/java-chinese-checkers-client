package com.phcarvalho.model;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.remote.IChatRemoteCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.exception.ConnectionException;

public class ChatModel {

    private ChatController controller;
    private IChatRemoteCommandTemplate chatRemoteCommandTemplate;

    public ChatModel(ChatController controller) {
        this.controller = controller;
        chatRemoteCommandTemplate = DependencyFactory.getSingleton().get(IChatRemoteCommandTemplate.class);
    }

    public void sendMessage(SendMessageCommand sendMessageCommand) throws ConnectionException {
        chatRemoteCommandTemplate.sendMessage(sendMessageCommand);
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        controller.sendMessageByCallback(sendMessageCommand);
    }
}
