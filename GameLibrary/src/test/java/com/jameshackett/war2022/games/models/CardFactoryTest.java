package com.jameshackett.war2022.games.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CardFactoryTest {


    @Test
    public void test() {
       CardDeck cardList = CardDeckFactory.createRandomDeck();
       Assert.assertEquals(52, cardList.size());

       //for (int i = 0; i < 40; i++) {
       //    System.out.println(cardList.dealCard().toString());
       //}
    }

}
