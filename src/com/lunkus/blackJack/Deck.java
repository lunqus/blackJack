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

    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = cards.size();

        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
    }

    // Returns total value of cards in the deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card card : cards) {
            switch (card.getValue()) {
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: totalValue += 1; break;
            }
        }

        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }

        return  totalValue;
    }

    // Getters and Setters
    public int deckSize() {
        return cards.size();
    }
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
