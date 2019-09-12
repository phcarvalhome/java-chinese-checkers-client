package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainLocalCommandTemplate extends UnicastRemoteObject implements IMainCommandTemplate {

    private GameModel gameModel;

    public MainLocalCommandTemplate() throws RemoteException {
        super();
        gameModel = DependencyFactory.getSingleton().get(GameModel.class);
    }

    @Override
    public void addGame(AddGameCommand addGameCommand) throws RemoteException {
        gameModel.addByCallback(addGameCommand);
    }

    @Override
    public void addPlayer(AddPlayerCommand addPlayerCommand) throws RemoteException {
        gameModel.selectByCallback(addPlayerCommand);
    }

    @Override
    public void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) throws RemoteException {
        gameModel.flagAsReadyByCallback(flagAsReadyCommand);
    }
}
