package com.phcarvalho.model.configuration.startingposition;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionTopRightConfiguration implements IStartingPositionConfiguration {

    private List<BoardRowConfiguration> boardRowConfigurationList;

    public StartingPositionTopRightConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(5).fillInterval(20, 26).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(6).fillInterval(21, 25).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(7).fillInterval(22, 24).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(8).addPosition(23).getInstance());
    }

    @Override
    public List<BoardRowConfiguration> getBoardRowConfigurationList() {
        return boardRowConfigurationList;
    }

    @Override
    public String toString() {
        return "StartingPositionTopRightConfiguration{" +
                "boardRowConfigurationList=" + boardRowConfigurationList +
                '}';
    }
}
