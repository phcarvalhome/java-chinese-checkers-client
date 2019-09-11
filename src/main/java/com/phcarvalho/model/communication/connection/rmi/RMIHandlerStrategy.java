package com.phcarvalho.model.communication.connection.rmi;

import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.exception.ConnectionException;

public class RMIHandlerStrategy implements IConnectionHandlerStrategy {


    @Override
    public void connectToServer(String host, Integer port, String userName) throws ConnectionException {

    }

    @Override
    public void send(ICommand command) throws ConnectionException {

    }

    @Override
    public void setCommandInvoker(CommandInvoker commandInvoker) {

    }

    @Override
    public void setMainModel(MainModel mainModel) {

    }
}
