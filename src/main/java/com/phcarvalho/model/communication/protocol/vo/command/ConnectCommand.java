package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;

import java.util.List;

public class ConnectCommand extends AbstractCommand {

    private List<String> apiNameList;

    public ConnectCommand(List<String> apiNameList) {
        this.apiNameList = apiNameList;
    }

    @Override
    public CommandTypeEnum getType() {
        return CommandTypeEnum.CONNECT;
    }

    public List<String> getApiNameList() {
        return apiNameList;
    }

    @Override
    public String toString() {
        return "ConnectCommand{" +
                "apiNameList=" + apiNameList +
                '}';
    }
}
