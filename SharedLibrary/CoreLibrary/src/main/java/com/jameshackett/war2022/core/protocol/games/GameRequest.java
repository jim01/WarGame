package com.jameshackett.war2022.core.protocol.games;

public class GameRequest {

    public String playerName;

    public GameRequest() {
    }

    public GameRequest setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }
}
