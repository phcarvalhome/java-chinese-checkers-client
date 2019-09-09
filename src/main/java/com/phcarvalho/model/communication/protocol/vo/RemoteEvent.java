package com.phcarvalho.model.communication.protocol.vo;

import com.phcarvalho.model.communication.protocol.vo.command.ICommand;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RemoteEvent implements Serializable {

    private ICommand command;
    private Class<?> commandClass;
    private LocalDateTime creationDateTime;

    public RemoteEvent(ICommand command) {
        this.command = command;
        commandClass = command.getClass();
        creationDateTime = LocalDateTime.now();
    }

    public ICommand getCommand() {
        return command;
    }

    public Class<?> getCommandClass() {
        return commandClass;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    @Override
    public String toString() {
        return "RemoteEvent{" +
                "command=" + command +
                ", commandClass=" + commandClass +
                ", creationDateTime=" + creationDateTime +
                '}';
    }
}
