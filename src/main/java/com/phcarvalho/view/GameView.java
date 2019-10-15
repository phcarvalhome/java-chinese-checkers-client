package com.phcarvalho.view;

import com.phcarvalho.controller.GameController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.AddGameCommand;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.FlagAsReadyCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;
import com.phcarvalho.model.vo.GameStatusEnum;
import com.phcarvalho.model.vo.PieceColorEnum;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.Random;

public class GameView extends JPanel {

    private static final String DEFAULT_TITLE = "Game ";
    private static final String TITLE = "Game";
    private static final String ADD_GAME = "Add Game";
    private static final String SELECT_GAME = "Select Game";
    private static final String FLAG_AS_READY = "Flag as Ready";
    private static final String GIVE_UP = "Give Up";
    private static final String EMPTY_LABEL = "-";
    private static final String SELECT_PIECE_COLOR = "Select Piece Color";
    private static final String SELECT_STARTING_POSITION = "Select Starting Position";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private GameController controller;
    private MainView mainView;
    private JList<Game> list;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton addGameButton;
    private JButton selectGameButton;
    private JLabel selectedGameLabel;
    private JLabel selectedGameValueLabel;
    private JButton flagAsReadyButton;
    private JButton giveUpButton;
    private JLabel gameStatusLabel;
    private JLabel gameStatusValueLabel;

    private DialogUtil dialogUtil;

