package com.phcarvalho.view;

import com.phcarvalho.controller.MainController;
import com.phcarvalho.dependencyfactory.DependencyFactory;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private static final String TITLE = "Chinese Checkers - Client";

    private MainController controller;
    private JPanel mainPanel;
    private JPanel topLeftPanel;
    private JPanel topRightPanel;
    private JPanel bottomLeftPanel;
//    private JPanel bottomRightPanel;
    private BoardView boardView;
    private ConnectedPlayerView connectedPlayerView;
    private GameView gameView;
    private MenuView menuView;

    public MainView(MainController controller) {
        this.controller = controller;
        mainPanel = new JPanel(new GridBagLayout());
        topLeftPanel = new JPanel(new GridBagLayout());
        topRightPanel = new JPanel(new GridBagLayout());
        bottomLeftPanel = new JPanel(new GridBagLayout());
//        bottomRightPanel = new JPanel(new GridBagLayout());
        boardView = DependencyFactory.getSingleton().get(BoardView.class);
        connectedPlayerView = DependencyFactory.getSingleton().get(ConnectedPlayerView.class);
        gameView = DependencyFactory.getSingleton().get(GameView.class);
        menuView = DependencyFactory.getSingleton().get(MenuView.class);
        initialize();
    }

    private void initialize() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(topLeftPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topLeftPanel.add(boardView, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(topRightPanel, gridBagConstraints);


        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topRightPanel.add(gameView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        topRightPanel.add(connectedPlayerView, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        mainPanel.add(bottomLeftPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomLeftPanel.add(menuView, gridBagConstraints);

//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 1;
//        mainPanel.add(bottomRightPanel, gridBagConstraints);
//
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        bottomRightPanel.add(menuView, gridBagConstraints);

        setTitle(TITLE);
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public ConnectedPlayerView getConnectedPlayerView() {
        return connectedPlayerView;
    }

    public GameView getGameView() {
        return gameView;
    }

    public MenuView getMenuView() {
        return menuView;
    }
}
