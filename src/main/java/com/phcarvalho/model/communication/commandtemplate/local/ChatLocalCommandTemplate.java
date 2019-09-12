package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.ChatModel;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatLocalCommandTemplate extends UnicastRemoteObject implements IChatCommandTemplate {

    private ChatModel chatModel;

    public ChatLocalCommandTemplate() throws RemoteException {
        super();
        chatModel = DependencyFactory.getSingleton().get(ChatModel.class);
    }


    @Override
    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        chatModel.sendMessageByCallback(sendMessageCommand);
    }
}
