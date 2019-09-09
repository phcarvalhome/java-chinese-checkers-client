package com.phcarvalho.model.configuration.entity;

import com.phcarvalho.model.configuration.startingposition.vo.StartingPositionEnum;
import com.phcarvalho.model.vo.PieceColorEnum;
import com.phcarvalho.model.vo.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game implements Serializable {

    private Integer id;
    private String title;
    private Player ownerPlayer;
    private List<Player> playerList;
    private Player turnPlayer;
    private boolean isGameStarted;
    private List<Player> readyPlayerList;
    private int playersQuantity;

    public Game(String title, Player ownerPlayer, int playersQuantity) {
        this.title = title;
        this.ownerPlayer = ownerPlayer;
        playerList = new ArrayList<>();
        playerList.add(ownerPlayer);
        turnPlayer = ownerPlayer;
        isGameStarted = false;
        readyPlayerList = new ArrayList<>();
        this.playersQuantity = playersQuantity;
    }

    public void addPlayer(Player player){
        playerList.add(player);
    }

    public List<Player> getPlayerList(Player player) {
        return playerList.stream()
                .filter(playerElement -> !playerElement.equals(player))
                .collect(Collectors.toList());
    }

    public List<User> getRemoteUserList() {
        return playerList.stream()
                .map(playerElement -> playerElement.getUser())
                .collect(Collectors.toList());
    }

    public List<User> getRemoteUserList(User user) {
        return playerList.stream()
                .map(playerElement -> playerElement.getUser())
                .filter(sourceElement -> !sourceElement.equals(user))
                .collect(Collectors.toList());
    }

    public String[] getFreeStartingPositionList() {
        return Arrays.stream(StartingPositionEnum.values())
                .filter(startingPositionEnum -> !playerList.stream()
                        .map(player -> player.getStartingPosition())
                        .collect(Collectors.toList())
                        .contains(startingPositionEnum))
                .map(startingPosition -> startingPosition.getValue())
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }

    public String[] getFreePieceColorList() {
        return Arrays.stream(PieceColorEnum.values())
                .filter(pieceColor -> !playerList.stream()
                        .map(player -> player.getColor())
                        .collect(Collectors.toList())
                        .contains(pieceColor))
                .map(startingPosition -> startingPosition.getValue())
                .collect(Collectors.toList())
                .toArray(String[]::new);
    }

    public void addReadyPlayer(Player player){
        readyPlayerList.add(player);

        if(readyPlayerList.size() == playersQuantity)
            isGameStarted = true;
    }

    public boolean isFull() {
        return playerList.size() == playersQuantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Player getOwnerPlayer() {
        return ownerPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player getTurnPlayer() {
        return turnPlayer;
    }

    public void setTurnPlayer(Player turnPlayer) {
        this.turnPlayer = turnPlayer;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public List<Player> getReadyPlayerList() {
        return readyPlayerList;
    }

    public int getPlayersQuantity() {
        return playersQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title + " [" + id + "]";
    }
}
