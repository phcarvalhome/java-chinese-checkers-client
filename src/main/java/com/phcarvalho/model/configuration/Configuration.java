package com.phcarvalho.model.configuration;

import com.phcarvalho.model.configuration.entity.Game;
import com.phcarvalho.model.configuration.entity.User;
import com.phcarvalho.model.vo.Player;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private static Configuration singleton;

    public static Configuration getSingleton(){

        if(singleton == null)
            singleton = new Configuration();

        return singleton;
    }

    private Player player;
    private User localUser;
    private User remoteUser;
    private Map<Integer, Game> gameMap;
    private boolean serverConnected;
    private Game gameSelected;

    private Configuration() {
        gameMap = new HashMap<>();
        serverConnected = false;
    }

    public void addGame(Game game){
        gameMap.put(game.getId(), game);
    }

    public void removeGame(Integer gameId){
        gameMap.remove(gameId);
    }

    public Game getGame(Integer gameId){
        return gameMap.get(gameId);
    }

    public void clear(){
        gameMap = new HashMap<>();
        serverConnected = false;
    }

    public void setGameSelectedAndPlayer(Game gameSelected, Player player){
        this.gameSelected = gameSelected;
        this.player = player;
    }

    public void removeGameSelectedAndPlayer(){
        this.gameSelected = gameSelected;
        this.player = player;
    }

    public Player getPlayer() {

        if(player == null)
            throw new RuntimeException("The player field is null!");

        return player;
    }

    public User getLocalUser() {

//        if(localUser == null)
//            throw new RuntimeException("The localUser field is null!");

        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public User getRemoteUser() {

        if(remoteUser == null)
            throw new RuntimeException("The remoteUser field is null!");

        return remoteUser;
    }

    public void setRemoteUser(User remoteUser) {
        this.remoteUser = remoteUser;
    }

    public Map<Integer, Game> getGameMap() {
        return gameMap;
    }

    public boolean isServerConnected() {
        return serverConnected;
    }

    public void setServerConnected(boolean serverConnected) {
        this.serverConnected = serverConnected;
    }

    public Game getGameSelected() {
        return gameSelected;
    }
}
