package com.phcarvalho.model.configuration.builder;

import com.phcarvalho.model.configuration.StartingBoardConfiguration;
import com.phcarvalho.model.configuration.builder.vo.BoardRowConfiguration;
import com.phcarvalho.model.vo.Position;

public class BoardRowConfigurationBuilder {

    private BoardRowConfiguration instance;

    private BoardRowConfigurationBuilder(BoardRowConfiguration instance) {
        this.instance = instance;
    }

    public static BoardRowConfigurationBuilder ofEnabledPosition(Integer row){
        BoardRowConfiguration boardRowConfiguration = new BoardRowConfiguration(row, true);

        return new BoardRowConfigurationBuilder(boardRowConfiguration);
    }

    public static BoardRowConfigurationBuilder ofDisabledPosition(Integer row){
        BoardRowConfiguration boardRowConfiguration = new BoardRowConfiguration(row, false);

        return new BoardRowConfigurationBuilder(boardRowConfiguration);
    }

    public BoardRowConfigurationBuilder addPosition(Integer column){
        instance.getPositionList().add(new Position(instance.getRow(), column));

        return this;
    }

    public BoardRowConfigurationBuilder fillInterval(Integer column1, Integer column2){

        if(instance.isEnabledPosition())
            fillIntervalForEnabled(column1, column2);
        else
            fillIntervalForDisabled(column1, column2);

        return this;
    }

    private void fillIntervalForEnabled(Integer column1, Integer column2) {

        while(column1 <= column2){
            instance.getPositionList().add(new Position(instance.getRow(), column1));
            column1 += 2;
        }
    }

    private void fillIntervalForDisabled(Integer column1, Integer column2) {

        for(Integer column = StartingBoardConfiguration.COLUMN_INITIAL; column <= column1; column++)
            instance.getPositionList().add(new Position(instance.getRow(), column));

        while(column1 <= column2){
            instance.getPositionList().add(new Position(instance.getRow(), column1));
            column1 += 2;
        }

        for(Integer column = column2; column <= StartingBoardConfiguration.COLUMN_FINAL; column++)
            instance.getPositionList().add(new Position(instance.getRow(), column));
    }

    public BoardRowConfiguration getInstance(){
        return instance;
    }
}
