package com.phcarvalho.model.communication.strategy.factory;

import com.phcarvalho.model.communication.strategy.ICommandTemplateStrategy;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;
import com.phcarvalho.model.communication.strategy.rmi.RMICommandTemplateStrategy;
import com.phcarvalho.model.communication.strategy.rmi.RMIConnectionStrategy;

public class RMIStrategyFactory implements ICommunicationStrategyFactory {

    @Override
    public IConnectionStrategy buildConnectionStrategy() {
        return new RMIConnectionStrategy();
    }

    @Override
    public ICommandTemplateStrategy buildCommandTemplateStrategy() {
        return new RMICommandTemplateStrategy();
    }
}
