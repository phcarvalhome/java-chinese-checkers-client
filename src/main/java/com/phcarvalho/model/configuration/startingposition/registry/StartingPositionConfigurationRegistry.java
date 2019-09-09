package com.phcarvalho.model.configuration.startingposition.registry;

import com.phcarvalho.model.configuration.startingposition.*;
import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;

import java.util.HashMap;
import java.util.Map;

public class StartingPositionConfigurationRegistry {

    private Map<StartingPositionEnum, IStartingPositionConfiguration> startingPositionConfigurationMap;

    public StartingPositionConfigurationRegistry() {
        startingPositionConfigurationMap = new HashMap<>();
        startingPositionConfigurationMap.put(StartingPositionEnum.TOP, new StartingPositionTopConfiguration());
        startingPositionConfigurationMap.put(StartingPositionEnum.TOP_RIGHT, new StartingPositionTopRightConfiguration());
        startingPositionConfigurationMap.put(StartingPositionEnum.BOTTOM_RIGHT, new StartingPositionBottomRightConfiguration());
        startingPositionConfigurationMap.put(StartingPositionEnum.BOTTOM, new StartingPositionBottomConfiguration());
        startingPositionConfigurationMap.put(StartingPositionEnum.BOTTOM_LEFT, new StartingPositionBottomLeftConfiguration());
        startingPositionConfigurationMap.put(StartingPositionEnum.TOP_LEFT, new StartingPositionTopLeftConfiguration());
    }

    public IStartingPositionConfiguration get(StartingPositionEnum startingPositionEnum) {
        return startingPositionConfigurationMap.get(startingPositionEnum);
    }
}
