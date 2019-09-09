package com.phcarvalho.controller;

import com.phcarvalho.model.MainModel;
import com.phcarvalho.view.MainView;

public class MainController {

    private MainView view;
    private MainModel model;

    public MainController() {
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public void setModel(MainModel model) {
        this.model = model;
    }
}
