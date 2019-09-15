package com.phcarvalho.model.communication.strategy;

import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IConnectionCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;

public abstract class AbstractCommandTemplateFactory implements ICommandTemplateFactory {

    private IConnectionCommandTemplate connection;
    private IBoardCommandTemplate board;
    private IChatCommandTemplate chat;
    private IMainCommandTemplate main;

    @Override
    public IConnectionCommandTemplate getConnection() {

        if(connection == null)
            connection = buildConnection();

        return connection;
    }

    @Override
    public IBoardCommandTemplate getBoard() {

        if(board == null)
            board = buildBoard();

        return board;
    }

    @Override
    public IChatCommandTemplate getChat() {

        if(chat == null)
            chat = buildChat();

        return chat;
    }

    @Override
    public IMainCommandTemplate getMain() {

        if(main == null)
            main = buildMain();

        return main;
    }

    protected abstract IConnectionCommandTemplate buildConnection();

    protected abstract IBoardCommandTemplate buildBoard();

    protected abstract IChatCommandTemplate buildChat();

    protected abstract IMainCommandTemplate buildMain();
}
