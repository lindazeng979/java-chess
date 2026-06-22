import java.util.*;
import java.awt.Color;

/**
 * Represents a
 * smart player in a chess game
 * who plays with strategy.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class SmartPlayer extends Player
{
    /**
     * Constructs a smart player with given attributes.
     * 
     * @param boardP    board to play on
     * @param nameP     name of player
     * @param colorP    color to play
     */
    public SmartPlayer(Board board, String name, Color color)
    {
        super(board, name, color);
    }

    /**
     * Returns the score of the board
     * for the smart player, represented
     * by the sum of the smart player's pieces
     * minus the sum of the opponent's pieces.
     * 
     * @return the currenty score of the smart player 
     */
    public int score()
    {
        int sum = 0;
        ArrayList<Location> all = getBoard().getOccupiedLocations();
        for (Location l: all)
        {
            Piece p = getBoard().get(l);
            if (p.getColor() == getColor())
            {
                sum += p.getValue();
            }
            else
            {
                sum -= p.getValue();
            }
        }
        return sum;
    }

    /**
     * Returns next move the 
     * player will play.
     * Looks several steps ahead
     * at what opponent will do,
     * and finds the highest worst-case
     * result. (currenty looks forward 4 times)
     * 
     * @precondition there is at least one
     *              possible move
     * 
     * @return player's next move
     */
    public Move nextMove()
    {
        ArrayList<Move> possible = getBoard().allMoves(getColor());
        Move max = possible.get(0);
        getBoard().executeMove(possible.get(0));
        int maxScore = valueOfMeanestResponse(3);
        getBoard().undoMove(possible.get(0));
        for (Move m: possible)
        {
            getBoard().executeMove(m);
            int val = valueOfMeanestResponse(3);
            if (val > maxScore)
            {
                maxScore = val;
                max = m;
            }
            getBoard().undoMove(m);
        }
        return max;
    }

    /**
     * Repeatedly looks forward
     * num times to find the lowest
     * score possible for this player
     * based on the opponents' meanest
     * moves.
     * 
     * @precondition there is at least one
     *              possible move
     * 
     * @param num number of times to look ahead
     * @return the int represent the worst score
     *          that could result from num moves;
     *          the current score if num is 0
     */
    private int valueOfMeanestResponse(int num)
    {
        if (num == 0)
        {
            return score();
        }
        Color opp = Color.WHITE;
        if (getColor() ==Color.WHITE)
        {
            opp = Color.BLACK;
        }
        ArrayList<Move> possible = getBoard().allMoves(opp);
        getBoard().executeMove(possible.get(0));
        int minScore = valueOfBestMove(num - 1);
        //int minScore = score();
        getBoard().undoMove(possible.get(0));
        for (Move m: possible)
        {
            getBoard().executeMove(m);
            int val = valueOfBestMove(num - 1);
            if (val < minScore)
            {
                minScore = val;
            }
            getBoard().undoMove(m);
        }
        return minScore;
    }

    /**
     * Repeatedly looks forward
     * num times to find the
     * best score this player can do
     * (characterized by the highest
     * out of the meanest scores
     * that may result)
     * 
     * @param num number of times to look forward
     * @return  int representing the most optimal
     *          score for the player
     *          out of the worst results;
     *          the current score if num is 0
     */
    private int valueOfBestMove(int num)
    {
        if (num == 0)
        {
            return score();
        }
        ArrayList<Move> possible = getBoard().allMoves(getColor());
        getBoard().executeMove(possible.get(0));
        int maxScore = valueOfMeanestResponse(num - 1);
        getBoard().undoMove(possible.get(0));
        for (Move m: possible)
        {
            getBoard().executeMove(m);
            int val = valueOfMeanestResponse(num - 1);
            if (val > maxScore)
            {
                maxScore = val;
            }
            getBoard().undoMove(m);
        }
        return maxScore;
    }
}
