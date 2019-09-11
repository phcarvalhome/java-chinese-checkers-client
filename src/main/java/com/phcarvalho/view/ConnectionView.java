package com.phcarvalho.view;

import com.phcarvalho.controller.ConnectionController;
import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.exception.ConnectionException;
import com.phcarvalho.view.util.DialogUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Random;

public class ConnectionView extends JPanel {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9999;
    private static final String DEFAULT_USER_NAME = "Player ";
    private static final String CONNECT_TO_SERVER = "Connect To Server";
    private static final String EMPTY_LABEL = "-";

    private ConnectionController controller;
    private MainView mainView;
    private DialogUtil dialogUtil;
    private JButton connectToServerButton;
    private JLabel userNameLabel;
    private JLabel userNameValueLabel;
    private JLabel localHostLabel;
    private JLabel localHostValueLabel;
    private JLabel localPortLabel;
    private JLabel localPortValueLabel;

    public ConnectionView(ConnectionController controller) {
        super(new GridBagLayout());
        this.controller = controller;
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
        connectToServerButton = new JButton(CONNECT_TO_SERVER);
        userNameLabel = new JLabel("User name:");
        userNameValueLabel = new JLabel(EMPTY_LABEL);
        localHostLabel = new JLabel("Host:");
        localHostValueLabel = new JLabel(EMPTY_LABEL);
        localPortLabel = new JLabel("Port:");
        localPortValueLabel = new JLabel(EMPTY_LABEL);
        initialize();
    }

    private void initialize() {
        TitledBorder titledBorder = new TitledBorder("Connection");

        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBorder(titledBorder);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        connectToServerButton.addActionListener(actionEvent -> connectToServer());
        connectToServerButton.setPreferredSize(new Dimension(160, 30));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(connectToServerButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(userNameLabel, gridBagConstraints);

        userNameValueLabel.setPreferredSize(new Dimension(200, 40));
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(userNameValueLabel, gridBagConstraints);

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localHostLabel, gridBagConstraints);

        localHostValueLabel.setPreferredSize(new Dimension(200, 40));
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localHostValueLabel, gridBagConstraints);

        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localPortLabel, gridBagConstraints);

        localPortValueLabel.setPreferredSize(new Dimension(50, 40));
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(2, 4, 2, 4);
        add(localPortValueLabel, gridBagConstraints);
    }

    private void connectToServer(){
        String host = getHost();

        if(host != null){
            Integer port = getPort();

            if(port != null){
                String userName = getUserName();

                if(userName != null) {

                    try {
                        controller.connectToServer(host, port, userName);
                    } catch (ConnectionException e) {
                        dialogUtil.showError(e.getMessage(), e.getTitle(), e);
                    }
                }
            }
        }
    }

    private String getHost() {
        String userName = dialogUtil.showInput("What is the host?", CONNECT_TO_SERVER);

        if(userName == null)
            return null;

        if(userName.isEmpty())
            return DEFAULT_HOST;

        return userName;
    }

    private Integer getPort() {
        String portAsString = dialogUtil.showInput("What is the port?", CONNECT_TO_SERVER);

        if(portAsString == null)
            return null;

        if(portAsString.isEmpty())
            return DEFAULT_PORT;

        try {
            return Integer.valueOf(portAsString);
        } catch (NumberFormatException e) {
            dialogUtil.showError("The port must be an integer!", CONNECT_TO_SERVER);
        }

        return getPort();
    }

    private String getUserName() {
        String userName = dialogUtil.showInput("What is the user name?", CONNECT_TO_SERVER);

        if(userName == null)
            return null;

        if(userName.isEmpty())
            return DEFAULT_USER_NAME + new Random().nextInt();

        return userName;
    }

    public void connectToServerByCallback(User localUser) {
        userNameValueLabel.setText(localUser.getName());
        localHostValueLabel.setText(localUser.getHost());
        localPortValueLabel.setText(localUser.getPort().toString());
        connectToServerButton.setEnabled(false);
        mainView.getGameView().getAddGameButton().setEnabled(true);
        mainView.getGameView().getSelectGameButton().setEnabled(true);
//        mainView.getConnectedPlayerView().getStartGameButton().setEnabled(true);
//        mainView.getChatView().getMessageTextField().setEnabled(true);
        mainView.getChatView().displaySystemMessage("The server is connected!");
//        dialogUtil.showInformation("The server is connected!", SERVER_CONNECTION);
    }

    public void clear() {
        userNameValueLabel.setText(EMPTY_LABEL);
        localHostValueLabel.setText(EMPTY_LABEL);
        localPortValueLabel.setText(EMPTY_LABEL);
//        connectToServerButton.setEnabled(true);
        mainView.getGameView().getAddGameButton().setEnabled(false);
        mainView.getGameView().getSelectGameButton().setEnabled(false);
//        mainView.getConnectedPlayerView().getStartGameButton().setEnabled(false);
        mainView.getChatView().getMessageTextField().setEnabled(false);
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
}
