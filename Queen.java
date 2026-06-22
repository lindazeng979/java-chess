import java.awt.Color;
import java.util.*;

/**
 * Represents a queen in chess.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Queen extends Piece
{
    /**
     * Constructs a queen
     * with given color and filename.
     * It is valued at 9.
     * 
     * @param col color of queen
     * @param fileName file with image of queen
     */
    public Queen(Color col, String filename)
    {
        super(col, filename, 9);
    }

    /**
     * Indicates which locations
     * the  queen can move to.
     * Can move in all directions.
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        sweep(res, Location.NORTH);
        sweep(res, Location.EAST);
        sweep(res, Location.SOUTH);
        sweep(res, Location.WEST);
        sweep(res, Location.NORTHEAST);
        sweep(res, Location.NORTHWEST);
        sweep(res, Location.SOUTHEAST);
        sweep(res, Location.SOUTHWEST);
        //System.out.println(res);
        return res;
    }
}
