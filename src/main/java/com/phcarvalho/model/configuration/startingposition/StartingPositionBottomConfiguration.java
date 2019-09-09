package com.phcarvalho.model.configuration.startingposition;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionBottomConfiguration implements IStartingPositionConfiguration {

    private List<BoardRowConfiguration> boardRowConfigurationList;

    public StartingPositionBottomConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(14).addPosition(11).addPosition(13).addPosition(15).addPosition(17).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(15).addPosition(12).addPosition(14).addPosition(16).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(16).addPosition(13).addPosition(15).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(17).addPosition(14).getInstance());
    }

    @Override
    public List<BoardRowConfiguration> getBoardRowConfigurationList() {
        return boardRowConfigurationList;
    }

    @Override
    public String toString() {
        return "StartingPositionBottomConfiguration{" +
                "boardRowConfigurationList=" + boardRowConfigurationList +
                '}';
    }
}
