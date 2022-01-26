package com.jameshackett.war2022.games.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Leaderboard {

    public Integer id;
    public String game;
    public String player;
    public Integer wins;
    public LocalDateTime updated;

    public Leaderboard() {
        wins = 1;
        updated = LocalDateTime.now();
    }

    public Leaderboard(String game, String player) {
        this.game = game;
        this.player = cleanData(player);
        wins = 1;
        updated = LocalDateTime.now();
    }

    public Leaderboard(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        game = resultSet.getString(2);
        player = resultSet.getString(3);
        wins = resultSet.getInt(4);
        updated = resultSet.getTimestamp(5).toLocalDateTime();
    }

    public Leaderboard setGame(String game) {
        this.game = game;
        return this;
    }

    public Leaderboard setPlayer(String player) {
        this.player = player;
        return this;
    }

    private String cleanData(String str) {
       return str.replaceAll("/[^a-zA-Z0-9 ]/", "");
    }

}
