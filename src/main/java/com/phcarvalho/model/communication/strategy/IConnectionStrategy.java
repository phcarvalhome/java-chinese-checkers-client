package com.phcarvalho.model.communication.strategy;

import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;

import java.rmi.RemoteException;

public interface IConnectionStrategy {

    void connectToServer(String host, Integer port, String userName) throws RemoteException;

    void send(ICommand command) throws RemoteException;

    void setCommandInvoker(CommandInvoker commandInvoker);

    void setMainModel(MainModel mainModel);
}
