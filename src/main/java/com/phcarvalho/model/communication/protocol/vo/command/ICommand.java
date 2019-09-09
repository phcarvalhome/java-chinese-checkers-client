package com.phcarvalho.model.communication.protocol.vo.command;

import com.phcarvalho.model.communication.protocol.vo.CommandTypeEnum;
import com.phcarvalho.model.configuration.entity.User;

import java.io.Serializable;

public interface ICommand extends Serializable {

    User getSourceUser();

    CommandTypeEnum getType();
}
