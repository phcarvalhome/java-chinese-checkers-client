package com.phcarvalho.model.communication.strategy.factory;

import com.phcarvalho.model.communication.strategy.ICommandTemplateFactory;
import com.phcarvalho.model.communication.strategy.IConnectionStrategy;

public interface ICommunicationStrategy {

    IConnectionStrategy buildConnectionStrategy();

    ICommandTemplateFactory buildCommandTemplateFactory();
}
