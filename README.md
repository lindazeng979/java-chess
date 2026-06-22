# Chess

A simple Java chess implementation built for learning and experimentation. The projects includes piece and board display, player implementations (human and AI), and a Game runner.

## Requirements

- Java 17 or newer

## Getting Started

Open a terminal in the project root and compile all source files:

```bash
javac *.java
```

Run the game:

```bash
java Game
```

The `Game` class is the application entry point. When launched, it creates a game instance and uses `BoardDisplay` to render the chess board and handle user interaction.

## How to Play

When the game starts, you will be prompted to configure both White and Black players.

For each side, choose:

- A player type: `human`, `smart`, or `random`
- A player name

Press **Enter** to accept the default values (`human` with names  `White/Black`).

Example:

```text
White player type (human/smart/random) [human]: smart
White player name [White]: Bot

Black player type (human/smart/random) [human]: human
Black player name [Black]: Alice
```

## Player Types

### HumanPlayer

Allows a user to make moves through the graphical board interface.

If a side is configured as a `human` player:

1. Click the piece you want to move.
2. The selected piece should highlight.
3. Click a valid destination square.

When it is a human player's turn, the game will also display a terminal message such as:

```text
White player's turn: Alice
```

### RandomPlayer

Chooses a legal move uniformly at random from all available moves.

### SmartPlayer

Uses a minimax-style search algorithm to evaluate future positions.

The algorithm executes the following steps:
- Evaluates current position by value (piece count and strength)
- Simulates future move sequences
- Chooses the move with the best worst-case outcome
- Searches several plies deep (currently 4 plies by default)


## Project Structure

### Game Logic

- `Game.java` — application entry point and game loop
- `Board.java` — chess board implementation
- `Move.java` — move representation
- `Location.java` — board coordinates

### Grid Infrastructure

- `Grid.java`
- `AbstractGrid.java`
- `BoundedGrid.java`

These classes provide the underlying grid abstraction used by the board.

### Pieces

- `Piece.java`
- `King.java`
- `Queen.java`
- `Rook.java`
- `Bishop.java`
- `Knight.java`
- `Pawn.java`

Each piece class defines its movement behavior and legal move generation.

### Players

- `Player.java`
- `HumanPlayer.java`
- `RandomPlayer.java`
- `SmartPlayer.java`

These classes define the available player implementations.

### User Interface

- `BoardDisplay.java` — graphical board display and user interaction handling

## Project Extensions

Possible improvements include more advanced AI search and evaluation functions, alpha-beta pruning, opening books, additional chess rules and edge cases, and enhanced UI features.

## Acknowledgments

This project was created as a course project for AP Computer Science with Data Structures (2023).

## Author

Linda Zeng

## License

This project is licensed under the MIT License. See the LICENSE file for details.
