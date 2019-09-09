package com.phcarvalho.model.communication.commandtemplate.remote;

import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;

public interface IBoardRemoteCommandTemplate {

    void notifyVictory(NotifyVictoryCommand notifyVictoryCommand);

    void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand);

    void movePiece(MovePieceCommand movePieceCommand);
}
