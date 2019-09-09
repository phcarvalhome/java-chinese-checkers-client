package com.phcarvalho.model.configuration.startingposition;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionTopLeftConfiguration implements IStartingPositionConfiguration {

    private List<BoardRowConfiguration> boardRowConfigurationList;

    public StartingPositionTopLeftConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(5).fillInterval(2, 8).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(6).fillInterval(3, 7).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(7).fillInterval(4, 6).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(8).addPosition(5).getInstance());
    }

    @Override
    public List<BoardRowConfiguration> getBoardRowConfigurationList() {
        return boardRowConfigurationList;
    }

    @Override
    public String toString() {
        return "StartingPositionTopLeftConfiguration{" +
                "boardRowConfigurationList=" + boardRowConfigurationList +
                '}';
    }
}
