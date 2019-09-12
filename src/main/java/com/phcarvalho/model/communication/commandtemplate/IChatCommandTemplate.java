package com.phcarvalho.model.communication.commandtemplate;

import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChatCommandTemplate extends Remote {

    void sendMessage(SendMessageCommand sendMessageCommand) throws RemoteException;
}
