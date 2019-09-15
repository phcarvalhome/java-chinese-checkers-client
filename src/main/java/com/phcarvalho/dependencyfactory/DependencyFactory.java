package com.phcarvalho.dependencyfactory;

import com.phcarvalho.controller.*;
import com.phcarvalho.model.*;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.strategy.factory.ICommunicationStrategy;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;
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

    private ICommunicationStrategy communicationStrategyFactory;
    private Map<Class<?>, Object> dependencyMap;

    private DependencyFactory() {
        dependencyMap = new HashMap<>();
    }

    public void build(){
        dependencyMap.put(DialogUtil.class, new DialogUtil());
        dependencyMap.put(CommandInvoker.class, new CommandInvoker());
        dependencyMap.put(SocketConnectionStrategy.class, new SocketConnectionStrategy());
        dependencyMap.put(IConnectionStrategy.class, communicationStrategyFactory.buildConnectionStrategy());
        dependencyMap.put(ICommandTemplateFactory.class, communicationStrategyFactory.buildCommandTemplateFactory());
        dependencyMap.put(StartingPositionConfigurationRegistry.class, new StartingPositionConfigurationRegistry());

//        dependencyMap.put(IConnectionCommandTemplate.class, get(ICommandTemplateFactory.class).getConnection());
//        dependencyMap.put(IBoardCommandTemplate.class, get(ICommandTemplateFactory.class).getBoard());
//        dependencyMap.put(IChatCommandTemplate.class, get(ICommandTemplateFactory.class).getChat());
//        dependencyMap.put(IMainCommandTemplate.class, get(ICommandTemplateFactory.class).getMain());

        buildBoardMVC();
        buildConnectedPlayerMVC();
        buildGameMVC();
        buildConnectionMVC();
        buildChatMVC();
        buildMainMVC();

        get(CommandInvoker.class).buildCommandObserverMap();
//        get(IConnectionStrategy.class).setCommandInvoker(get(CommandInvoker.class));

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
//        get(IConnectionHandlerStrategy.class).setConnectedPlayerModel(connectedPlayerModel);
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
//        get(IConnectionHandlerStrategy.class).setGameModel(gameModel);
    }

    private void buildConnectionMVC() {
        ConnectionController connectionController = new ConnectionController();
        ConnectionView connectionView = new ConnectionView(connectionController);
        ConnectionModel connectionModel = new ConnectionModel(connectionController);

        connectionController.setView(connectionView);
        connectionController.setModel(connectionModel);
        dependencyMap.put(ConnectionView.class, connectionView);
        dependencyMap.put(ConnectionModel.class, connectionModel);
//        get(IConnectionHandlerStrategy.class).setConnectionModel(connectionModel);
    }

    private void buildChatMVC() {
        ChatController chatController = new ChatController();
        ChatView chatView = new ChatView(chatController);
        ChatModel chatModel = new ChatModel(chatController);

        chatController.setView(chatView);
        chatController.setModel(chatModel);
        dependencyMap.put(ChatView.class, chatView);
        dependencyMap.put(ChatModel.class, chatModel);
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
        get(ConnectionView.class).setMainView(mainView);
        get(ChatView.class).setMainView(mainView);
//        get(IConnectionStrategy.class).setMainModel(mainModel);
        //TODO talvez fazer o set de cada model que foi colocado lá...
    }

    public <T> T get(Class<T> classType){
        Object dependency = dependencyMap.get(classType);

        if(dependency == null)
            throw new RuntimeException("The dependency is null! Type: " + classType);

        return (T) dependency;
    }

    public void setCommunicationStrategyFactory(ICommunicationStrategy communicationStrategyFactory) {
        this.communicationStrategyFactory = communicationStrategyFactory;
    }
}
