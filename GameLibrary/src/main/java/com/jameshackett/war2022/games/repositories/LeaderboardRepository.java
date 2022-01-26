package com.jameshackett.war2022.games.repositories;

import com.jameshackett.core.DatabaseManager;
import com.jameshackett.war2022.core.exceptions.DataAccessException;
import com.jameshackett.war2022.games.models.Leaderboard;
import org.apache.tomcat.jdbc.pool.DataSource;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LeaderboardRepository {


    private static final int CACHE_TTL = 100;
    private LoadingCache<String, List<Leaderboard>> leaderboardCache = CacheBuilder.newBuilder()
            .expireAfterWrite(CACHE_TTL, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<String , List<Leaderboard>>() {
                        @Override
                        public List<Leaderboard> load(String gameName) {
                            return getLeaderboardTopByGame(gameName);
                        }
                    }
            );

    private DataSource dataSource;

    public LeaderboardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns cached version of top 100 players. if it isn't found it returns database results
     */
    public List<Leaderboard> getLeaderboardTopByGameCached(String gameName) {
        try {
            return leaderboardCache.get(gameName);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves game win for player in the database. adds row if it doesn't exist otherwise increments a counter
     *
     * @param leaderboard
     */
    public void saveGameWinner(Leaderboard leaderboard) {
        // Normally we have a database abstraction layer to avoid writing SQL directly when possible
        // I didn't want to use boilerplate db access layer and thought it better to show the SQL for this project
        PreparedStatement stmt = null;
        DatabaseManager db = null;
        try {
            db = DatabaseManager.getDatabaseManager(dataSource);
            String sql = String.format("INSERT INTO `leaderboard` (`game`, `player`, `wins`, `updated`) " +
                    " VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `wins` = `wins` + 1");
            stmt = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,leaderboard.game);
            stmt.setString(2,leaderboard.player);
            stmt.setInt(3,leaderboard.wins);
            stmt.setTimestamp(4,Timestamp.valueOf(leaderboard.updated));
            stmt.execute();
            leaderboardCache.refresh(leaderboard.game);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            db.close(stmt, null);
        }
    }


    /**
     * returns the top 100 players for a game
     * @param gameName
     * @return
     */
    public List<Leaderboard> getLeaderboardTopByGame(String gameName) {
        PreparedStatement stmt = null;
        ResultSet res = null;
        DatabaseManager db = null;
        List<Leaderboard> leaderboardList = new ArrayList();
        try {
            db = DatabaseManager.getDatabaseManager(dataSource);
            String sql = "SELECT * FROM leaderboard WHERE game = ? order by wins limit 100";
            stmt = db.getConnection().prepareStatement(sql);
            stmt.setString(1, gameName);
            res = stmt.executeQuery();
            while (res.next()) {
                leaderboardList.add (new Leaderboard(res));
            }
            return leaderboardList;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            db.close(stmt, res);
        }
    }


}
