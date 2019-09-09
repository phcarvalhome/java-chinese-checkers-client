package com.phcarvalho.model.communication.connection;

import com.phcarvalho.model.ConnectedPlayerModel;
import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.MenuModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;

public interface IConnectionHandlerStrategy {

    void connectToServer(String host, Integer port, String userName);

    void send(ICommand command);

    void setCommandInvoker(CommandInvoker commandInvoker);

    void setMenuModel(MenuModel menuModel);

    void setGameModel(GameModel gameModel);

    void setConnectedPlayerModel(ConnectedPlayerModel connectedPlayerModel);
}
