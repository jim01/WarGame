package com.jameshackett.war2022.core.protocol.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameResponse implements Serializable {


    public GameResponse() {
    }

    public GameResponse(List<String> yourCards, List<String> houseCards) {
        this.yourCards = yourCards;
        this.houseCards = houseCards;
    }

    public boolean winner;
    public List<String> yourCards;
    public List<String> houseCards;


}
