import java.util.*;
import java.awt.Color;

/**
 * Represents a
 * random player in a chess game
 * who plays random moves each turn.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class RandomPlayer extends Player
{
    /**
     * Constructs a random player with given attributes.
     * 
     * @param boardP    board to play on
     * @param nameP     name of player
     * @param colorP    color to play
     */
    public RandomPlayer(Board boardP, String nameP, Color colorP)
    {
        super(boardP, nameP, colorP);
    }

    /**
     * Returns next move the 
     * player will play (randomized)
     * 
     * @return player's next move
     */
    public Move nextMove()
    {
        ArrayList<Move> moves = getBoard().allMoves(getColor());
        int random = (int)(Math.random() * moves.size());
        return moves.get(random);
    }
}
