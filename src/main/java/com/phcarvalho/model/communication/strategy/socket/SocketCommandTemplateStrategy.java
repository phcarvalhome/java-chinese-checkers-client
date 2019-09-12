package com.phcarvalho.model.communication.strategy.socket;

import com.phcarvalho.model.communication.commandtemplate.remote.socket.BoardRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.ChatRemoteCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.remote.socket.MainRemoteCommandTemplate;
import com.phcarvalho.model.communication.strategy.ICommandTemplateStrategy;
import com.phcarvalho.model.communication.strategy.vo.CommandTemplateSet;

public class SocketCommandTemplateStrategy implements ICommandTemplateStrategy {

    @Override
    public CommandTemplateSet getCommandTemplateSet() {
        BoardRemoteCommandTemplate boardRemoteCommandTemplate = new BoardRemoteCommandTemplate();
        ChatRemoteCommandTemplate chatRemoteCommandTemplate = new ChatRemoteCommandTemplate();
        MainRemoteCommandTemplate mainRemoteCommandTemplate = new MainRemoteCommandTemplate();

        return new CommandTemplateSet(boardRemoteCommandTemplate, chatRemoteCommandTemplate, mainRemoteCommandTemplate);
    }
}
