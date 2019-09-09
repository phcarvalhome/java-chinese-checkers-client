package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;

public class MainLocalCommandTemplate implements ILocalCommandTemplate {

    private GameModel gameModel;

    public MainLocalCommandTemplate() {
        gameModel = DependencyFactory.getSingleton().get(GameModel.class);
    }

    public void addGame(AddGameCommand addGameCommand){
        gameModel.addByCallback(addGameCommand);
    }

    public void addPlayer(AddPlayerCommand addPlayerCommand) {
        gameModel.selectByCallback(addPlayerCommand);
    }

    public void flagAsReady(FlagAsReadyCommand flagAsReadyCommand) {
        gameModel.flagAsReadyByCallback(flagAsReadyCommand);
    }
}
