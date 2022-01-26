package com.jameshackett.war2022.games.managers;

import com.jameshackett.war2022.core.protocol.games.GameRequest;
import com.jameshackett.war2022.core.protocol.games.GameResponse;
import com.jameshackett.war2022.games.managers.games.War2022;
import com.jameshackett.war2022.games.models.CardDeck;
import com.jameshackett.war2022.games.models.CardDeckFactory;
import com.jameshackett.war2022.games.models.Leaderboard;
import com.jameshackett.war2022.games.repositories.LeaderboardRepository;

import java.util.List;

public class GameManager {

    private static final String gameName = "War2022";

    private final War2022 war2022;
    private final LeaderboardRepository leaderboardRepository;

    public GameManager(LeaderboardRepository leaderboardRepository) {
        war2022 = new War2022();
        this.leaderboardRepository = leaderboardRepository;
    }

    public GameResponse playWar2022(GameRequest gameRequest) {
        CardDeck cardDeck = CardDeckFactory.createRandomDeck();
        GameResponse response = war2022.play(cardDeck);
        if (response.winner) {
            leaderboardRepository.saveGameWinner(new Leaderboard(gameName, gameRequest.playerName));
        }
        return response;
    }

    public List<Leaderboard> getLeaderboard(String game) {
        return leaderboardRepository.getLeaderboardTopByGameCached(game);
    }

}
