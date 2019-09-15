package com.phcarvalho.model.communication.strategy.rmi;

import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.CommandTemplateEnum;
import com.phcarvalho.model.communication.strategy.AbstractCommandTemplateFactory;
import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RMICommandTemplateFactory extends AbstractCommandTemplateFactory {

    @Override
    public IConnectionCommandTemplate buildConnection() {
        return (IConnectionCommandTemplate) lookup(CommandTemplateEnum.CONNECTION);
    }

    private Remote lookup(CommandTemplateEnum commandTemplate){
        User remoteUser = Configuration.getSingleton().getRemoteUser();
        String apiName = "rmi://" + remoteUser.getHost() + ":" + remoteUser.getPort() + "/" +
                "SERVER/" + commandTemplate.name();

        try {
            return Naming.lookup(apiName);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            //todo handler it
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    @Override
    public IBoardCommandTemplate buildBoard() {
        return (IBoardCommandTemplate) lookup(CommandTemplateEnum.BOARD);
    }

    @Override
    public IChatCommandTemplate buildChat() {
        return (IChatCommandTemplate) lookup(CommandTemplateEnum.CHAT);
    }

    @Override
    public IMainCommandTemplate buildMain() {
        return (IMainCommandTemplate) lookup(CommandTemplateEnum.MAIN);
    }
}
