package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.ConnectionModel;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.ConnectCommand;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConnectionLocalCommandTemplate extends UnicastRemoteObject implements IConnectionCommandTemplate {

    private ConnectionModel connectionModel;

    public ConnectionLocalCommandTemplate() throws RemoteException {
        super();
        connectionModel = DependencyFactory.getSingleton().get(ConnectionModel.class);
    }

    @Override
    public void connect(ConnectCommand connectCommand) throws RemoteException {
        connectionModel.connectToServerByCallback(connectCommand);
    }
}
