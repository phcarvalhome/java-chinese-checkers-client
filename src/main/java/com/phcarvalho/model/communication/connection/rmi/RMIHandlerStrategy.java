package com.phcarvalho.model.communication.connection.rmi;

import com.phcarvalho.model.ConnectedPlayerModel;
import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.MenuModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;

public class RMIHandlerStrategy implements IConnectionHandlerStrategy {

    @Override
    public void connectToServer(String host, Integer port, String userName) {

    }

    @Override
    public void send(ICommand command) {

    }

    @Override
    public void setCommandInvoker(CommandInvoker commandInvoker) {

    }

    @Override
    public void setMenuModel(MenuModel menuModel) {

    }

    @Override
    public void setGameModel(GameModel gameModel) {

    }

    @Override
    public void setConnectedPlayerModel(ConnectedPlayerModel connectedPlayerModel) {

    }
}
