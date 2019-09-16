package com.phcarvalho.model;

import com.phcarvalho.controller.BoardController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.protocol.vo.command.AddPlayerCommand;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.configuration.startingposition.registry.StartingPositionConfigurationRegistry;
import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;

import java.rmi.RemoteException;

public class BoardModel {

    private BoardController controller;
    private ICommandTemplateFactory commandTemplateFactory;

    public BoardModel(BoardController controller) {
        this.controller = controller;
        commandTemplateFactory = DependencyFactory.getSingleton().get(ICommandTemplateFactory.class);
    }

    public void movePiece(Position sourcePosition, Position targetPosition, Piece piece) {
        boolean goal = isGoal(piece.getPlayer());

        if(goal){
            try {
                commandTemplateFactory.getBoard().notifyVictory(new NotifyVictoryCommand());
            } catch (RemoteException e) {
                e.printStackTrace();
                //TODO add handling...
            }
        }
        else{
            Game gameSelected = Configuration.getSingleton().getGameSelected();

            try {
                commandTemplateFactory.getBoard()
                        .movePiece(new MovePieceCommand(sourcePosition, targetPosition, piece, gameSelected));
            } catch (RemoteException e) {
                e.printStackTrace();
                //TODO add handling...
            }
        }
    }

    public void movePieceByCallback(MovePieceCommand movePieceCommand){
        Game remoteGame = movePieceCommand.getGame();

        Configuration.getSingleton().getGameSelected().setTurnPlayer(remoteGame.getTurnPlayer());
        controller.setTurnPlayer(remoteGame.getTurnPlayer());
        controller.getBoardPositionView(movePieceCommand.getTargetPosition()).setPiece(movePieceCommand.getPiece());
        clearPosition(movePieceCommand.getSourcePosition());
    }

    public boolean isGoal(Player player) {

        for(StartingPositionEnum playerBoardPosition: StartingPositionEnum.values()){
            Integer goalPositionCounter = countGoalPositionInPlayerBoardPosition(playerBoardPosition, player);

            if(goalPositionCounter == 10)
                return true;
        }

        return false;
    }

    private Integer countGoalPositionInPlayerBoardPosition(StartingPositionEnum playerBoardPosition, Player player) {
        Integer goalPositionCounter = 0;

        if(playerBoardPosition.equals(player.getStartingPosition()))
            return goalPositionCounter;

        StartingPositionConfigurationRegistry startingPositionConfigurationRegistry = DependencyFactory.getSingleton().get(StartingPositionConfigurationRegistry.class);

        for(BoardRowConfiguration boardRowConfiguration: startingPositionConfigurationRegistry.get(playerBoardPosition).getBoardRowConfigurationList())
            goalPositionCounter += countGoalPositionInBoardRowConfiguration(boardRowConfiguration, player);

        return goalPositionCounter;
    }

    private Integer countGoalPositionInBoardRowConfiguration(BoardRowConfiguration boardRowConfiguration, Player player) {
        Integer goalPositionCounter = 0;

        for(Position position: boardRowConfiguration.getPositionList()){
            Piece piece = controller.getBoardPositionView(position).getPiece();

            if((piece != null) && (piece.getPlayer().equals(player)))
                goalPositionCounter++;
        }

        return goalPositionCounter;
    }

    public void clearPosition(Position position) {
        controller.clearPosition(position);
    }

    public void removePlayerByCallback(AddPlayerCommand addPlayerCommand) {
        Game gameSelected = Configuration.getSingleton().getGameSelected();
        Integer gameId = addPlayerCommand.getGame().getId();
        Player player = addPlayerCommand.getPlayer();

        if((gameSelected != null) && (gameSelected.getId().equals(gameId)))
            removePlayer(player);
    }

    private void removePlayer(Player player) {
        controller.getPositionViewMap().values()
                .stream()
                .filter(boardPositionView -> boardPositionView.isNotEmpty())
                .filter(boardPositionView -> boardPositionView.getPiece().getPlayer().equals(player))
                .forEach(boardPositionView -> clearPosition(boardPositionView.getPosition()));
    }
}
