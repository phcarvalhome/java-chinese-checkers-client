package com.phcarvalho.model.communication.commandtemplate.local;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.ChatModel;
import com.phcarvalho.model.communication.protocol.vo.command.SendMessageCommand;

public class ChatLocalCommandTemplate implements ILocalCommandTemplate {

    private ChatModel chatModel;

    public ChatLocalCommandTemplate() {
        chatModel = DependencyFactory.getSingleton().get(ChatModel.class);
    }

    public void sendMessage(SendMessageCommand sendMessageCommand){
        chatModel.sendMessageByCallback(sendMessageCommand);
    }
}
