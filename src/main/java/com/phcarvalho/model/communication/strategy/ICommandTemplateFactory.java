package com.phcarvalho.model.communication.strategy;

import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;

public interface ICommandTemplateFactory {

    IConnectionCommandTemplate getConnection();

    IBoardCommandTemplate getBoard();

    IChatCommandTemplate getChat();

    IMainCommandTemplate getMain();
}
