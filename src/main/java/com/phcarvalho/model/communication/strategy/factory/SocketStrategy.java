package com.phcarvalho.model.communication.strategy.factory;

import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.strategy.socket.SocketCommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.socket.SocketConnectionStrategy;

public class SocketStrategy implements ICommunicationStrategy {

    @Override
    public IConnectionStrategy buildConnectionStrategy() {
        return new SocketConnectionStrategy();
    }

    @Override
    public ICommandTemplateFactory buildCommandTemplateFactory() {
        return new SocketCommandTemplateFactory();
    }
}
