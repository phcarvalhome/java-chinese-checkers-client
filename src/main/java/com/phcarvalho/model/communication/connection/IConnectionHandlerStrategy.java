package com.phcarvalho.model.communication.connection;

import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.exception.ConnectionException;

public interface IConnectionHandlerStrategy {

    void connectToServer(String host, Integer port, String userName) throws ConnectionException;

    void send(ICommand command) throws ConnectionException;

    void setCommandInvoker(CommandInvoker commandInvoker);

    void setMainModel(MainModel mainModel);
}
