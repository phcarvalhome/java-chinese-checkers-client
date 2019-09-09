package com.phcarvalho.view.datatransfer;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.BoardModel;
import com.phcarvalho.model.BoardPositionModel;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.model.vo.Position;
import com.phcarvalho.view.BoardPositionView;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

public class BoardPositionTransferHandler extends TransferHandler {

    private static final DataFlavor DATA_FLAVOR = new DataFlavor(BoardPositionModel.class, "BoardPosition");

    private BoardModel boardModel;

    public BoardPositionTransferHandler() {
        boardModel = DependencyFactory.getSingleton().get(BoardModel.class);
    }

    @Override
    public boolean importData(TransferSupport support) {

        if (canImport(support)) {
            BoardPositionModel sourceBoardPosition = getTransferData(support);
            BoardPositionModel targetBoardPosition = getComponent(support).getBoardPosition();
            Position sourcePosition = sourceBoardPosition.getPosition();
            Position targetPosition = targetBoardPosition.getPosition();

            boardModel.movePiece(sourcePosition, targetPosition, sourceBoardPosition.getPiece());

            return true;
        }

        return false;
    }

    private BoardPositionModel getTransferData(TransferSupport support){

        try {
            Transferable transferable = support.getTransferable();
            Object transferData = transferable.getTransferData(DATA_FLAVOR);

            if (transferData instanceof BoardPositionModel)
                return (BoardPositionModel) transferData;
            else
                throw new RuntimeException("Error in the transfer data getting! Transfer data: " + transferData);
        } catch (UnsupportedFlavorException | IOException e) {
            throw new RuntimeException("Error in the transfer data getting! Transfer data type: " + BoardPositionModel.class, e);
        }
    }

    private BoardPositionView getComponent(TransferSupport support){
        Component component = support.getComponent();

        if (component instanceof BoardPositionView)
            return (BoardPositionView) component;
        else
            throw new RuntimeException("Error in the component getting! Component: " + component);
    }

    @Override
    public boolean canImport(TransferSupport support) {
        BoardPositionModel targetBoardPosition = getComponent(support).getBoardPosition();
        BoardPositionModel sourceBoardPosition = getTransferData(support);
        boolean dataFlavorSupported = support.isDataFlavorSupported(DATA_FLAVOR);
        boolean targetEnabled = targetBoardPosition.isEnabled();
        boolean targetEmpty = targetBoardPosition.isEmpty();
        boolean targetNeighbor = isNeighbor(sourceBoardPosition.getPosition(), targetBoardPosition.getPosition());
        boolean gameStarted = Configuration.getSingleton().getGameSelected().isGameStarted();
        Player turnPlayer = Configuration.getSingleton().getGameSelected().getTurnPlayer();
        Player player = Configuration.getSingleton().getPlayer();
        boolean turnEnabled = turnPlayer.equals(player);
        Piece piece = sourceBoardPosition.getPiece();
        boolean ownPiece = (piece != null) && (piece.getPlayer().equals(player));

        return (dataFlavorSupported) &&
                (targetEnabled) &&
                (targetEmpty) &&
                (targetNeighbor) &&
                (gameStarted) &&
                (turnEnabled) &&
                (ownPiece);
    }

    private boolean isNeighbor(Position sourcePosition, Position targetPosition){
        boolean topRightNeighbor = (sourcePosition.getColumn() == targetPosition.getColumn() + 1) &&
                (sourcePosition.getRow() == targetPosition.getRow() - 1);
        boolean bottomRightNeighbor = (sourcePosition.getColumn() == targetPosition.getColumn() + 1) &&
                (sourcePosition.getRow() == targetPosition.getRow() + 1);
        boolean topLeftNeighbor = (sourcePosition.getColumn() == targetPosition.getColumn() - 1) &&
                (sourcePosition.getRow() == targetPosition.getRow() - 1);
        boolean bottomLeftNeighbor = (sourcePosition.getColumn() == targetPosition.getColumn() - 1) &&
                (sourcePosition.getRow() == targetPosition.getRow() + 1);

        return (topRightNeighbor) || (bottomRightNeighbor) || (topLeftNeighbor) || (bottomLeftNeighbor);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new BoardPositionTransferable(((BoardPositionView) c).getBoardPosition());
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
    }
}