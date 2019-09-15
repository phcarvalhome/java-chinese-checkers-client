package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;

import java.rmi.RemoteException;

public class ChatRemoteCommandTemplate implements IChatCommandTemplate {

    private SocketConnectionStrategy socketConnectionStrategy;

    public ChatRemoteCommandTemplate() {
        socketConnectionStrategy = DependencyFactory.getSingleton().get(SocketConnectionStrategy.class);
    }

    @Override
    public void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException {
        socketConnectionStrategy.send(sendMessageCommand);
    }
}
