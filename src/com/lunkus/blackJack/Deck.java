package com.lunkus.blackJack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Holds decks
 */
public class Deck {

    // Instance Variables
    private ArrayList<Card> cards;

    // Constructor
    public Deck() {
        cards = new ArrayList<>();
    }

    // Instance Methods
    public void createFullDeck() {
        // Generate Cards
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                // Add new card to the deck
                cards.add(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<>();
        // Use Random
        Random random = new Random();
        int deckSize = cards.size();
        for (int i = 0; i < deckSize; i++){
            int randIndex = random.nextInt(cards.size());
            tempDeck.add(cards.get(randIndex));
            cards.remove(randIndex);
        }
        cards = tempDeck;
    }

    public void draw(Deck comingFrom) {
        cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // Getters and Setters
    public void removeCard(int i) {
        cards.remove(i);
    }

    public Card getCard(int i) {
        return cards.get(i);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    // toString method
    @Override
    public String toString() {
        String cardsOutput = "";
        for (Card card : cards) {
            cardsOutput += card.toString() + "\n";
        }
        return cardsOutput;
    }
}
