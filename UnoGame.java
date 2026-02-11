/**
 * SYST 17796 Project - UNO Card Game
 * @author Andrii Lozynskyi
 * Student ID: 991811357
 * Date: February 10, 2026
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Implementation of the UNO card game
 * Simplified rules for 2 players with basic actions
 */
public class UnoGame extends Game {
    
    private UnoDeck deck;
    private ArrayList<UnoCard> discardPile;
    private UnoCard.Color currentColor;
    private int currentPlayerIndex;
    private boolean isReversed;
    private UnoPlayer winner;
    
    /**
     * Constructor for UnoGame
     * @param name the name of the game
     */
    public UnoGame(String name) {
        super(name);
        this.discardPile = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.isReversed = false;
        this.winner = null;
    }
    
    /**
     * Initialize the game with players
     * @param player1Name name of first player
     * @param player2Name name of second player
     */
    public void initializePlayers(String player1Name, String player2Name) {
        UnoPlayer player1 = new UnoPlayer(player1Name);
        UnoPlayer player2 = new UnoPlayer(player2Name);
        
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        setPlayers(players);
        
        // Create and shuffle deck
        deck = new UnoDeck();
        deck.shuffle();
        
        // Deal 7 cards to each player
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.drawCard());
            player2.addCard(deck.drawCard());
        }
        
        // Place first card on discard pile (make sure it's not a Wild card)
        UnoCard firstCard = deck.drawCard();
        while (firstCard.getType() == UnoCard.Type.WILD || 
               firstCard.getType() == UnoCard.Type.WILD_DRAW_FOUR) {
            deck.addCard(firstCard);
            deck.shuffle();
            firstCard = deck.drawCard();
        }
        discardPile.add(firstCard);
        currentColor = firstCard.getColor();
        
        System.out.println("\n=== " + getName() + " ===");
        System.out.println("Starting card: " + firstCard);
        System.out.println(player1.getName() + " has " + player1.getCardCount() + " cards");
        System.out.println(player2.getName() + " has " + player2.getCardCount() + " cards");
        System.out.println();
    }
    
    /**
     * Play the game
     */
    @Override
    public void play() {
        while (winner == null) {
            UnoPlayer currentPlayer = (UnoPlayer) getPlayers().get(currentPlayerIndex);
            
            System.out.println("\n========================================");
            System.out.println(currentPlayer.getName() + "'s Turn");
            System.out.println("========================================");
            
            playTurn(currentPlayer);
            
            // Check if current player won
            if (currentPlayer.hasWon()) {
                winner = currentPlayer;
                break;
            }
            
            // Move to next player
            nextPlayer();
        }
        
        declareWinner();
    }
    
    /**
     * Play a single turn
     * @param player the current player
     */
    private void playTurn(UnoPlayer player) {
        UnoCard topCard = discardPile.get(discardPile.size() - 1);
        
        // Let player choose a card
        UnoCard playedCard = player.chooseCard(topCard, currentColor);
        
        if (playedCard == null) {
            // Player draws a card
            drawCards(player, 1);
            System.out.println(player.getName() + " draws a card.");
            
            // Check if the drawn card can be played
            UnoCard drawnCard = player.getHand().get(player.getCardCount() - 1);
            if (drawnCard.canPlayOn(topCard, currentColor)) {
                System.out.print("\nYou drew: " + drawnCard);
                System.out.print("\nDo you want to play it? (y/n): ");
                String response = new java.util.Scanner(System.in).nextLine();
                if (response.toLowerCase().equals("y")) {
                    playedCard = drawnCard;
                    player.removeCard(playedCard);
                    discardPile.add(playedCard);
                    handleCardEffect(playedCard, player);
                }
            }
        } else {
            // Player plays the chosen card
            player.removeCard(playedCard);
            discardPile.add(playedCard);
            
            System.out.println(player.getName() + " plays: " + playedCard);
            
            // Handle UNO call
            if (player.getCardCount() == 1) {
                System.out.println(player.getName() + " says: UNO!");
            }
            
            handleCardEffect(playedCard, player);
        }
        
        System.out.println("\nCards remaining: " + player.getCardCount());
    }
    
    /**
     * Handle the effect of action cards
     * @param card the card that was played
     * @param player the player who played it
     */
    private void handleCardEffect(UnoCard card, UnoPlayer player) {
        switch (card.getType()) {
            case SKIP:
                System.out.println("Next player is skipped!");
                currentColor = card.getColor();
                nextPlayer(); // Skip next player
                break;
                
            case REVERSE:
                isReversed = !isReversed;
                System.out.println("Direction reversed!");
                currentColor = card.getColor();
                break;
                
            case DRAW_TWO:
                nextPlayer();
                UnoPlayer nextPlayer = (UnoPlayer) getPlayers().get(currentPlayerIndex);
                drawCards(nextPlayer, 2);
                System.out.println(nextPlayer.getName() + " draws 2 cards and loses a turn!");
                currentColor = card.getColor();
                break;
                
            case WILD:
                currentColor = player.chooseColor();
                System.out.println("Color changed to: " + currentColor);
                break;
                
            case WILD_DRAW_FOUR:
                currentColor = player.chooseColor();
                System.out.println("Color changed to: " + currentColor);
                nextPlayer();
                UnoPlayer victim = (UnoPlayer) getPlayers().get(currentPlayerIndex);
                drawCards(victim, 4);
                System.out.println(victim.getName() + " draws 4 cards and loses a turn!");
                break;
                
            case NUMBER:
                currentColor = card.getColor();
                break;
        }
    }
    
    /**
     * Draw cards from the deck
     * @param player the player drawing cards
     * @param count number of cards to draw
     */
    private void drawCards(UnoPlayer player, int count) {
        for (int i = 0; i < count; i++) {
            if (deck.isEmpty()) {
                reshuffleDiscardPile();
            }
            
            if (!deck.isEmpty()) {
                player.addCard(deck.drawCard());
            }
        }
    }
    
    /**
     * Reshuffle the discard pile into the deck when deck is empty
     */
    private void reshuffleDiscardPile() {
        if (discardPile.size() <= 1) {
            return; // Keep at least the top card
        }
        
        System.out.println("Reshuffling discard pile into deck...");
        
        // Keep the top card
        UnoCard topCard = discardPile.remove(discardPile.size() - 1);
        
        // Put all other cards back in deck
        for (UnoCard card : discardPile) {
            deck.addCard(card);
        }
        
        discardPile.clear();
        discardPile.add(topCard);
        
        deck.shuffle();
    }
    
    /**
     * Move to the next player
     */
    private void nextPlayer() {
        if (isReversed) {
            currentPlayerIndex--;
            if (currentPlayerIndex < 0) {
                currentPlayerIndex = getPlayers().size() - 1;
            }
        } else {
            currentPlayerIndex++;
            if (currentPlayerIndex >= getPlayers().size()) {
                currentPlayerIndex = 0;
            }
        }
    }
    
    /**
     * Declare the winner
     */
    @Override
    public void declareWinner() {
        System.out.println("\n========================================");
        System.out.println("           GAME OVER!");
        System.out.println("========================================");
        if (winner != null) {
            System.out.println("*** " + winner.getName() + " WINS! ***");
        }
        
        System.out.println("\nFinal scores:");
        for (Player p : getPlayers()) {
            UnoPlayer player = (UnoPlayer) p;
            System.out.println(player.getName() + ": " + player.getCardCount() + " cards remaining");
        }
    }
}
