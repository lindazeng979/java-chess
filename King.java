import java.awt.Color;
import java.util.*;

/**
 * Represents a king
 * in chess.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class King extends Piece
{
    /**
     * Constructs a king
     * with given color and filename.
     * It is valued at 1000.
     * 
     * @param col color of king
     * @param fileName file with image of king
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Indicates which locations
     * the king can move to.
     * It can move one away
     * in all directions
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        Location cur = getLocation();
        for (int i = cur.getRow() - 1; i <= cur.getRow() + 1; i++)
        {
            for (int j = cur.getCol() - 1; j <= cur.getCol() + 1; j++)
            {
                Location loc = new Location(i, j);
                if (isValidDestination(loc))
                {
                    res.add(loc);
                }
            }
        }
        return res;
    }

    /*private boolean willDie(Location l)
    {
        ArrayList<Move> other;
        if (getColor() == Color.WHITE)
        {
            other = getBoard().allMoves(Color.BLACK);
        }
        else
        {
            other = getBoard().allMoves(Color.WHITE);
        }
        for (Move m: other)
        {
            if (m.getDestination().equals(l))
            {
                return true;
            }
        }
        return false;
    }
    */
}
