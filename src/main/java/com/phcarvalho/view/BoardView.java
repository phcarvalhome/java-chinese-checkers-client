package com.phcarvalho.view;

import com.phcarvalho.controller.BoardController;
import com.phcarvalho.model.configuration.StartingBoardConfiguration;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel {

    private BoardController controller;
    private MainView mainView;
    private Map<Position, BoardPositionView> positionViewMap;

    public BoardView(BoardController controller) {
        super(new GridLayout(StartingBoardConfiguration.ROW_FINAL, StartingBoardConfiguration.COLUMN_FINAL));
        this.controller = controller;
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

    public void clearPosition(Position position){
        positionViewMap.get(position).removePiece();
    }

    public void setTurnPlayer(Player turnPlayer) {
        mainView.getConnectedPlayerView().setTurnPlayer(turnPlayer);
    }

    public BoardPositionView getBoardPositionView(Position position) {
        return positionViewMap.get(position);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public Map<Position, BoardPositionView> getPositionViewMap() {
        return positionViewMap;
    }
}
