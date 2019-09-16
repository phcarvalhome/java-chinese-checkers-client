package com.phcarvalho.controller;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.BoardModel;
import com.phcarvalho.model.configuration.startingposition.IStartingPositionConfiguration;
import com.phcarvalho.model.configuration.startingposition.registry.StartingPositionConfigurationRegistry;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;
import com.phcarvalho.view.BoardPositionView;
import com.phcarvalho.view.BoardView;

import java.util.Map;

public class BoardController {

    private BoardView view;
    private BoardModel model;

    public BoardController() {
    }

    public void addPlayer(Player player) {
        StartingPositionConfigurationRegistry startingPositionConfigurationRegistry = DependencyFactory.getSingleton().get(StartingPositionConfigurationRegistry.class);
        IStartingPositionConfiguration playerBoardConfiguration = startingPositionConfigurationRegistry.get(player.getStartingPosition());

        playerBoardConfiguration.getBoardRowConfigurationList()
                .forEach(boardRowConfiguration -> boardRowConfiguration.getPositionList()
                        .forEach(position -> view.getBoardPositionView(position).setPiece(new Piece(player))));
    }

    public void clearPosition(Position position) {
        view.clearPosition(position);
    }

    public void setTurnPlayer(Player turnPlayer) {
        view.setTurnPlayer(turnPlayer);
    }

    public BoardPositionView getBoardPositionView(Position position) {
        return view.getBoardPositionView(position);
    }

    public Map<Position, BoardPositionView> getPositionViewMap() {
        return view.getPositionViewMap();
    }

    public void setView(BoardView view) {
        this.view = view;
    }

    public void setModel(BoardModel model) {
        this.model = model;
    }
}
