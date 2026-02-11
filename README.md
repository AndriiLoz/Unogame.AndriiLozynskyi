# UNO Card Game - SYST 17796 Project

## Project Information
- **Course:** SYST 17796 - Fundamentals of Software Design and Development
- **Student:** Andrii Lozynskyi
- **Student ID:** 991811357
- **Game:** UNO
- **Date:** February 10, 2026

## Game Description
This is a Java implementation of the popular UNO card game. UNO is a card-matching game where players race to empty their hands by playing cards that match the color or value of the top card on the discard pile.

### Game Rules
1. Each player starts with 7 cards
2. Players must match the top card by either:
   - Color (RED, YELLOW, GREEN, BLUE)
   - Number (0-9)
   - Action type (SKIP, REVERSE, DRAW TWO)
3. If a player cannot play, they must draw a card
4. Action Cards:
   - **SKIP** - Next player loses their turn
   - **REVERSE** - Reverses the direction of play
   - **DRAW TWO** - Next player draws 2 cards and loses their turn
   - **WILD** - Player chooses the color to continue
   - **WILD DRAW FOUR** - Player chooses color, next player draws 4 cards and loses turn
5. When a player has one card left, they must say "UNO!"
6. First player to empty their hand wins

### Card Deck Composition
- **108 cards total:**
  - Number cards (0-9) in 4 colors: 76 cards
    - One 0 per color
    - Two of each 1-9 per color
  - Action cards in 4 colors: 24 cards
    - 2 SKIP per color
    - 2 REVERSE per color
    - 2 DRAW TWO per color
  - Wild cards: 8 cards
    - 4 WILD
    - 4 WILD DRAW FOUR

## Project Structure
```
UnoGame/
├── src/
│   └── ca/
│       └── sheridancollege/
│           └── project/
│               ├── Card.java              (Base class - abstract)
│               ├── Player.java            (Base class - abstract)
│               ├── Game.java              (Base class - abstract)
│               ├── GroupOfCards.java      (Base class - concrete)
│               ├── UnoCard.java           (UNO card implementation)
│               ├── UnoPlayer.java         (Player implementation)
│               ├── UnoDeck.java           (Deck implementation)
│               ├── UnoGame.java           (Game logic)
│               └── Main.java              (Entry point)
├── README.md
└── .gitignore
```

## How to Play
1. Run the program
2. Enter names for two players when prompted
3. Press Enter to start the game
4. On your turn:
   - View your hand and the top card
   - Choose a card to play by entering its number
   - Or enter 0 to draw a card
5. If playing a WILD card, choose a color (1-4)
6. Continue until one player wins

## Features
- Complete UNO card game implementation
- Interactive console-based gameplay
- Support for all standard UNO action cards
- Automatic deck reshuffling when draw pile is empty
- Input validation and error handling
- Turn-by-turn gameplay with clear prompts

## Object-Oriented Design
- **Inheritance:** Base classes (Card, Player, Game, GroupOfCards) extended by UNO-specific classes
- **Encapsulation:** Private fields with public accessor methods
- **Abstraction:** Abstract methods in base classes implemented by subclasses
- **Polymorphism:** ArrayList of Player objects containing UnoPlayer instances
- **Enums:** Type-safe representation of colors and card types
- **ArrayList:** Dynamic card management for hands, deck, and discard pile

## Technologies Used
- Java SE
- Java Collections Framework (ArrayList)
- Scanner for user input
- Enums for type safety

## Class Relationships
```
Card (abstract)
  └── UnoCard

Player (abstract)
  └── UnoPlayer

Game (abstract)
  └── UnoGame

GroupOfCards (concrete)
  └── UnoDeck
```

## Future Enhancements (Not in Current Version)
- Support for 3-4 players
- Challenging on Wild Draw Four
- Stacking of Draw Two and Draw Four cards
- Score tracking across multiple rounds
- Custom house rules
- GUI interface

## Author
Andrii Lozynskyi - Student ID: 991811357

## Course Information
SYST 17796 - Fundamentals of Software Design and Development
Sheridan College

## License
This project is created for educational purposes as part of the SYST 17796 course.
