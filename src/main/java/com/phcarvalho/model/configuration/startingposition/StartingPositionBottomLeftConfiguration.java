package com.phcarvalho.model.configuration.startingposition;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionBottomLeftConfiguration implements IStartingPositionConfiguration {

    private List<BoardRowConfiguration> boardRowConfigurationList;

    public StartingPositionBottomLeftConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(10).addPosition(5).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(11).fillInterval(4, 6).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(12).fillInterval(3, 7).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(13).fillInterval(2, 8).getInstance());
    }

    @Override
    public List<BoardRowConfiguration> getBoardRowConfigurationList() {
        return boardRowConfigurationList;
    }

    @Override
    public String toString() {
        return "StartingPositionBottomLeftConfiguration{" +
                "boardRowConfigurationList=" + boardRowConfigurationList +
                '}';
    }
}
