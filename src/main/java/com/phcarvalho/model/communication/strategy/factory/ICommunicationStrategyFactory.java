package com.phcarvalho.model.communication.strategy.factory;

import com.phcarvalho.model.communication.strategy.ICommandTemplateStrategy;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;

public interface ICommunicationStrategyFactory {

    IConnectionStrategy buildConnectionStrategy();

    ICommandTemplateStrategy buildCommandTemplateStrategy();
}
