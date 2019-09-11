package com.phcarvalho.model.communication.commandtemplate.remote;

import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;
import com.phcarvalho.model.exception.ConnectionException;

public interface IBoardRemoteCommandTemplate {

    void notifyVictory(NotifyVictoryCommand notifyVictoryCommand) throws ConnectionException;

    void notifyWithdrawal(NotifyWithdrawalCommand notifyWithdrawalCommand) throws ConnectionException;

    void movePiece(MovePieceCommand movePieceCommand) throws ConnectionException;
}
