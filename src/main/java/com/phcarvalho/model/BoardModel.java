package com.phcarvalho.model;

import com.phcarvalho.controller.BoardController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.remote.IBoardRemoteCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.command.MovePieceCommand;
import com.phcarvalho.model.communication.protocol.vo.command.NotifyVictoryCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;
import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.startingposition.registry.StartingPositionConfigurationRegistry;
import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;

public class BoardModel {

    private BoardController controller;
    private IBoardRemoteCommandTemplate boardRemoteCommandTemplate;

    public BoardModel(BoardController controller) {
        this.controller = controller;
        boardRemoteCommandTemplate = DependencyFactory.getSingleton().get(IBoardRemoteCommandTemplate.class);
    }

    public void movePiece(Position sourcePosition, Position targetPosition, Piece piece) {
        boolean goal = isGoal(piece.getPlayer());

        if(goal){
            boardRemoteCommandTemplate.notifyVictory(new NotifyVictoryCommand());
        }
        else{
            Game gameSelected = Configuration.getSingleton().getGameSelected();

            boardRemoteCommandTemplate.movePiece(new MovePieceCommand(sourcePosition, targetPosition, piece, gameSelected));
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
}
