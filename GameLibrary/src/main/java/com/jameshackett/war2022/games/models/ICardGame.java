package com.jameshackett.war2022.games.models;

import com.jameshackett.war2022.core.protocol.games.GameResponse;

public interface ICardGame  {

    GameResponse play(CardDeck cardDeck);

}
