package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.ConnectCommand;
import com.phcarvalho.model.communication.protocol.vo.command.DisconnectCommand;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;

import java.rmi.RemoteException;

public class ConnectionRemoteCommandTemplate implements IConnectionCommandTemplate {

    private SocketConnectionStrategy socketConnectionStrategy;

    public ConnectionRemoteCommandTemplate() {
        socketConnectionStrategy = DependencyFactory.getSingleton().get(SocketConnectionStrategy.class);
    }

    @Override
    public void connect(ConnectCommand connectCommand) throws RemoteException {
        socketConnectionStrategy.connectToServer(connectCommand.getRemoteUser());
    }

    @Override
    public void disconnect(DisconnectCommand disconnectCommand) throws RemoteException {
        socketConnectionStrategy.send(disconnectCommand);
    }
}
