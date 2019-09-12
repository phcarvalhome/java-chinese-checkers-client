package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;

import java.rmi.RemoteException;

public class BoardRemoteCommandTemplate implements IBoardCommandTemplate {

    private IConnectionStrategy connectionHandlerStrategy;

    public BoardRemoteCommandTemplate() {
        connectionHandlerStrategy = DependencyFactory.getSingleton().get(IConnectionStrategy.class);
    }

    @Override
    public void notifyVictory(NotifyVictoryCommand notifyVictoryCommand) throws RemoteException {
        connectionHandlerStrategy.send(notifyVictoryCommand);
    }

    @Override
    public void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand) throws RemoteException {
        connectionHandlerStrategy.send(notifyWithdrawalCommand);
    }

    @Override
    public void movePiece(MovePieceCommand movePieceCommand) throws RemoteException {
        connectionHandlerStrategy.send(movePieceCommand);
    }
}
