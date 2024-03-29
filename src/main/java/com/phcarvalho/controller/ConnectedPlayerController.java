package com.phcarvalho.controller;

import com.phcarvalho.model.ConnectedPlayerModel;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.view.ConnectedPlayerView;

public class ConnectedPlayerController {

    private ConnectedPlayerView view;
    private ConnectedPlayerModel model;

    public ConnectedPlayerController() {
    }

    public void initializeList() {
        view.getList().setModel(model.getList());
    }

    public void add(Player player) {
        model.add(player);
    }

    public void removeByCallback(Player player) {
        view.removeByCallback(player);
    }

    public int getPlayerIndex(Player player) {
        return model.getPlayerIndex(player);
    }

    public void clear() {
        model.clear();
    }

    public void setView(ConnectedPlayerView view) {
        this.view = view;
    }

    public void setModel(ConnectedPlayerModel model) {
        this.model = model;
    }
}
