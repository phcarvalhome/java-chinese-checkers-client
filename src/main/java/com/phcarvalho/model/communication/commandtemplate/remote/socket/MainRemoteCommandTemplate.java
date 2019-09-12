package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;

import java.rmi.RemoteException;

public class MainRemoteCommandTemplate implements IMainCommandTemplate {

    private IConnectionStrategy connectionHandlerStrategy;

    public MainRemoteCommandTemplate() {
        connectionHandlerStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
    }

    @Override
    public void addGame(AddGameCommand addGameCommand) throws RemoteException {
        connectionHandlerStrategy.send(addGameCommand);
    }

    @Override
    public void addPlayer(AddPlayerCommand addPlayerCommand) throws RemoteException {
        connectionHandlerStrategy.send(addPlayerCommand);
    }

    @Override
    public void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) throws RemoteException {
        connectionHandlerStrategy.send(flagAsReadyCommand);
    }
}
