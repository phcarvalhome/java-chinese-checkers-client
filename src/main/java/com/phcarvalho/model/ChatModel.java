package com.phcarvalho.model;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.rmi.RemoteException;

public class ChatModel {

    private ChatController controller;
    private IChatCommandTemplate chatCommandTemplate;

    public ChatModel(ChatController controller) {
        this.controller = controller;
        chatCommandTemplate = DependencyFactory.getSingleton().get(IChatCommandTemplate.class);
    }

    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        chatCommandTemplate.sendMessage(sendMessageCommand);
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        controller.sendMessageByCallback(sendMessageCommand);
    }
}
