package com.phcarvalho.controller;

import com.phcarvalho.model.ConnectionModel;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.exception.ConnectionException;
import com.phcarvalho.view.ConnectionView;

public class ConnectionController {

    private ConnectionView view;
    private ConnectionModel model;

    public ConnectionController() {
    }

    public void connectToServer(String host, Integer port, String userName) throws ConnectionException {
        model.connectToServer(host, port, userName);
    }

    public void connectToServerByCallback(User localUser) {
        view.connectToServerByCallback(localUser);
    }

    public void clear() {
        view.clear();
    }

    public void setView(ConnectionView view) {
        this.view = view;
    }

    public void setModel(ConnectionModel model) {
        this.model = model;
    }
}
