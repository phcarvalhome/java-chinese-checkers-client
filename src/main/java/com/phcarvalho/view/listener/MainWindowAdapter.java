package com.phcarvalho.view.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowAdapter extends WindowAdapter {

    private Runnable windowClosingRunnable;

    public MainWindowAdapter(Runnable windowClosingRunnable) {
        this.windowClosingRunnable = windowClosingRunnable;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        windowClosingRunnable.run();
    }
}
