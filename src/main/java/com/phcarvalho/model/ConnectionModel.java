package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import java.rmi.RemoteException;

public class ConnectionModel {

    private ConnectionController controller;
    private IConnectionStrategy connectionHandlerStrategy;

    public ConnectionModel(ConnectionController controller) {
        this.controller = controller;
        connectionHandlerStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
    }

    public void connectToServer(String host, Integer port, String userName) throws RemoteException {
        connectionHandlerStrategy.connectToServer(host, port, userName);
    }

    public void connectToServerByCallback(User localUser) {
        Configuration.getSingleton().setServerConnected(true);
        Configuration.getSingleton().setLocalUser(localUser);
        controller.connectToServerByCallback(localUser);
    }

    public void clear() {
        Configuration.getSingleton().clear();
        controller.clear();
    }
}
