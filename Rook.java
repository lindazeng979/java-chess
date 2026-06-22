import java.awt.Color;
import java.util.*;

/**
 * Represents a rook in chess.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Rook extends Piece
{
    /**
     * Constructs a rook
     * with given color and filename.
     * It is valued at 5
     * 
     * @param col color of rook
     * @param fileName file with image of rook
     */
    public Rook(Color col, String filename)
    {
        super(col, filename, 5);
    }

    /**
     * Indicates which locations
     * the rook can move to.
     * Can move up and down
     * and left and right.
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        sweep(res, Location.NORTH);
        sweep(res, Location.SOUTH);
        sweep(res, Location.WEST);
        sweep(res, Location.EAST);
        return res;
    }
}
