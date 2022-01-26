package com.jameshackett.war2022.games.models;

public class Card implements Comparable<Card> {

    private final CardSuit cardSuit;
    private final CardValue cardValue;

    public Card(CardSuit cardSuit, CardValue cardValue) {
        this.cardSuit = cardSuit;
        this.cardValue = cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    @Override
    public int compareTo(Card card) {
        if (this.cardValue.getValue() == card.cardValue.getValue()) {
            return 0;
        }
        if (this.cardValue.getValue() > card.cardValue.getValue()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return cardSuit.getName() + " " + cardValue.getName();
    }
}
