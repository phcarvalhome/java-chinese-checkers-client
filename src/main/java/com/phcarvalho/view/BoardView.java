package com.phcarvalho.view;

import com.phcarvalho.controller.BoardController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyWithdrawalCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.StartingBoardConfiguration;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel {

    private static final String NOTIFY_VICTORY = "Notify Victory";
    private static final String NOTIFY_WITHDRAWAL = "Notify Withdrawal";

    private BoardController controller;
    private MainView mainView;
    private Map<Position, BoardPositionView> positionViewMap;

    private DialogUtil dialogUtil;

    public BoardView(BoardController controller) {
        super(new GridLayout(StartingBoardConfiguration.ROW_FINAL, StartingBoardConfiguration.COLUMN_FINAL));
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        initialize();
    }

    private void initialize(){
        TitledBorder titledBorder = new TitledBorder("Board");

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        setPreferredSize(new Dimension(880, 660));
    }

    public void addPlayer(Player player){

        if(positionViewMap == null)
            buildPositionViewMap();

        controller.addPlayer(player);
    }

    private void buildPositionViewMap() {
        positionViewMap = new HashMap<>();

        for(Integer row = StartingBoardConfiguration.ROW_INITIAL; row <= StartingBoardConfiguration.ROW_FINAL; row++)

            for(Integer column = StartingBoardConfiguration.COLUMN_INITIAL; column <= StartingBoardConfiguration.COLUMN_FINAL; column++)
                add(buildBoardPositionView(row, column));

        revalidate();
        repaint();
    }

    private BoardPositionView buildBoardPositionView(Integer row, Integer column) {
        BoardPositionView boardPositionView = new BoardPositionView(row, column);

        positionViewMap.put(boardPositionView.getPosition(), boardPositionView);

        return boardPositionView;
    }

    public void notifyVictoryByCallback(NotifyVictoryCommand notifyVictoryCommand) {
        Player player = notifyVictoryCommand.getPlayer();
        String message = String.join("",
                "The player ", player.getUser().getName(), " is the WINNER!");

        dialogUtil.showInformation(message, NOTIFY_VICTORY);
        reset(message);
    }

    private void reset(String message) {
        clear();
        mainView.getGameView().reset();
        mainView.getConnectedPlayerView().reset();
        mainView.getChatView().displaySystemMessage(message);
    }

    private void clear() {
        removeAll();
        revalidate();
        repaint();
    }

    public void notifyWithdrawalByCallback(NotifyWithdrawalCommand notifyWithdrawalCommand) {
        User localUser = Configuration.getSingleton().getLocalUser();
        Player player = notifyWithdrawalCommand.getPlayer();
        String message = String.join("",
                "The player ", player.getUser().getName(), " GAVE UP!");

//        dialogUtil.showInformation(message, NOTIFY_WITHDRAWAL);

        if(localUser.equals(notifyWithdrawalCommand.getPlayer().getUser()))
            reset(message);
    }

    public void clearPosition(Position position){
        positionViewMap.get(position).removePiece();
    }

    public void setTurnPlayer(Player turnPlayer) {
        mainView.getConnectedPlayerView().setTurnPlayer(turnPlayer);
    }

    public BoardPositionView getBoardPositionView(Position position) {
        return positionViewMap.get(position);
    }

    public void removePlayer(Player player) {
        positionViewMap.values()
                .stream()
                .filter(boardPositionView -> boardPositionView.isNotEmpty())
                .filter(boardPositionView -> boardPositionView.getPiece().getPlayer().equals(player))
                .forEach(boardPositionView -> clearPosition(boardPositionView.getPosition()));
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
