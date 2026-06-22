import java.awt.Color;
import java.util.*;

/**
 * Represents a knight
 * in chess.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Knight extends Piece
{
    /**
     * Constructs a knight
     * with given color and filename.
     * It is valued at 3.
     * 
     * @param col color of knight
     * @param fileName file with image of knight
     */
    public Knight(Color col, String filename)
    {
        super(col, filename, 3);
    }
    
    /**
     * Indicates which locations
     * the knight can move to.
     * It can move in Ls any direction
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        int curR = getLocation().getRow();
        int curC = getLocation().getCol();
        res.add(new Location(curR + 2, curC + 1));
        res.add(new Location(curR + 2, curC - 1));
        res.add(new Location(curR - 2, curC + 1));
        res.add(new Location(curR - 2, curC - 1));
        res.add(new Location(curR + 1, curC + 2));
        res.add(new Location(curR - 1, curC + 2));
        res.add(new Location(curR + 1, curC - 2));
        res.add(new Location(curR -1, curC - 2));
        for (int i = res.size() - 1; i >= 0; i--)
        {
            if (!isValidDestination(res.get(i)))
            {
                res.remove(i);
            }
        }
        return res;
    }
}
