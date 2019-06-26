package com.lunkus.blackJack;

/**
 * Card items
 */
public class Card {

    private Suit suit;
    private Value value;

    // Constructor
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    // Public methods
    @Override
    public String toString() {
        return this.suit.toString() + " -> " + this.value.toString();
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
}
