package com.jameshackett.war2022.games.models;

import java.util.Objects;

public class CardValue {

    public enum VALUES {
        TWO(2,"two"),
        THREE(3,"three"),
        FOUR(4,"four"),
        FIVE(5,"five"),
        SIX(6,"six"),
        SEVEN(7,"seven"),
        EIGHT(8,"eight"),
        NINE(9,"nine"),
        TEN(10,"ten"),
        JACK(11,"jack"),
        QUEEN(12,"queen"),
        KING(13,"king"),
        ACE(14,"ace");

        VALUES(int value, String name) {
            this.value = value;
            this.name = name;
        }
        private final int value;
        private final String name;
    }

    private final String name;
    private final int value;

    public CardValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public CardValue(VALUES v) {
        this.name = v.name;
        this.value = v.value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardValue cardValue = (CardValue) o;
        return value == cardValue.value && Objects.equals(name, cardValue.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
