package com.phcarvalho.view.listener;

import com.phcarvalho.view.BoardPositionView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BoardPositionListener extends MouseAdapter {

    private static BoardPositionListener singleton;

    public static BoardPositionListener getSingleton(){

        if(singleton == null)
            singleton = new BoardPositionListener();

        return singleton;
    }

    private BoardPositionListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        BoardPositionView boardPositionView = (BoardPositionView) e.getSource();

        if(boardPositionView.isNotEmpty())
            boardPositionView.getTransferHandler().exportAsDrag(boardPositionView, e, TransferHandler.COPY);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
    }
}
