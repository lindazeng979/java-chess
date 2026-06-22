import java.util.*;
import java.awt.Color;

/**
 * Represents a
 * human player in a chess game
 * who plays based on
 * a user's clicks.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display; //display of game

    /**
     * Constructs a human player with given attributes.
     * 
     * @param boardP    board to play on
     * @param nameP     name of player
     * @param colorP    color to play
     * @param displayP  display of game player is in
     */
    public HumanPlayer(Board boardP, String nameP, Color colorP, BoardDisplay displayP)
    {
        super(boardP, nameP, colorP);
        display = displayP;
    }

    /**
     * Returns next move the 
     * player will play
     * (based on the move selected
     * by the user currently)
     * 
     * @return player's next move
     */
    public Move nextMove()
    {
        Move move = display.selectMove();
        ArrayList<Move> validMoves = getBoard().allMoves(getColor());
        while (!validMoves.contains(move))
        {
            move = display.selectMove();
        }
        return move;
    }
}
