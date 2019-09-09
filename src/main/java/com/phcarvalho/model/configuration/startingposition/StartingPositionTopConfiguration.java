package com.phcarvalho.model.configuration.startingposition;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingPositionTopConfiguration implements IStartingPositionConfiguration {

    private List<BoardRowConfiguration> boardRowConfigurationList;

    public StartingPositionTopConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(1).addPosition(14).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(2).addPosition(13).addPosition(15).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(3).addPosition(12).addPosition(14).addPosition(16).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(4).addPosition(11).addPosition(13).addPosition(15).addPosition(17).getInstance());
    }

    @Override
    public List<BoardRowConfiguration> getBoardRowConfigurationList() {
        return boardRowConfigurationList;
    }

    @Override
    public String toString() {
        return "StartingPositionTopConfiguration{" +
                "boardRowConfigurationList=" + boardRowConfigurationList +
                '}';
    }
}
