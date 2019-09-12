package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.BoardModel;
import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BoardLocalCommandTemplate extends UnicastRemoteObject implements IBoardCommandTemplate {

    private BoardModel gameModel;

    public BoardLocalCommandTemplate() throws RemoteException {
        super();
        gameModel = DependencyFactory.getSingleton().get(BoardModel.class);
    }

    @Override
    public void notifyVictory(NotifyVictoryCommand notifyVictoryCommand) throws RemoteException {

    }

    @Override
    public void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand) throws RemoteException {

    }

    @Override
    public void movePiece(MovePieceCommand movePieceCommand) throws RemoteException {
        gameModel.movePieceByCallback(movePieceCommand);
    }
}
