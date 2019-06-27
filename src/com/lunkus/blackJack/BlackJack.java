package com.lunkus.blackJack;

import java.util.Scanner;

/**
 * Main method
 */
public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to BlacJack");

        // Create playing deck
        Deck deck = new Deck();
        deck.createFullDeck();
        deck.shuffle();

        // Create a deck for a player
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game Loop
        while (playerMoney > 0) {
            // Play on!
            // Takes the players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have. Please leave.");
                break;
            }

            boolean endRound = false;

            // Start dealing
            // Player gets two cards
            playerDeck.draw(deck);
            playerDeck.draw(deck);

            // Dealer gets two cards
            dealerDeck.draw(deck);
            dealerDeck.draw(deck);

            while (true) {
                System.out.println("Your hand: ");
                System.out.println(dealerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardsValue());

                // Display Dealer Hand
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");

                // What does the player want to do?
                System.out.println("Would you like to (1)Hit or (2)Stand ?");
                int response = userInput.nextInt();

                // They Hit
                if (response == 1) {
                    playerDeck.draw(deck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    // Bust if > 21
                    if (playerDeck.cardsValue() > 21) {
                        System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }
            // Reveal Dealer Cards
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            // See if dealer has more points than player
            if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }
            // Dealer Draws at 16, stand at 17
            while ((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(deck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            // Display Total Value for Dealer
            System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardsValue());

            // Determine if Dealer is busted
            if ((dealerDeck.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts! You win.");
                playerMoney += playerBet;
                endRound = true;
            }

            // Determine if push
            if ((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("Push!");
                endRound = true;
            }

            // Player wins
            if (playerDeck.cardsValue() > dealerDeck.cardsValue() && endRound == false) {
                playerMoney += playerBet;
                endRound = true;
            }

            else if (endRound == false) {
                System.out.println("You lose the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }
            playerDeck.moveAllToDeck(deck);
            dealerDeck.moveAllToDeck(deck);
            System.out.println("End of hand.");
        }

        System.out.println("Game over! You are out of money. :(");
    }
}
