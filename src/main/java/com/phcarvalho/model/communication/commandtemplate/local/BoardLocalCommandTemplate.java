package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.BoardModel;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;

public class BoardLocalCommandTemplate implements ILocalCommandTemplate {

    private BoardModel gameModel;

    public BoardLocalCommandTemplate() {
        gameModel = DependencyFactory.getSingleton().get(BoardModel.class);
    }

    public void notifyVictory(NotifyVictoryCommand notifyVictoryCommand){

    }

    public void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand){

    }

    public void movePiece(MovePieceCommand movePieceCommand){
        gameModel.movePieceByCallback(movePieceCommand);
    }
}
