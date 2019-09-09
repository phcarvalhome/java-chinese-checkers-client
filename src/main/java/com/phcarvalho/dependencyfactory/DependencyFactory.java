package com.phcarvalho.dependencyfactory;

import com.phcarvalho.controller.*;
import com.phcarvalho.model.*;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.commandtemplate.remote.IBoardRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.IChatRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.IMainRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.BoardRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.ChatRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.MainRemoteCommandTemplate;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.communication.connection.socket.SocketHandlerStrategy;
import com.phcarvalho.model.configuration.startingposition.registry.StartingPositionConfigurationRegistry;
import com.phcarvalho.view.*;
import com.phcarvalho.view.datatransfer.BoardPositionTransferHandler;
import com.phcarvalho.view.util.DialogUtil;

import java.util.HashMap;
import java.util.Map;

public class DependencyFactory {

    private static DependencyFactory singleton;

    public static DependencyFactory getSingleton(){

        if(singleton == null)
            singleton = new DependencyFactory();

        return singleton;
    }

    private Map<Class<?>, Object> dependencyMap;

    private DependencyFactory() {
        dependencyMap = new HashMap<>();
    }

    public void build(){
        dependencyMap.put(DialogUtil.class, new DialogUtil());
        dependencyMap.put(CommandInvoker.class, new CommandInvoker());
        dependencyMap.put(IConnectionHandlerStrategy.class, new SocketHandlerStrategy());
        dependencyMap.put(StartingPositionConfigurationRegistry.class, new StartingPositionConfigurationRegistry());

        dependencyMap.put(IMainRemoteCommandTemplate.class, new MainRemoteCommandTemplate());
        dependencyMap.put(IBoardRemoteCommandTemplate.class, new BoardRemoteCommandTemplate());
        dependencyMap.put(IChatRemoteCommandTemplate.class, new ChatRemoteCommandTemplate());

        buildBoardMVC();
        buildConnectedPlayerMVC();
        buildGameMVC();
        buildMenuMVC();
        buildMainMVC();

        get(CommandInvoker.class).buildCommandObserverMap();
        get(IConnectionHandlerStrategy.class).setCommandInvoker(get(CommandInvoker.class));

        dependencyMap.put(BoardPositionTransferHandler.class, new BoardPositionTransferHandler());
    }

    private void buildBoardMVC() {
        BoardController boardController = new BoardController();
        BoardView boardView = new BoardView(boardController);
        BoardModel boardModel = new BoardModel(boardController);

        boardController.setView(boardView);
        boardController.setModel(boardModel);
        dependencyMap.put(BoardView.class, boardView);
        dependencyMap.put(BoardModel.class, boardModel);
    }

    private void buildConnectedPlayerMVC() {
        ConnectedPlayerController connectedPlayerController = new ConnectedPlayerController();
        ConnectedPlayerView connectedPlayerView = new ConnectedPlayerView(connectedPlayerController);
        ConnectedPlayerModel connectedPlayerModel = new ConnectedPlayerModel(connectedPlayerController);

        connectedPlayerController.setView(connectedPlayerView);
        connectedPlayerController.setModel(connectedPlayerModel);
        connectedPlayerController.initializeList();
        dependencyMap.put(ConnectedPlayerView.class, connectedPlayerView);
        dependencyMap.put(ConnectedPlayerModel.class, connectedPlayerModel);
        get(IConnectionHandlerStrategy.class).setConnectedPlayerModel(connectedPlayerModel);
    }

    private void buildGameMVC() {
        GameController gameController = new GameController();
        GameView gameView = new GameView(gameController);
        GameModel gameModel = new GameModel(gameController);

        gameController.setView(gameView);
        gameController.setModel(gameModel);
        gameController.initializeList();
        dependencyMap.put(GameView.class, gameView);
        dependencyMap.put(GameModel.class, gameModel);
        get(IConnectionHandlerStrategy.class).setGameModel(gameModel);
    }

    private void buildMenuMVC() {
        MenuController menuController = new MenuController();
        MenuView menuView = new MenuView(menuController);
        MenuModel menuModel = new MenuModel(menuController);

        menuController.setView(menuView);
        menuController.setModel(menuModel);
        dependencyMap.put(MenuView.class, menuView);
        dependencyMap.put(MenuModel.class, menuModel);
        get(IConnectionHandlerStrategy.class).setMenuModel(menuModel);
    }

    private void buildMainMVC() {
        MainController mainController = new MainController();
        MainView mainView = new MainView(mainController);
        MainModel mainModel = new MainModel(mainController);

        mainController.setView(mainView);
        mainController.setModel(mainModel);
        dependencyMap.put(MainView.class, mainView);
        dependencyMap.put(MainModel.class, mainModel);
        get(DialogUtil.class).setMainView(mainView);
        get(BoardView.class).setMainView(mainView);
        get(ConnectedPlayerView.class).setMainView(mainView);
        get(GameView.class).setMainView(mainView);
        get(MenuView.class).setMainView(mainView);
    }

    public <T> T get(Class<T> type){
        Object dependency = dependencyMap.get(type);

        if(dependency == null)
            throw new RuntimeException("The dependency is null! Type: " + type);

        return (T) dependency;
    }
}
