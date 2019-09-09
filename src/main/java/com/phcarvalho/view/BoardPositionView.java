package com.phcarvalho.view;

import com.phcarvalho.controller.BoardPositionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.BoardPositionModel;
import com.phcarvalho.model.vo.BoardPositionIconEnum;
import com.phcarvalho.model.vo.Piece;
import com.phcarvalho.model.vo.Position;
import com.phcarvalho.view.datatransfer.BoardPositionTransferHandler;
import com.phcarvalho.view.listener.BoardPositionListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class BoardPositionView extends JButton {

    private static Integer SIZE_WIDTH = 38;
    private static Integer SIZE_HEIGHT = 38;

    private BoardPositionController controller;
    private BoardPositionTransferHandler boardPositionTransferHandler;

    public BoardPositionView(Integer row, Integer column) {
        controller = new BoardPositionController(this, row, column);
        boardPositionTransferHandler = DependencyFactory.getSingleton().get(BoardPositionTransferHandler.class);
        initialize();
    }

    private void initialize() {
        setInitialIcon();
        setBackground((controller.getBoardPositionModel().isEnabled()) ? (Color.WHITE) : (Color.BLACK));
        setPreferredSize(new Dimension(SIZE_WIDTH, SIZE_HEIGHT));
        setBorder(BorderFactory.createEmptyBorder());
        setTransferHandler(boardPositionTransferHandler);
        addMouseMotionListener(BoardPositionListener.getSingleton());
    }

    private void setInitialIcon() {
        BoardPositionIconEnum boardPositionIcon = controller.getBoardPositionIcon();

        setBoardPositionIcon(boardPositionIcon);
    }

    private void setBoardPositionIcon(BoardPositionIconEnum boardPositionIcon) {

        if(boardPositionIcon.EMPTY.equals(boardPositionIcon)){
            setBackground(Color.WHITE);
            setIcon(null);

            return;
        }

        if(boardPositionIcon.DISABLED.equals(boardPositionIcon)){
            setBackground(Color.BLACK);
            setIcon(null);

            return;
        }

        URL url = getClass().getClassLoader().getResource(boardPositionIcon.getFileName());
        Image image = new ImageIcon(url).getImage()
                .getScaledInstance(SIZE_WIDTH - 6, SIZE_HEIGHT - 6,  java.awt.Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(image));
    }

    public boolean isEmpty() {
        return controller.isEmpty();
    }

    public boolean isNotEmpty() {
        return controller.isNotEmpty();
    }

    public void removePiece() {
        controller.removePiece();
        setBoardPositionIcon(BoardPositionIconEnum.EMPTY);
    }

    public Position getPosition() {
        return controller.getPosition();
    }

    public Piece getPiece() {
        return controller.getPiece();
    }

    public void setPiece(Piece piece) {
        controller.setPiece(piece);
        setBoardPositionIcon(BoardPositionIconEnum.from(piece.getPlayer().getColor()));
    }

    public BoardPositionModel getBoardPosition() {
        return controller.getBoardPositionModel();
    }
}
