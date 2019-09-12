package com.phcarvalho.model.communication.protocol.vo;

import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;

import java.rmi.Remote;

public enum CommandTemplateEnum {

    MAIN(IMainCommandTemplate.class),
    BOARD(IBoardCommandTemplate.class),
    CHAT(IChatCommandTemplate.class),
    CONNECTION(IConnectionCommandTemplate.class);

    private Class<? extends Remote> templateClass;

    CommandTemplateEnum(Class<? extends Remote> templateClass) {
        this.templateClass = templateClass;
    }

    public Class<? extends Remote> getTemplateClass() {
        return templateClass;
    }
}
