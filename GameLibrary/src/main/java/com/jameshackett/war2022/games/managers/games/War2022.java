package com.jameshackett.war2022.games.managers.games;

import com.jameshackett.war2022.core.Logger;
import com.jameshackett.war2022.core.protocol.games.GameResponse;
import com.jameshackett.war2022.games.models.Card;
import com.jameshackett.war2022.games.models.CardDeck;
import com.jameshackett.war2022.games.models.ICardGame;


public class War2022 implements ICardGame {

    Logger logger = new Logger();

    @Override
    public GameResponse play(CardDeck cardDeck) {
        CardDeck player2 = cardDeck.cut(cardDeck.size() / 2);
        CardDeck player1 = cardDeck;
        GameResponse gameResponse = createGameResponse(player1, player2);
        gameResponse.winner = findWinner(player1, player2);
        return gameResponse;
    }

    /**
     *  after each round the winner gets their card added to the deck first.
     *  If there is a draw and they both run out of cards at the same time then p1 will win
     * @return true for player1 winner, false player2
     * @param p1Deck
     * @param p2Deck
     */
    private boolean findWinner(CardDeck p1Deck, CardDeck p2Deck) {
        int counter = 0;
        while (!p1Deck.isEmpty() && !p2Deck.isEmpty()) {
            // get card
            Card p1Card =  p1Deck.dealCard();
            Card p2Card =  p2Deck.dealCard();
            logger.log( p1Card.getCardValue().getValue() + " vs " + p2Card.getCardValue().getValue());
            if (p1Card.compareTo(p2Card) == 1 || (
                    // if they are the same card value doWar to see who wins
                    p1Card.compareTo(p2Card) == 0 && doWar(p1Deck, p2Deck)   )) {
                // p1 wins round
                logger.log( "p1 wins ");
                p1Deck.addToBottom(p1Card);
                p1Deck.addToBottom(p2Card);
            } else {
                // p2 wins round
                logger.log(  "p2 wins ");
                p2Deck.addToBottom(p2Card);
                p2Deck.addToBottom(p1Card);
            }
            counter++;
            logger.log("Round:" + counter + " cards left " + p1Deck.size() + " vs " + p2Deck.size());
        }
        return p2Deck.isEmpty();
    }

    /**
     * Draw 4 cards and compare last card to find winner. If someone does not have enough cards they lose
     * @param p1Deck
     * @param p2Deck
     * @return boolean true if p1 wins, else p2 wins
     */
    private boolean doWar(CardDeck p1Deck, CardDeck p2Deck) {
        if (p2Deck.size() < 4) {
            return true;
        }
        if (p1Deck.size() < 4) {
            return false;
        }
        Card[] p1CardPot = createWarCardPot(p1Deck);
        Card[] p2CardPot = createWarCardPot(p2Deck);

        logger.log(  "WAR>" + p1CardPot[3].getCardValue().getValue() + " vs " + p2CardPot[3].getCardValue().getValue());
        if (p1CardPot[3].compareTo(p2CardPot[3]) == 1 ||
                (p1CardPot[3].compareTo(p2CardPot[3]) == 0 && doWar(p1Deck, p2Deck)))  {
            // p1 wins round
            logger.log(  "WAR>p1 wins");
            addCardPotToDeck(p1CardPot, p1Deck);
            addCardPotToDeck(p2CardPot, p1Deck);
            return true;
        } else {
            // p2 wins
            logger.log(  "WAR>p2 wins");
            addCardPotToDeck(p2CardPot, p2Deck);
            addCardPotToDeck(p1CardPot, p2Deck);
            return false;
        }
    }

    private Card[] createWarCardPot(CardDeck cardDeck) {
        Card[] cardPot = new Card[4];
        for (int i = 0; i < 4; i++) {
            cardPot[i] = cardDeck.dealCard();
        }
        return cardPot;
    }

    private void addCardPotToDeck(Card[] cardPot, CardDeck cardDeck) {
        for (int i = 0; i < 4; i++) {
            cardDeck.addToBottom(cardPot[i] );
        }
    }

    private GameResponse createGameResponse(CardDeck player1, CardDeck player2) {
        return new GameResponse(player1.export(), player2.export());
    }

}
