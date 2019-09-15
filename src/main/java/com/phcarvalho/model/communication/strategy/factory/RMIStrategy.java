package com.phcarvalho.model.communication.strategy.factory;

import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.strategy.rmi.RMICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.rmi.RMIConnectionStrategy;

public class RMIStrategy implements ICommunicationStrategy {

    @Override
    public IConnectionStrategy buildConnectionStrategy() {
        return new RMIConnectionStrategy();
    }

    @Override
    public ICommandTemplateFactory buildCommandTemplateFactory() {
        return new RMICommandTemplateFactory();
    }
}
