package com.phcarvalho.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatTextPaneKeyListener implements KeyListener {

    private Runnable keyPressedRunnable;

    public ChatTextPaneKeyListener(Runnable keyPressedRunnable) {
        this.keyPressedRunnable = keyPressedRunnable;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            keyPressedRunnable.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
