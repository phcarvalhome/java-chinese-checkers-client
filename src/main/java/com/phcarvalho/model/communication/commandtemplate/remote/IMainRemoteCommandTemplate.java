package com.phcarvalho.model.communication.commandtemplate.remote;

import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;
import com.phcarvalho.model.exception.ConnectionException;

public interface IMainRemoteCommandTemplate {

    void addGame(AddGameCommand addGameCommand) throws ConnectionException;

    void addPlayer(AddPlayerCommand addPlayerCommand) throws ConnectionException;

    void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) throws ConnectionException;
}
