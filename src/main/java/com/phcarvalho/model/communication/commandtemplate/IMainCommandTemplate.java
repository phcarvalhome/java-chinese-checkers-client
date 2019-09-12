package com.phcarvalho.model.communication.commandtemplate;

import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMainCommandTemplate extends Remote {

    void addGame(AddGameCommand addGameCommand) throws RemoteException;

    void addPlayer(AddPlayerCommand addPlayerCommand) throws RemoteException;

    void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) throws RemoteException;
}
