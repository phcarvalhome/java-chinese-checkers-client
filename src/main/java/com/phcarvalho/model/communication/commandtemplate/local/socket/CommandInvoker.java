package com.phcarvalho.model.communication.commandtemplate.local.socket;

import com.phcarvalho.dependencyfactory.DependencyFactory;
import com.phcarvalho.model.communication.commandtemplate.local.BoardLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ChatLocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.ILocalCommandTemplate;
import com.phcarvalho.model.communication.commandtemplate.local.MainLocalCommandTemplate;
import com.phcarvalho.model.communication.protocol.vo.CommandTemplateEnum;
import com.phcarvalho.model.communication.protocol.vo.RemoteEvent;
import com.phcarvalho.model.communication.protocol.vo.command.ICommand;
import com.phcarvalho.model.util.LogUtil;
import com.phcarvalho.view.util.DialogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private static final String COMMAND_INVOCATION = "Command Invocation";
    private static final String REMOTE_EVENT_EXECUTED = "Remote Event Executed";
    private static final String COMMAND_INVOKED = "Command Invoked";

    private DialogUtil dialogUtil;
    private Map<CommandTemplateEnum, ILocalCommandTemplate> commandTemplateMap;

    public CommandInvoker() {
        dialogUtil = DependencyFactory.getSingleton().get(DialogUtil.class);
    }

    public void buildCommandObserverMap() {
        commandTemplateMap = new HashMap<>();
        commandTemplateMap.put(CommandTemplateEnum.MAIN, new MainLocalCommandTemplate());
        commandTemplateMap.put(CommandTemplateEnum.BOARD, new BoardLocalCommandTemplate());
        commandTemplateMap.put(CommandTemplateEnum.CHAT, new ChatLocalCommandTemplate());
    }

    public void execute(RemoteEvent remoteEvent){
        LogUtil.logInformation(remoteEvent.toString(), REMOTE_EVENT_EXECUTED);

        CommandTemplateEnum commandContext = remoteEvent.getCommand().getType().getTemplate();
        ILocalCommandTemplate commandObserver = commandTemplateMap.get(commandContext);

        try {
            Method commandMethod = commandObserver.getClass()
                    .getDeclaredMethod(remoteEvent.getCommand().getType().getValue(), remoteEvent.getCommandClass());
            ICommand command = remoteEvent.getCommand();

            commandMethod.invoke(commandObserver, command);
            LogUtil.logInformation(command.toString(), COMMAND_INVOKED);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            dialogUtil.showError("Error in the command invocation!", COMMAND_INVOCATION, e);
        } catch (InvocationTargetException e) {
            dialogUtil.showError("Error in the command invocation!", COMMAND_INVOCATION, (Exception) e.getTargetException());
        }
    }
}
