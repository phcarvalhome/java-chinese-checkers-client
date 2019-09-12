package com.phcarvalho;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.strategy.factory.RMIStrategyFactory;
import com.phcarvalho.view.MainView;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            DependencyFactory.getSingleton().setCommunicationStrategyFactory(new SocketStrategyFactory());
            DependencyFactory.getSingleton().setCommunicationStrategyFactory(new RMIStrategyFactory());
            DependencyFactory.getSingleton().build();
            DependencyFactory.getSingleton().get(MainView.class);
        });
    }
}
