package com.phcarvalho.model.communication.commandtemplate.remote.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;

import java.rmi.RemoteException;

public class BoardRemoteCommandTemplate implements IBoardCommandTemplate {

    private SocketConnectionStrategy socketConnectionStrategy;

    public BoardRemoteCommandTemplate() {
        socketConnectionStrategy = DependencyFactory.getSingleton().get(SocketConnectionStrategy.class);
    }

    @Override
    public void notifyVictory(NotifyVictoryCommand notifyVictoryCommand) throws RemoteException {
        socketConnectionStrategy.send(notifyVictoryCommand);
    }

    @Override
    public void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand) throws RemoteException {
        socketConnectionStrategy.send(notifyWithdrawalCommand);
    }

    @Override
    public void movePiece(MovePieceCommand movePieceCommand) throws RemoteException {
        socketConnectionStrategy.send(movePieceCommand);
    }
}
