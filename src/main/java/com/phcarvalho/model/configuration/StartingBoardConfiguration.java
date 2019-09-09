package com.phcarvalho.model.configuration;

import com.phcarvalho.model.configuration.builder.BoardRowConfigurationBuilder;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;

import java.util.ArrayList;
import java.util.List;

public class StartingBoardConfiguration {

    public static final Integer ROW_INITIAL = 1;
    public static final Integer ROW_FINAL = 17;
    public static final Integer COLUMN_INITIAL = 1;
    public static final Integer COLUMN_FINAL = 27;

    private static StartingBoardConfiguration singleton;

    public static StartingBoardConfiguration getSingleton(){

        if(singleton == null)
            singleton = new StartingBoardConfiguration();

        return singleton;
    }

    private List<BoardRowConfiguration> boardRowConfigurationList;

    private StartingBoardConfiguration() {
        buildBoardRowConfigurationList();
    }

    private void buildBoardRowConfigurationList() {
        boardRowConfigurationList = new ArrayList();
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(1).addPosition(14).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(2).addPosition(13).addPosition(15).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(3).addPosition(12).addPosition(14).addPosition(16).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(4).addPosition(11).addPosition(13).addPosition(15).addPosition(17).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(5).fillInterval(1, 27).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(6).fillInterval(2, 26).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(7).fillInterval(3, 25).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(8).fillInterval(4, 24).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(9).fillInterval(5, 23).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(10).fillInterval(4, 24).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(11).fillInterval(3, 25).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(12).fillInterval(2, 26).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofDisabledPosition(13).fillInterval(1, 27).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(14).addPosition(11).addPosition(13).addPosition(15).addPosition(17).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(15).addPosition(12).addPosition(14).addPosition(16).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(16).addPosition(13).addPosition(15).getInstance());
        boardRowConfigurationList.add(BoardRowConfigurationBuilder.ofEnabledPosition(17).addPosition(14).getInstance());
    }

    public BoardRowConfiguration getBoardRowConfiguration(Integer row) {
        return boardRowConfigurationList.get(row - 1);
    }
}
