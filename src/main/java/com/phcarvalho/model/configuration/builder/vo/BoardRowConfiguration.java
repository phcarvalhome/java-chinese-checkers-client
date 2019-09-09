package com.phcarvalho.model.configuration.builder.vo;

import com.phcarvalho.model.vo.Position;

import java.util.ArrayList;
import java.util.List;

public class BoardRowConfiguration {

    private Integer row;
    private boolean enabledPosition;
    private List<Position> positionList;

    public BoardRowConfiguration(Integer row, boolean enabledPosition) {
        this.row = row;
        this.enabledPosition = enabledPosition;
        positionList = new ArrayList<>();
    }

    public boolean containsPosition(Integer column){
        boolean isPresent = positionList.stream()
                .filter(position -> position.getColumn().equals(column))
                .findFirst()
                .isPresent();

        if(enabledPosition)
            return isPresent;
        else
            return !isPresent;
    }

    public Integer getRow() {
        return row;
    }

    public boolean isEnabledPosition() {
        return enabledPosition;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    @Override
    public String toString() {
        return "BoardRowConfiguration{" +
                "row=" + row +
                ", enabledPosition=" + enabledPosition +
                ", positionList=" + positionList +
                '}';
    }
}
