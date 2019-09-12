package com.phcarvalho.controller;

import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.view.GameView;

import java.rmi.RemoteException;

public class GameController {

    private GameView view;
    private GameModel model;

    public GameController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

    public void add(AddGameCommand addGameCommand) throws RemoteException {
        model.add(addGameCommand);
    }

    public void addByCallback(AddGameCommand addGameCommand) {
        view.addByCallback(addGameCommand);
    }

    public void enableReady() {
        view.enableFlagAsReady();
    }

    public void select(AddPlayerCommand addPlayerCommand) throws RemoteException {
        model.select(addPlayerCommand);
    }

    public void selectByCallback(Player player, Game game) {
        view.selectByCallback(player, game);
    }

    public void flagAsReady() throws RemoteException {
        model.flagAsReady();
    }

    public void flagAsReadyByCallback(FlagAsReadyCommand flagAsReadyCommand) {
        view.flagAsReadyByCallback(flagAsReadyCommand);
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void setModel(GameModel model) {
        this.model = model;
    }
}
