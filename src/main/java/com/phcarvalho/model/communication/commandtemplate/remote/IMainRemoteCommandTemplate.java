package com.phcarvalho.model.communication.commandtemplate.remote;

import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;

public interface IMainRemoteCommandTemplate {

    void addGame(AddGameCommand addGameCommand);

    void addPlayer(AddPlayerCommand addPlayerCommand);

    void flagAsReady(FlagAsReadyCommand flagAsReadyCommand);
}
