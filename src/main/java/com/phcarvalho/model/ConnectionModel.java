package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.ConnectCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import java.rmi.RemoteException;

public class ConnectionModel {

    private ConnectionController controller;
    private IConnectionStrategy connectionStrategy;
    private ICommandTemplateFactory commandTemplateFactory;

    public ConnectionModel(ConnectionController controller) {
        this.controller = controller;
        connectionStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
    }

    public void connectToServer(User localUser, User remoteUser) throws RemoteException {
        Configuration.getSingleton().setLocalUser(localUser);
        Configuration.getSingleton().setRemoteUser(remoteUser);
        connectionStrategy.connectToServer(remoteUser);
        commandTemplateFactory.getConnection().connect(new ConnectCommand(remoteUser));
    }

    public void connectToServerByCallback(ConnectCommand connectCommand) {
        Configuration.getSingleton().setServerConnected(true);
        controller.connectToServerByCallback();
    }

    public void clear() {
        Configuration.getSingleton().clear();
        controller.clear();
    }
}
