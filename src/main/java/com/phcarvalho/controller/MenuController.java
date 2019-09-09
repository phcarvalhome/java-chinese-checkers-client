package com.phcarvalho.controller;

import com.phcarvalho.model.MenuModel;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.view.MenuView;

public class MenuController {

    private MenuView view;
    private MenuModel model;

    public MenuController() {
    }

    public void connectToServer(String host, Integer port, String userName) {
        model.connectToServer(host, port, userName);
    }

    public void setLocalUser(User localUser) {
        view.setLocalUser(localUser);
    }

    public void clear() {
        view.clear();
    }

    public void setView(MenuView view) {
        this.view = view;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
}
