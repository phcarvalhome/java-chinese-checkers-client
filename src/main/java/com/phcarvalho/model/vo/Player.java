package com.phcarvalho.model.vo;

import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {

    private PieceColorEnum color;
    private StartingPositionEnum startingPosition;
    private User user;

    public Player(PieceColorEnum color, StartingPositionEnum startingPosition, User user) {
        this.color = color;
        this.startingPosition = startingPosition;
        this.user = user;
    }

    public PieceColorEnum getColor() {
        return color;
    }

    public StartingPositionEnum getStartingPosition() {
        return startingPosition;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(user, player.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return "[" + color.getValue() + "][" + startingPosition.getValue() + "] " + user.getName();
    }
}
