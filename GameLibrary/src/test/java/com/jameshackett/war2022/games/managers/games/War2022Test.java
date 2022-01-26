package com.jameshackett.war2022.games.managers.games;

import com.jameshackett.war2022.core.protocol.games.GameResponse;
import com.jameshackett.war2022.games.models.CardDeck;
import com.jameshackett.war2022.games.models.CardDeckFactory;
import org.junit.Test;

public class War2022Test {


    @Test
    public void testWar2020() {
        CardDeck cardDeck = CardDeckFactory.createRandomDeck();
        War2022 war2022 = new War2022();
        GameResponse response = war2022.play(cardDeck);
    }

}
