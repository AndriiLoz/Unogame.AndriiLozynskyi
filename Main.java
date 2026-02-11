/**
 * SYST 17796 Project - UNO Card Game
 * @author Andrii Lozynskyi
 * Student ID: 991811357
 * Date: February 10, 2026
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * Main class to start and run the UNO card game
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("========================================");
        System.out.println("       Welcome to UNO Card Game!");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Game Rules:");
        System.out.println("- Match the card color or number/action");
        System.out.println("- Number cards (0-9) must match color or number");
        System.out.println("- Action cards:");
        System.out.println("  * SKIP - Next player loses a turn");
        System.out.println("  * REVERSE - Changes direction of play");
        System.out.println("  * DRAW TWO - Next player draws 2 cards");
        System.out.println("  * WILD - Change the color");
        System.out.println("  * WILD DRAW FOUR - Change color, next");
        System.out.println("    player draws 4 cards");
        System.out.println("- Say 'UNO!' when you have 1 card left");
        System.out.println("- First player to empty their hand wins!");
        System.out.println();
        
        // Get player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();
        
        // Create and start the game
        UnoGame game = new UnoGame("UNO");
        game.initializePlayers(player1Name, player2Name);
        
        System.out.print("\nReady to start? Press Enter...");
        scanner.nextLine();
        
        game.play();
        
        System.out.println("\nThank you for playing UNO!");
        scanner.close();
    }
}
