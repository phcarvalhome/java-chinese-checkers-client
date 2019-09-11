package com.phcarvalho.model.communication.connection.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.connection.IConnectionHandlerStrategy;
import com.phcarvalho.model.communication.protocol.vo.RemoteEvent;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.exception.ConnectionException;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.util.DialogUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Executors;

public class SocketHandlerStrategy implements IConnectionHandlerStrategy {

    private static final String SERVER_CONNECTION = "Server Connection";
    public static final String RECEIVE_REMOTE_COMMAND = "Receive Remote Command";
    public static final String SEND_REMOTE_COMMAND = "Send Remote Command";
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

    private MainModel mainModel;

    public SocketHandlerStrategy() {
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
    }

    @Override
    public void connectToServer(String host, Integer port, String userName) throws ConnectionException {

        if((socket != null) && (socket.isConnected()))
            throw new ConnectionException("The server is already connected!", SERVER_CONNECTION);

        try {
            socket = new Socket(host, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            connectToServerByCallback(userName);
            waitRemoteEvent();
        } catch (IOException e) {
            closeResources();

            throw new ConnectionException("Error in the server connection!", e, SERVER_CONNECTION);
        }
    }

    private void connectToServerByCallback(String userName) {
        localUser = User.of(userName, socket.getLocalAddress().getHostName(), socket.getLocalPort());
        mainModel.connectToServerByCallback(localUser);
    }

    private void waitRemoteEvent() {

        Executors.newSingleThreadExecutor().execute(() -> {

            while(true){

                try {
                    RemoteEvent remoteEvent = (RemoteEvent) objectInputStream.readObject();

                    commandInvoker.execute(remoteEvent);
                } catch (IOException | ClassNotFoundException e) {
                    dialogUtil.showError("Error in the remote command receiving!", RECEIVE_REMOTE_COMMAND, e);
                    mainModel.clear();
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
    public void send(ICommand command) throws ConnectionException {

        if((socket == null) || (!socket.isConnected()))
            throw new ConnectionException("The server is not connected!", SERVER_CONNECTION);

        try {
            RemoteEvent remoteEvent = new RemoteEvent(command);

            objectOutputStream.writeObject(remoteEvent);
            objectOutputStream.reset();
            LogUtil.logInformation(remoteEvent.toString(), REMOTE_EVENT_SENT);
        } catch (IOException e) {
            closeResources();

            throw new ConnectionException("Error in the remote command sending!", e, SEND_REMOTE_COMMAND);
        }
    }

    @Override
    public void setCommandInvoker(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }
}
