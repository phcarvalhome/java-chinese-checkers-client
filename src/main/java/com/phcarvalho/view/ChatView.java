package com.phcarvalho.view;

import com.phcarvalho.controller.ChatController;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.exception.ConnectionException;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.model.vo.Player;
import com.phcarvalho.view.listener.ChatTextPaneKeyListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;

public class ChatView extends JPanel {

    private static final String TITLE = "Chat";
    private static final int WIDTH = 280;
    private static final int HEIGHT = 120;

    private ChatController controller;
    private MainView mainView;
    private JTextPane displayTextPane;
    private JTextField messageTextField;

    public ChatView(ChatController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        displayTextPane = new JTextPane();
        messageTextField = new JTextField();
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder(TITLE);

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        displayTextPane.setEditable(false);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);

        JScrollPane scrollPane = new JScrollPane(displayTextPane);

        scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(scrollPane, gridBagConstraints);
        messageTextField.setEnabled(false);
        messageTextField.addKeyListener(new ChatTextPaneKeyListener(() -> displayMessage()));
        messageTextField.setPreferredSize(new Dimension(WIDTH, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(messageTextField, gridBagConstraints);
    }

    private void displayMessage() {
        Integer gameId = Configuration.getSingleton().getGameSelected().getId();
        Player player = Configuration.getSingleton().getPlayer();
        String message = messageTextField.getText();
        SendMessageCommand sendMessageCommand = new SendMessageCommand(gameId, player, message);

        try {
            controller.sendMessage(sendMessageCommand);
        } catch (ConnectionException e) {
            e.printStackTrace();
            //TODO add handling...
        }

        messageTextField.setText("");
    }

    public void sendMessageByCallback(SendMessageCommand sendMessageCommand) {
        String sourceUser = sendMessageCommand.getPlayer().getUser().getName();

        sourceUser = sourceUser.replace(">>> ", "");
        displayMessage(sourceUser, sendMessageCommand.getMessage(), sendMessageCommand.getPlayer().getColor().getColor());
    }

    private void displayMessage(String sourceUser, String message, Color color){
        append(sourceUser + ": ", buildSimpleAttributeSetWithBold(color));
        append(message + "\n", buildSimpleAttributeSet(color));
    }

    private void append(String message, AttributeSet attributeSet) {

        try {
            int length = displayTextPane.getDocument().getLength();

            displayTextPane.setCaretPosition(length);
            displayTextPane.getDocument().insertString(length, message, attributeSet);
        } catch (BadLocationException e) {
            LogUtil.logError("Error in the chat message insertion", "Insert Chat Message", e);
        }
    }

    private SimpleAttributeSet buildSimpleAttributeSetWithBold(Color color) {
        SimpleAttributeSet simpleAttributeSet = buildSimpleAttributeSet(color);

        StyleConstants.setBold(simpleAttributeSet, true);

        return simpleAttributeSet;
    }

    private SimpleAttributeSet buildSimpleAttributeSet(Color color) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();

        StyleConstants.setForeground(simpleAttributeSet, color);
        StyleConstants.setFontFamily(simpleAttributeSet, "Lucida Console");
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_JUSTIFIED);

        return simpleAttributeSet;
    }

    public void displaySystemMessage(String message){
        displayMessage("SYSTEM", message, Color.BLACK);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public JTextField getMessageTextField() {
        return messageTextField;
    }
}
