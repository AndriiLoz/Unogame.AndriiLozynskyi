/**
 * SYST 17796 Project - UNO Card Game
 * @author Andrii Lozynskyi
 * Student ID: 991811357
 * Date: February 10, 2026
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Represents a standard UNO deck with 108 cards
 */
public class UnoDeck extends GroupOfCards {
    
    /**
     * Constructor creates a standard 108-card UNO deck
     */
    public UnoDeck() {
        super(108);
        initializeDeck();
    }
    
    /**
     * Initialize the deck with all 108 standard UNO cards
     * - 19 cards of each color (RED, YELLOW, GREEN, BLUE): 0 (1 card), 1-9 (2 each)
     * - 8 action cards of each color: Skip (2), Reverse (2), Draw Two (2)
     * - 4 Wild cards
     * - 4 Wild Draw Four cards
     */
    private void initializeDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        
        // Add colored number and action cards
        for (UnoCard.Color color : new UnoCard.Color[]{
            UnoCard.Color.RED, UnoCard.Color.YELLOW, 
            UnoCard.Color.GREEN, UnoCard.Color.BLUE}) {
            
            // One zero card per color
            cards.add(new UnoCard(color, 0));
            
            // Two of each number 1-9 per color
            for (int value = 1; value <= 9; value++) {
                cards.add(new UnoCard(color, value));
                cards.add(new UnoCard(color, value));
            }
            
            // Two of each action card per color
            cards.add(new UnoCard(color, UnoCard.Type.SKIP));
            cards.add(new UnoCard(color, UnoCard.Type.SKIP));
            
            cards.add(new UnoCard(color, UnoCard.Type.REVERSE));
            cards.add(new UnoCard(color, UnoCard.Type.REVERSE));
            
            cards.add(new UnoCard(color, UnoCard.Type.DRAW_TWO));
            cards.add(new UnoCard(color, UnoCard.Type.DRAW_TWO));
        }
        
        // Add Wild cards (4 total)
        for (int i = 0; i < 4; i++) {
            cards.add(new UnoCard(UnoCard.Color.WILD, UnoCard.Type.WILD));
        }
        
        // Add Wild Draw Four cards (4 total)
        for (int i = 0; i < 4; i++) {
            cards.add(new UnoCard(UnoCard.Color.WILD, UnoCard.Type.WILD_DRAW_FOUR));
        }
        
        setCards(cards);
    }
    
    /**
     * Draw a card from the deck
     * @return the top card, or null if deck is empty
     */
    public UnoCard drawCard() {
        ArrayList<Card> cards = getCards();
        if (cards.isEmpty()) {
            return null;
        }
        return (UnoCard) cards.remove(0);
    }
    
    /**
     * Check if deck is empty
     * @return true if no cards left
     */
    public boolean isEmpty() {
        return getCards().isEmpty();
    }
    
    /**
     * Get the number of cards remaining in deck
     * @return card count
     */
    public int getCardCount() {
        return getCards().size();
    }
    
    /**
     * Add a card back to the deck (for reshuffling discard pile)
     * @param card the card to add
     */
    public void addCard(UnoCard card) {
        getCards().add(card);
    }
}
