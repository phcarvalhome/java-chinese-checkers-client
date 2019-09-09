package com.phcarvalho.model.vo;

import java.io.Serializable;

public class Piece implements Serializable {

    private Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "player=" + player +
                '}';
    }
}
