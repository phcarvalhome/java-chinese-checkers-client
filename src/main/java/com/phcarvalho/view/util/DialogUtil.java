package com.phcarvalho.view.util;

import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.MainView;

import javax.swing.*;

public class DialogUtil {

    private MainView mainView;

    public DialogUtil() {
    }

    public void showInformation(String message, String title){
        JOptionPane.showMessageDialog(mainView, message, title, JOptionPane.INFORMATION_MESSAGE);
        LogUtil.logInformation(message, title);
    }

    public void showError(String message, String title){
        JOptionPane.showMessageDialog(mainView, message, title, JOptionPane.ERROR_MESSAGE);
        LogUtil.logError(message, title);
    }

    public void showError(String message, String title, Exception exception){
        JOptionPane.showMessageDialog(mainView, message, title, JOptionPane.ERROR_MESSAGE);
        LogUtil.logError(message, title, exception);
    }

    public String showInput(String message, String title){
        return JOptionPane.showInputDialog(mainView, message, title, JOptionPane.QUESTION_MESSAGE);
    }

    public <T> T showInput(String message, String title, T[] optionArray, Object initialValue){
        return (T) JOptionPane.showInputDialog(mainView, message, title, JOptionPane.QUESTION_MESSAGE, null, optionArray, initialValue);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
