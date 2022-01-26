package com.jameshackett.war2022.games.repositories;

import com.jameshackett.war2022.games.TestManager;
import com.jameshackett.war2022.games.models.Leaderboard;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class LeaderboardRepositoryTest {

    private final String testPlayer;
    private final String testGame;
    private final TestManager testManager;

    public LeaderboardRepositoryTest() {
        testPlayer = "testplayer_" + UUID.randomUUID().toString();
        testGame = "testgame_" + UUID.randomUUID().toString();
        testManager = new TestManager();
    }

    @BeforeClass
    public static void before() {

    }

    @AfterClass
    public static void after() {
        // clear out test data
    }

    @Test
    public void testSavingLeaderboard() {
        LeaderboardRepository leaderboardRepository = new LeaderboardRepository(testManager.dataSource);
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setGame(testGame);
        leaderboard.setPlayer(testPlayer);
        // save new leaderboard
        leaderboardRepository.saveGameWinner(leaderboard);

        // fetch list
        List<Leaderboard> leaderboardList =  leaderboardRepository.getLeaderboardTopByGameCached(testGame);
        Assert.assertEquals(1, leaderboardList.size());
        Assert.assertEquals(1, leaderboardList.get(0).wins.intValue());

        // increment wins
        leaderboardRepository.saveGameWinner(leaderboard);

        // fetch new list and verify wins increased
        leaderboardList =  leaderboardRepository.getLeaderboardTopByGameCached(testGame);
        Assert.assertEquals(1, leaderboardList.size());
        Assert.assertEquals(2, leaderboardList.get(0).wins.intValue());
    }


}