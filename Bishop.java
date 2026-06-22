import java.awt.Color;
import java.util.*;

/**
 * Represents a bishop
 * piece in a chess game.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Bishop extends Piece
{
    /**
     * Constructs a bishop
     * with given color and image.
     * It is valued at 3.
     * 
     * @param col color of bishop
     * @param filename file used to display bishop
     */
    public Bishop(Color col, String filename)
    {
        super(col, filename, 3);
    }

    /**
     * Indicates which locations
     * bishop can move to.
     * It can move diagonally.
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        sweep(res, Location.NORTHEAST);
        sweep(res, Location.NORTHWEST);
        sweep(res, Location.SOUTHEAST);
        sweep(res, Location.SOUTHWEST);
        return res;
    }
}
