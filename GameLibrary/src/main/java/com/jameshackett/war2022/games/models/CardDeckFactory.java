package com.jameshackett.war2022.games.models;

import java.util.*;

public class CardDeckFactory {

    public static CardDeck createRandomDeck() {
        List<Card> cards = new ArrayList<>();
        for (CardSuit.SUITS suit : CardSuit.SUITS.values()) {
           for (CardValue.VALUES value : CardValue.VALUES.values())  {
               cards.add(new Card(new CardSuit(suit), new CardValue(value)));
           }
        }
        Collections.shuffle(cards);
        return new CardDeck(new ArrayDeque(cards));
    }


}
