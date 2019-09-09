package com.phcarvalho.model.communication.connection.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.ConnectedPlayerModel;
import com.phcarvalho.model.GameModel;
import com.phcarvalho.model.MenuModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.communication.protocol.vo.RemoteEvent;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.util.DialogUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;

public class SocketHandlerStrategy implements IConnectionHandlerStrategy {

    private static final String SERVER_CONNECTION = "Server Connection";
    private static final String IO_STREAM_OPENING = "I/O Stream Opening";
    private static final String INPUT_STREAMING_PROCESSING = "Input Stream Processing";
    private static final String OUTPUT_STREAM_PROCESSING = "Remote Event Sending";
    private static final String SOCKET_CLOSING = "Socket Closing";
    private static final String INPUT_STREAM_CLOSING = "Input Stream Closing";
    private static final String OUTPUT_STREAM_CLOSING = "Output Stream Closing";
    private static final String REMOTE_EVENT_SENT = "Remote Event Sent";

    private Socket socket;
    private CommandInvoker commandInvoker;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private User localUser;

    private DialogUtil dialogUtil;

    private MenuModel menuModel;
    private GameModel gameModel;
    private ConnectedPlayerModel connectedPlayerModel;

    public SocketHandlerStrategy() {
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
    }

    @Override
    public void connectToServer(String host, Integer port, String userName){

        if((socket != null) && (socket.isConnected())){
            dialogUtil.showInformation("The server is already connected!", SERVER_CONNECTION);

            return;
        }

        try {
            socket = new Socket(host, port);
            dialogUtil.showInformation("The server is connected!", SERVER_CONNECTION);
            buildObjectIOStream();
            setLocalUser(userName);
            waitRemoteEvent();
        } catch (IOException e) {
            dialogUtil.showError("Error in the server connection!", SERVER_CONNECTION, e);
            closeResources();
        }
    }

    public void buildObjectIOStream(){

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            dialogUtil.showError("Error in the I/O stream opening!", IO_STREAM_OPENING, e);
            closeResources();
        }
    }

    private void setLocalUser(String userName) {
        localUser = User.of(userName, socket.getLocalAddress().getHostName(), socket.getLocalPort());
        menuModel.setLocalUser(localUser);
    }

    private void waitRemoteEvent() {

        Executors.newSingleThreadExecutor().execute(() -> {

            while(true){

                try {
                    RemoteEvent remoteEvent = (RemoteEvent) objectInputStream.readObject();

                    commandInvoker.execute(remoteEvent);
                } catch (IOException | ClassNotFoundException e) {
                    dialogUtil.showError("Error in the input stream processing!", INPUT_STREAMING_PROCESSING, e);
                    menuModel.clear();
                    gameModel.clear();
                    connectedPlayerModel.clear();
                    closeResources();

                    return;
                }
            }
        });
    }

    private void closeResources(){

        try {
            socket.close();
        } catch (IOException e) {
            dialogUtil.showError("Error in the socket closing!", SOCKET_CLOSING, e);
        }

        try {
            objectOutputStream.close();
        } catch (IOException e) {
            dialogUtil.showError("Error in the output stream closing!", OUTPUT_STREAM_CLOSING, e);
        }

        try {
            objectInputStream.close();
        } catch (IOException e) {
            dialogUtil.showError("Error in the input stream closing!", INPUT_STREAM_CLOSING, e);
        }
    }

    @Override
    public void send(ICommand command) {

        if((socket == null) || (!socket.isConnected()))
            dialogUtil.showError("The server is not connected!", SERVER_CONNECTION);

        try {
            RemoteEvent remoteEvent = new RemoteEvent(command);

            objectOutputStream.writeObject(remoteEvent);
            objectOutputStream.reset();
            LogUtil.logInformation(remoteEvent.toString(), REMOTE_EVENT_SENT);
        } catch (IOException e) {
            dialogUtil.showError("Error in the output stream processing!", OUTPUT_STREAM_PROCESSING, e);
            closeResources();
        }
    }

    @Override
    public void setCommandInvoker(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    @Override
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void setConnectedPlayerModel(ConnectedPlayerModel connectedPlayerModel) {
        this.connectedPlayerModel = connectedPlayerModel;
    }
}
