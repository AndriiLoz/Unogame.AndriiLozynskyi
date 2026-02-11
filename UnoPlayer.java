/**
 * SYST 17796 Project - UNO Card Game
 * @author Andrii Lozynskyi
 * Student ID: 991811357
 * Date: February 10, 2026
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a player in the UNO game
 */
public class UnoPlayer extends Player {
    
    private ArrayList<UnoCard> hand;
    private Scanner scanner;
    
    /**
     * Constructor for UnoPlayer
     * @param name the player's name
     */
    public UnoPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Get the player's hand of cards
     * @return the hand
     */
    public ArrayList<UnoCard> getHand() {
        return hand;
    }
    
    /**
     * Add a card to the player's hand
     * @param card the card to add
     */
    public void addCard(UnoCard card) {
        hand.add(card);
    }
    
    /**
     * Remove a card from the player's hand
     * @param card the card to remove
     * @return true if the card was removed
     */
    public boolean removeCard(UnoCard card) {
        return hand.remove(card);
    }
    
    /**
     * Get the number of cards in hand
     * @return the card count
     */
    public int getCardCount() {
        return hand.size();
    }
    
    /**
     * Check if player has won (no cards left)
     * @return true if player has no cards
     */
    public boolean hasWon() {
        return hand.isEmpty();
    }
    
    /**
     * Display the player's hand
     */
    public void showHand() {
        System.out.println("\n" + getName() + "'s Hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }
    
    /**
     * Check if player has a playable card
     * @param topCard the card on top of discard pile
     * @param currentColor the current color
     * @return true if player has a playable card
     */
    public boolean hasPlayableCard(UnoCard topCard, UnoCard.Color currentColor) {
        for (UnoCard card : hand) {
            if (card.canPlayOn(topCard, currentColor)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Get all playable cards in hand
     * @param topCard the card on top of discard pile
     * @param currentColor the current color
     * @return list of playable cards
     */
    public ArrayList<UnoCard> getPlayableCards(UnoCard topCard, UnoCard.Color currentColor) {
        ArrayList<UnoCard> playable = new ArrayList<>();
        for (UnoCard card : hand) {
            if (card.canPlayOn(topCard, currentColor)) {
                playable.add(card);
            }
        }
        return playable;
    }
    
    /**
     * Player chooses a card to play
     * @param topCard the card on top of discard pile
     * @param currentColor the current color
     * @return the chosen card, or null if drawing
     */
    public UnoCard chooseCard(UnoCard topCard, UnoCard.Color currentColor) {
        showHand();
        ArrayList<UnoCard> playableCards = getPlayableCards(topCard, currentColor);
        
        System.out.println("\nTop card: " + topCard);
        System.out.println("Current color: " + currentColor);
        System.out.println("\nPlayable cards:");
        
        if (playableCards.isEmpty()) {
            System.out.println("No playable cards. You must draw.");
            return null;
        }
        
        for (int i = 0; i < playableCards.size(); i++) {
            int cardIndex = hand.indexOf(playableCards.get(i)) + 1;
            System.out.println(cardIndex + ". " + playableCards.get(i));
        }
        
        System.out.print("\nEnter card number to play (or 0 to draw): ");
        int choice = getValidInput(0, hand.size());
        
        if (choice == 0) {
            return null; // Player chooses to draw
        }
        
        UnoCard chosenCard = hand.get(choice - 1);
        
        // Verify the card is playable
        if (!chosenCard.canPlayOn(topCard, currentColor)) {
            System.out.println("That card cannot be played! Drawing instead.");
            return null;
        }
        
        return chosenCard;
    }
    
    /**
     * Player chooses a color for wild cards
     * @return the chosen color
     */
    public UnoCard.Color chooseColor() {
        System.out.println("\nChoose a color:");
        System.out.println("1. RED");
        System.out.println("2. YELLOW");
        System.out.println("3. GREEN");
        System.out.println("4. BLUE");
        System.out.print("Enter choice (1-4): ");
        
        int choice = getValidInput(1, 4);
        
        switch (choice) {
            case 1: return UnoCard.Color.RED;
            case 2: return UnoCard.Color.YELLOW;
            case 3: return UnoCard.Color.GREEN;
            case 4: return UnoCard.Color.BLUE;
            default: return UnoCard.Color.RED;
        }
    }
    
    /**
     * Get valid integer input within range
     * @param min minimum value
     * @param max maximum value
     * @return valid input
     */
    private int getValidInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
    
    /**
     * Play method - implemented in game loop
     */
    @Override
    public void play() {
        // Implemented in UnoGame class
    }
}
