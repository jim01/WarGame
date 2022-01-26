package com.jameshackett.war2022.games.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private Deque<Card> cards;

    public CardDeck(Deque cards) {
        this.cards = cards;
    }

    public void addToBottom(Card card) {
        cards.offer(card);
    }

    /**
     * removes top card from deck and returns it.
     *
     * @return
     */
    public Card dealCard() {
        return cards.poll();
    }

    /**
     * Cuts the deck at the specified index and returns a new card deck with the cards removed from this deck.
     *
     * @param index
     * @return
     */
    public CardDeck cut(int index) {
        if (index >= cards.size()) {
            throw new CardGameException("Index(" + index + ") out of range for deck(" + cards.size() + ")");
        }
        Deque<Card> split = new ArrayDeque();
        for (int i = 0; i < index; i++) {
            split.add(cards.poll());
        }
        return new CardDeck(split);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public List<String> export() {
        List<String> list = new ArrayList<>();
        cards.forEach(card -> {
            list.add(card.toString());
        });
        return list;
    }

}