    public GameView(GameController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        list = new JList();
        topPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel(new GridBagLayout());
        addGameButton = new JButton(ADD_GAME);
        selectGameButton = new JButton(SELECT_GAME);
        selectedGameLabel = new JLabel("Selected game:");
        selectedGameValueLabel = new JLabel(EMPTY_LABEL);
        flagAsReadyButton = new JButton(FLAG_AS_READY);
        giveUpButton = new JButton(GIVE_UP);
        gameStatusLabel = new JLabel("Status:");
        gameStatusValueLabel = new JLabel(GameStatusEnum.NOT_SELECTED.getValue());
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(topPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        add(topPanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(bottomPanel, gridBagConstraints);

        addGameButton.setEnabled(false);
        addGameButton.addActionListener(actionEvent -> add());
        addGameButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(addGameButton, gridBagConstraints);

        selectGameButton.setEnabled(false);
        selectGameButton.addActionListener(actionEvent -> select());
        selectGameButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(selectGameButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(selectedGameLabel, gridBagConstraints);

        selectedGameValueLabel.setPreferredSize(new Dimension(140, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(selectedGameValueLabel, gridBagConstraints);

        flagAsReadyButton.setEnabled(false);
        flagAsReadyButton.addActionListener(actionEvent -> flagAsReady());
        flagAsReadyButton.setPreferredSize(new Dimension(120, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(flagAsReadyButton, gridBagConstraints);

//        giveUpButton.setEnabled(false);
//        giveUpButton.addActionListener(actionEvent -> giveUp());
//        giveUpButton.setPreferredSize(new Dimension(120, 30));
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 2;
//        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
//        bottomPanel.add(giveUpButton, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(gameStatusLabel, gridBagConstraints);

        gameStatusValueLabel.setPreferredSize(new Dimension(140, 30));
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        bottomPanel.add(gameStatusValueLabel, gridBagConstraints);
    }

    private void add() {
        String title = getTitle();

        if(title != null) {
            Integer playersQuantity = getPlayersQuantity();

            if(playersQuantity != null){
                PieceColorEnum pieceColor = getColor(PieceColorEnum.getValueArray());

                if(pieceColor != null){
                    StartingPositionEnum startingPosition = getStartingPosition(StartingPositionEnum.getValueArray());

                    if(startingPosition != null){
                        User ownerUser = Configuration.getSingleton().getLocalUser();
                        Player ownerPlayer = new Player(pieceColor, startingPosition, ownerUser);
                        Game game = new Game(title, ownerPlayer, playersQuantity);
                        AddGameCommand addGameCommand = new AddGameCommand(game, false);

                        try {
                            controller.add(addGameCommand);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            //TODO add handling...
                        }
                    }
                }
            }
        }
    }

    private String getTitle() {
        String gameTitle = dialogUtil.showInput("What is the title?", ADD_GAME);

        if(gameTitle == null)
            return null;

        if(gameTitle.isEmpty())
            return DEFAULT_TITLE + new Random().nextInt();

        return gameTitle;
    }

    private Integer getPlayersQuantity(){
        Integer[] playersQuantityArray = {2, 3, 4, 5, 6};
        Integer size = dialogUtil.showInput("What are the players quantity?", ADD_GAME, playersQuantityArray, 2);

        if(size == null)
            return null;

        return size;
    }

    private PieceColorEnum getColor(String[] pieceColorArray){
        String color = dialogUtil.showInput("What is the color?", ADD_GAME, pieceColorArray, PieceColorEnum.TURQUOISE);

        if(color == null)
            return null;

        return PieceColorEnum.from(color);
    }

    private StartingPositionEnum getStartingPosition(String[] startingPositionArray){
        String startingPosition = dialogUtil.showInput("What is the position?", ADD_GAME, startingPositionArray, StartingPositionEnum.TOP);

        if(startingPosition == null)
            return null;

        return StartingPositionEnum.from(startingPosition);
    }

    public void addByCallback(AddGameCommand addGameCommand) {
        Game gameSelected = Configuration.getSingleton().getGameSelected();
        Game remoteGame = addGameCommand.getGame();
        Player ownerPlayer = addGameCommand.getGame().getOwnerPlayer();

        if((gameSelected != null) && (gameSelected.equals(remoteGame))){
            User localUser = Configuration.getSingleton().getLocalUser();

            if(localUser.equals(ownerPlayer.getUser())){
                mainView.getConnectedPlayerView().add(ownerPlayer);
                mainView.getChatView().getMessageTextField().setEnabled(true);
                addGameButton.setEnabled(false);
                selectGameButton.setEnabled(false);
                selectedGameValueLabel.setText(gameSelected.getTitle());
                gameStatusValueLabel.setText(GameStatusEnum.SELECTED.getValue());
            }

            mainView.getBoardView().addPlayer(ownerPlayer);

            String message = String.join("",
                    "The player ", ownerPlayer.getUser().getName(), " is CONNECTED!");

            mainView.getChatView().displaySystemMessage(message);
        }
    }

    public void enableFlagAsReady() {
        flagAsReadyButton.setEnabled(true);
    }

    private void select() {
        Game selectedGame = list.getSelectedValue();

        if(selectedGame == null){
            dialogUtil.showInformation("The game is not selected!", SELECT_GAME);
        }else{

            if(selectedGame.isGameStarted()){
                dialogUtil.showInformation("The game is already started!", SELECT_GAME);

                return;
            }

            if(selectedGame.isFull()){
                dialogUtil.showInformation("The game is full!", SELECT_GAME);

                return;
            }

            PieceColorEnum pieceColor = selectPieceColor(selectedGame);

            if (pieceColor == null)
                return;

            StartingPositionEnum startingPosition = selectStartingPosition(selectedGame);

            if (startingPosition == null)
                return;

            User localUser = Configuration.getSingleton().getLocalUser();
            Player player = new Player(pieceColor, startingPosition, localUser);
            AddPlayerCommand addPlayerCommand = new AddPlayerCommand(player, selectedGame);

            try {
                controller.select(addPlayerCommand);
            } catch (RemoteException e) {
                e.printStackTrace();
                //TODO add handling...
            }
        }
    }

    private PieceColorEnum selectPieceColor(Game selectedGame) {
        PieceColorEnum pieceColor = getColor(selectedGame.getFreePieceColorList());

        if(pieceColor == null)
            return null;

        if(isPieceColorAlreadySelected(selectedGame, pieceColor)){
            dialogUtil.showInformation("This color is already selected!", SELECT_PIECE_COLOR);
            select();
        }

        return pieceColor;
    }

    private boolean isPieceColorAlreadySelected(Game selectedGame, PieceColorEnum pieceColor){
        return selectedGame.getPlayerList()
                .stream()
                .filter(player -> player.getColor().equals(pieceColor))
                .findFirst()
                .isPresent();
    }

    private StartingPositionEnum selectStartingPosition(Game selectedGame) {
        StartingPositionEnum startingPosition = getStartingPosition(selectedGame.getFreeStartingPositionList());

        if(startingPosition == null)
            return null;

        if(isStartPositionAlreadySelected(selectedGame, startingPosition)){
            dialogUtil.showInformation("This starting position is already selected!", SELECT_STARTING_POSITION);
            select();
        }

        return startingPosition;
    }

    private boolean isStartPositionAlreadySelected(Game selectedGame, StartingPositionEnum startingPosition){
        return selectedGame.getPlayerList()
                .stream()
                .filter(player -> player.getStartingPosition().equals(startingPosition))
                .findFirst()
                .isPresent();
    }

    public void selectByCallback(Player player, Game game) {
        User localUser = Configuration.getSingleton().getLocalUser();

        if(localUser.equals(player.getUser())){
            mainView.getChatView().getMessageTextField().setEnabled(true);
            addGameButton.setEnabled(false);
            selectGameButton.setEnabled(false);
            selectedGameValueLabel.setText(game.getTitle());
            gameStatusValueLabel.setText(GameStatusEnum.SELECTED.getValue());
        }

        mainView.getBoardView().addPlayer(player);
        mainView.getConnectedPlayerView().add(player);

        String message = String.join("",
                "The player ", player.getUser().getName(), " is CONNECTED!");

        mainView.getChatView().displaySystemMessage(message);
    }

    private void flagAsReady() {

        try {
            controller.flagAsReady();
        } catch (RemoteException e) {
            e.printStackTrace();
            //TODO add handling...
        }

        addGameButton.setEnabled(false);
        selectGameButton.setEnabled(false);
        flagAsReadyButton.setEnabled(false);
    }

    public void flagAsReadyByCallback(FlagAsReadyCommand flagAsReadyCommand) {
        mainView.getConnectedPlayerView().setReadyPlayer(flagAsReadyCommand.getPlayer());

        if(flagAsReadyCommand.getGame().isGameStarted()){
            String message = "The game is STARTED!";

//            dialogUtil.showInformation(message, FLAG_AS_READY); //TODO tentar ajeitar isso
            giveUpButton.setEnabled(true);
            gameStatusValueLabel.setText(GameStatusEnum.STARTED.getValue());
            mainView.getConnectedPlayerView().setTurnPlayer(flagAsReadyCommand.getGame().getTurnPlayer());
            mainView.getChatView().displaySystemMessage(message);
        }else{
            User user = flagAsReadyCommand.getPlayer().getUser();
            String message = String.join("",
                    "The player ", user.getName(), " is READY!");
            User localUser = Configuration.getSingleton().getLocalUser();

            if(localUser.equals(user))
                gameStatusValueLabel.setText(GameStatusEnum.READY.getValue());

            mainView.getChatView().displaySystemMessage(message);
        }
    }

    public void enableGiveUp() {
        giveUpButton.setEnabled(true);
    }

    private void giveUp() {

        try {
            controller.giveUp();
        } catch (RemoteException e) {
            e.printStackTrace();
            //TODO add handling...
        }

//        addGameButton.setEnabled(false);
//        selectGameButton.setEnabled(false);
//        flagAsReadyButton.setEnabled(false);
    }

    public void reset() {
//        controller.clear();
        addGameButton.setEnabled(true);
        selectGameButton.setEnabled(true);
        flagAsReadyButton.setEnabled(false);
        giveUpButton.setEnabled(false);
        selectedGameValueLabel.setText(EMPTY_LABEL);
        gameStatusValueLabel.setText(GameStatusEnum.NOT_SELECTED.getValue());
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JList getList() {
        return list;
    }

    public JButton getAddGameButton() {
        return addGameButton;
    }

    public JButton getSelectGameButton() {
        return selectGameButton;
    }

    public JLabel getSelectedGameValueLabel() {
        return selectedGameValueLabel;
    }
}
