package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectedPlayerController;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.Player;

import javax.swing.*;

public class ConnectedPlayerModel {

    private ConnectedPlayerController controller;
    private DefaultListModel<Player> list;

    public ConnectedPlayerModel(ConnectedPlayerController controller) {
        this.controller = controller;
        list = new DefaultListModel();
    }

    public void add(Player player) {
        list.addElement(player);
    }

    public void removeByCallback(AddPlayerCommand addPlayerCommand) {
        Integer gameId = addPlayerCommand.getGame().getId();
        User sourceUser = addPlayerCommand.getSourceUser();

        Configuration.getSingleton().getGame(gameId).removeUser(sourceUser);

        Game gameSelected = Configuration.getSingleton().getGameSelected();
        Player player = addPlayerCommand.getPlayer();

        if((gameSelected != null) && (gameSelected.getId().equals(gameId))){
            list.removeElement(player);
            controller.removeByCallback(player);
        }
    }

    public void clear() {
        list.clear();
    }

    public int getPlayerIndex(Player player) {
        return list.indexOf(player);
    }

    public DefaultListModel<Player> getList() {
        return list;
    }
}
