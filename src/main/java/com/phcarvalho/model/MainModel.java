package com.phcarvalho.model;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.entity.User;

public class MainModel {

    private MainController controller;
    private ConnectionModel connectionModel;
    private GameModel gameModel;
    private ConnectedPlayerModel connectedPlayerModel;

    public MainModel(MainController controller) {
        this.controller = controller;
        connectionModel = DependencyFactory.getSingleton().get(ConnectionModel.class);
        gameModel = DependencyFactory.getSingleton().get(GameModel.class);
        connectedPlayerModel = DependencyFactory.getSingleton().get(ConnectedPlayerModel.class);
    }

    public void clear(){
        connectionModel.clear();
        gameModel.clear();
        connectedPlayerModel.clear();
    }
}
