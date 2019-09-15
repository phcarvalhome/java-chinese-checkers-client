package com.phcarvalho.model;

import com.phcarvalho.controller.GameController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.Player;

import javax.swing.*;
import java.rmi.RemoteException;

public class GameModel {

    private GameController controller;
    private ICommandTemplateFactory commandTemplateFactory;
    private DefaultListModel<Game> list;

    public GameModel(GameController controller) {
        this.controller = controller;
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
        list = new DefaultListModel();
    }

    public void add(AddGameCommand addGameCommand) throws RemoteException {
        commandTemplateFactory.getMain().addGame(addGameCommand);
    }

    public void addByCallback(AddGameCommand addGameCommand) {
        User localUser = Configuration.getSingleton().getLocalUser();
        Game game = addGameCommand.getGame();
        Player ownerPlayer = game.getOwnerPlayer();

        if(localUser.equals(ownerPlayer.getUser())){
            Configuration.getSingleton().setGameSelectedAndPlayer(game, ownerPlayer);
            controller.enableReady();
        }

        if(addGameCommand.isDelete()){
            Configuration.getSingleton().removeGame(game);
            list.removeElement(game);
        }else{
            Configuration.getSingleton().addGame(game);
            controller.addByCallback(addGameCommand);
            list.addElement(game);
        }
    }

    public void select(AddPlayerCommand addPlayerCommand) throws RemoteException {
        commandTemplateFactory.getMain().addPlayer(addPlayerCommand);
    }

    public void selectByCallback(AddPlayerCommand addPlayerCommand) {
        User localUser = Configuration.getSingleton().getLocalUser();
        Player player = addPlayerCommand.getPlayer();
        Game remoteGame = addPlayerCommand.getGame();
//        Configuration.getSingleton().addGame(remoteGame); //TODO é necessário?
        Game localGame = Configuration.getSingleton().getGame(remoteGame.getId());

        if(localUser.equals(player.getUser())){
            Configuration.getSingleton().setGameSelectedAndPlayer(localGame, player);
            controller.enableReady();
            remoteGame.getPlayerList()
                    .stream()
                    .filter(playerElement -> !localGame.getPlayerList().contains(playerElement))
                    .forEach(playerElement -> localGame.addPlayer(playerElement));
            remoteGame.getPlayerList()
                    .stream()
                    .forEach(playerElement -> controller.selectByCallback(playerElement, localGame));
        }else{
            localGame.addPlayer(player);

            Game gameSelected = Configuration.getSingleton().getGameSelected();

            if((gameSelected != null) && (gameSelected.equals(remoteGame)))
                controller.selectByCallback(player, localGame);
        }
    }

    public void flagAsReady() throws RemoteException {
        Player player = Configuration.getSingleton().getPlayer();
        Game gameSelected = Configuration.getSingleton().getGameSelected();
        FlagAsReadyCommand flagAsReadyCommand = new FlagAsReadyCommand(player, gameSelected);

        commandTemplateFactory.getMain().flagAsReady(flagAsReadyCommand);
    }

    public void flagAsReadyByCallback(FlagAsReadyCommand flagAsReadyCommand) {
        Game remoteGame = flagAsReadyCommand.getGame();
        User localUser = Configuration.getSingleton().getLocalUser();

        if(remoteGame.getRemoteUserList().contains(localUser)){

            if(remoteGame.isGameStarted())
                Configuration.getSingleton().getGameSelected().setGameStarted(true);

            controller.flagAsReadyByCallback(flagAsReadyCommand);
        }else{
            Game localGame = Configuration.getSingleton().getGame(remoteGame.getId());

            localGame.setGameStarted(true);
        }
    }

    public void clear() {
        list.clear();
    }

    public DefaultListModel<Game> getList() {
        return list;
    }
}
