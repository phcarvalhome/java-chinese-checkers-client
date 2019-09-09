package com.phcarvalho.model;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;

public class MainModel {

    private MainController controller;

    public MainModel(MainController controller) {
        this.controller = controller;
    }
}
