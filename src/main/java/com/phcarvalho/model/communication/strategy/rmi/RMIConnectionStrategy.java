package com.phcarvalho.model.communication.strategy.rmi;

import com.phcarvalho.model.MainModel;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.BoardLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ChatLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ConnectionLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.MainLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.socket.CommandInvoker;
import com.phcarvalho.model.communication.protocol.vo.command.ConnectCommand;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RMIConnectionStrategy implements IConnectionStrategy {

    @Override
    public void connectToServer(String host, Integer port, String userName) throws RemoteException {
        register(host, port, userName);
    }

    private void register(String host, Integer port, String userName) throws RemoteException {
        String baseAPIName = "rmi://" + host + ":" + port + "/";
        String baseAPINameWithName = baseAPIName + userName + "/";
        BoardLocalCommandTemplate boardLocalCommandTemplate = new BoardLocalCommandTemplate();
        String boardLocalAPIName = baseAPINameWithName + boardLocalCommandTemplate.getClass().getSimpleName();
        ChatLocalCommandTemplate chatLocalCommandTemplate = new ChatLocalCommandTemplate();
        String chatLocalAPIName = baseAPINameWithName + chatLocalCommandTemplate.getClass().getSimpleName();
        MainLocalCommandTemplate mainLocalCommandTemplate = new MainLocalCommandTemplate();
        String mainLocalAPIName = baseAPINameWithName + mainLocalCommandTemplate.getClass().getSimpleName();
        ConnectionLocalCommandTemplate connectionLocalCommandTemplate = new ConnectionLocalCommandTemplate();
        String connectionLocalAPIName = baseAPINameWithName + connectionLocalCommandTemplate.getClass().getSimpleName();

        try {
//            LocateRegistry.getRegistry(9999);

            Naming.rebind(boardLocalAPIName, boardLocalCommandTemplate);
            Naming.rebind(chatLocalAPIName, chatLocalCommandTemplate);
            Naming.rebind(mainLocalAPIName, mainLocalCommandTemplate);
            Naming.rebind(connectionLocalAPIName, mainLocalCommandTemplate);

            List<String> apiNameList = new ArrayList<>();

            apiNameList.add(boardLocalAPIName);
            apiNameList.add(chatLocalAPIName);
            apiNameList.add(mainLocalAPIName);
            apiNameList.add(connectionLocalAPIName);
            connectToServer(baseAPIName, apiNameList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void connectToServer(String baseAPIName, List<String> apiNameList) {

        try {
            String connectionLocalAPIName = baseAPIName + "/ConnectionLocalCommandTemplate";
            IConnectionCommandTemplate connectionCommandTemplate = (IConnectionCommandTemplate) Naming
                    .lookup(connectionLocalAPIName);
            ConnectCommand connectCommand = new ConnectCommand(apiNameList);

            connectionCommandTemplate.connect(connectCommand);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(ICommand command) throws RemoteException {

    }

    @Override
    public void setCommandInvoker(CommandInvoker commandInvoker) {

    }

    @Override
    public void setMainModel(MainModel mainModel) {

    }
}
