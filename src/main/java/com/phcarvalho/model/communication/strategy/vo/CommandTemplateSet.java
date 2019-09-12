package com.phcarvalho.model.communication.strategy.vo;

import com.phcarvalho.model.communication.commandtemplate.IBoardCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IChatCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.IMainCommandTemplate;

public class CommandTemplateSet {

    private IBoardCommandTemplate boardCommandTemplate;
    private IChatCommandTemplate chatCommandTemplate;
    private IMainCommandTemplate mainCommandTemplate;

    public CommandTemplateSet(IBoardCommandTemplate boardCommandTemplate,
                              IChatCommandTemplate chatCommandTemplate,
                              IMainCommandTemplate mainCommandTemplate) {
        this.boardCommandTemplate = boardCommandTemplate;
        this.chatCommandTemplate = chatCommandTemplate;
        this.mainCommandTemplate = mainCommandTemplate;
    }

    public IBoardCommandTemplate getBoardCommandTemplate() {
        return boardCommandTemplate;
    }

    public IChatCommandTemplate getChatCommandTemplate() {
        return chatCommandTemplate;
    }

    public IMainCommandTemplate getMainCommandTemplate() {
        return mainCommandTemplate;
    }
}
