package com.phcarvalho.model;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;

import java.rmi.RemoteException;

public class ChatModel {

    private ChatController controller;
    private ICommandTemplateFactory commandTemplateFactory;

    public ChatModel(ChatController controller) {
        this.controller = controller;
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
    }

    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        commandTemplateFactory.getChat().sendMessage(sendMessageCommand);
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        controller.sendMessageByCallback(sendMessageCommand);
    }
}
