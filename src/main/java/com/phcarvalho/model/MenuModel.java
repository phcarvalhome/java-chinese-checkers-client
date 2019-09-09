package com.phcarvalho.model;

import com.phcarvalho.controller.MenuController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

public class MenuModel {

    private MenuController controller;
    private IConnectionHandlerStrategy connectionHandlerStrategy;

    public MenuModel(MenuController controller) {
        this.controller = controller;
        connectionHandlerStrategy = DependencyFactory.getSingleton().get(IConnectionHandlerStrategy.class);
    }

    public void connectToServer(String host, Integer port, String userName) {
        connectionHandlerStrategy.connectToServer(host, port, userName);
    }

    public void setLocalUser(User localUser) {
        Configuration.getSingleton().setServerConnected(true);
        Configuration.getSingleton().setLocalUser(localUser);
        controller.setLocalUser(localUser);
    }

    public void clear() {
        Configuration.getSingleton().clear();
        controller.clear();
    }
}
