package com.phcarvalho.model.communication.strategy.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.protocol.vo.RemoteEvent;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.exception.ConnectionException;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.util.DialogUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;

public class SocketConnectionStrategy implements IConnectionStrategy {

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

    private DialogUtil dialogUtil;

    public SocketConnectionStrategy() {
        commandInvoker = DependencyFactory.getSingleton().get(CommandInvoker.class); //TODO teste da injecao direta...
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
    }

    @Override
    public void connectToServer(User remoteUser) throws RemoteException {

        if((socket != null) && (socket.isConnected()))
            throw new ConnectionException("The server is already connected!", SERVER_CONNECTION);

        String host = remoteUser.getHost();
        Integer port = remoteUser.getPort();

        try {
            socket = new Socket(host, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
//            localUser.setPort(socket.getLocalPort());
            waitRemoteEvent();
        } catch (IOException e) {
            closeResources();

            throw new ConnectionException("Error in the server connection!", e, SERVER_CONNECTION);
        }
    }

    private void waitRemoteEvent() {

        Executors.newSingleThreadExecutor().execute(() -> {

            while(true){

                try {
                    RemoteEvent remoteEvent = (RemoteEvent) objectInputStream.readObject();

                    commandInvoker.execute(remoteEvent);
                } catch (IOException | ClassNotFoundException e) {
                    dialogUtil.showError("Error in the remote command receiving!", RECEIVE_REMOTE_COMMAND, e);
//                    mainModel.clear(); //TODO dar o clear em um erro quando tentar se conectar, mas em um canto generico...
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

    public void send(ICommand command) throws RemoteException {

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

    //TODO testar se ainda precisa setar manual no dependecy factory
//    public void setCommandInvoker(CommandInvoker commandInvoker) {
//        this.commandInvoker = commandInvoker;
//    }
}
