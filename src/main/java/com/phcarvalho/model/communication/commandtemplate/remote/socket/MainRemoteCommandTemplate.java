package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;

import java.rmi.RemoteException;

public class MainRemoteCommandTemplate implements IMainCommandTemplate {

    private SocketConnectionStrategy socketConnectionStrategy;

    public MainRemoteCommandTemplate() {
        socketConnectionStrategy = DependencyFactory.getSingleton().get(SocketConnectionStrategy.class);
    }

    @Override
    public void addGame(AddGameCommand addGameCommand) throws RemoteException {
        socketConnectionStrategy.send(addGameCommand);
    }

    @Override
    public void addPlayer(AddPlayerCommand addPlayerCommand) throws RemoteException {
        socketConnectionStrategy.send(addPlayerCommand);
    }

    @Override
    public void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) throws RemoteException {
        socketConnectionStrategy.send(flagAsReadyCommand);
    }
}
