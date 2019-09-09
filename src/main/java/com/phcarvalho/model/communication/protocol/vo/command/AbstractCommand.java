package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.configuration.entity.User;

public abstract class AbstractCommand implements ICommand {

    private User sourceUser;

    public AbstractCommand() {
        sourceUser = Configuration.getSingleton().getLocalUser();
    }

    @Override
    public User getSourceUser() {
        return sourceUser;
    }
}
