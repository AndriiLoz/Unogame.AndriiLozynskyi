/**
 * SYST 17796 Project - UNO Card Game
 * @author Andrii Lozynskyi
 * Student ID: 991811357
 * Date: February 10, 2026
 */
package ca.sheridancollege.project;

/**
 * Represents an UNO card with color, value, and type
 */
public class UnoCard extends Card {
    
    // Enum for card colors
    public enum Color {
        RED, YELLOW, GREEN, BLUE, WILD
    }
    
    // Enum for card types
    public enum Type {
        NUMBER, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR
    }
    
    private final Color color;
    private final Type type;
    private final int value; // 0-9 for number cards, -1 for action cards
    
    /**
     * Constructor for number cards
     * @param color the color of the card
     * @param value the number value (0-9)
     */
    public UnoCard(Color color, int value) {
        this.color = color;
        this.value = value;
        this.type = Type.NUMBER;
    }
    
    /**
     * Constructor for action and wild cards
     * @param color the color of the card (WILD for wild cards)
     * @param type the type of card
     */
    public UnoCard(Color color, Type type) {
        this.color = color;
        this.type = type;
        this.value = -1;
    }
    
    /**
     * Get the color of the card
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Get the type of the card
     * @return the type
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Get the numeric value of the card
     * @return the value (0-9 for number cards, -1 for action cards)
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Check if this card can be played on top of another card
     * @param topCard the card on top of the discard pile
     * @param currentColor the current color (important for wild cards)
     * @return true if the card can be played
     */
    public boolean canPlayOn(UnoCard topCard, Color currentColor) {
        // Wild cards can always be played
        if (this.type == Type.WILD || this.type == Type.WILD_DRAW_FOUR) {
            return true;
        }
        
        // Match color
        if (this.color == currentColor) {
            return true;
        }
        
        // Match number
        if (this.type == Type.NUMBER && topCard.type == Type.NUMBER && this.value == topCard.value) {
            return true;
        }
        
        // Match action type
        if (this.type == topCard.type && this.type != Type.NUMBER) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Check if this is an action card
     * @return true if action card
     */
    public boolean isActionCard() {
        return type != Type.NUMBER;
    }
    
    /**
     * String representation of the card
     * @return string showing color and value/type
     */
    @Override
    public String toString() {
        if (type == Type.NUMBER) {
            return color + " " + value;
        } else if (type == Type.WILD || type == Type.WILD_DRAW_FOUR) {
            return type.toString().replace("_", " ");
        } else {
            return color + " " + type.toString().replace("_", " ");
        }
    }
}
