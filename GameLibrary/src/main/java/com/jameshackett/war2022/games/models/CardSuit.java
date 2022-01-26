package com.jameshackett.war2022.games.models;

public class CardSuit {

    public enum SUITS  {
        SPADES,
        HEARTS,
        DIAMONDS,
        CLUBS
    }

    private final String name;

    public CardSuit(SUITS suit) {
        this.name = suit.name();
    }

    public String getName() {
        return name;
    }

}
