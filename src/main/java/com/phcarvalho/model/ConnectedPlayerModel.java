package com.phcarvalho.model;

import com.phcarvalho.controller.ConnectedPlayerController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;
import com.phcarvalho.model.vo.Player;

import javax.swing.*;

public class ConnectedPlayerModel {

    private ConnectedPlayerController controller;
    private IMainCommandTemplate mainCommandTemplate;
    private DefaultListModel<Player> list;

    public ConnectedPlayerModel(ConnectedPlayerController controller) {
        this.controller = controller;
        mainCommandTemplate = DependencyFactory.getSingleton().get(IMainCommandTemplate.class);
        list = new DefaultListModel();
    }

    public void add(Player player) {
        list.addElement(player);
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
