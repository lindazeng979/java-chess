import java.awt.Color;
import java.util.*;

/**
 * Represents a pawn in chess.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Pawn extends Piece
{
    /**
     * Constructs a pawn
     * with given color and filename.
     * It is valued at 1.
     * 
     * @param col color of pawn
     * @param fileName file with image of pawn
     */
    public Pawn(Color col, String filename)
    {
        super(col, filename, 1);
    }
    
    /**
     * Indicates which locations
     * the  pawn can move to.
     * It can move up 1. Capture 
     * diagonally by 1. Move up
     * 2 at the start.
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> res = new ArrayList<Location>();
        Location front;
        Location side1;
        Location side2;
        if (getColor() == Color.WHITE)
        {
            front = new Location(getLocation().getRow() - 1, getLocation().getCol());
            side1 = new Location(getLocation().getRow() - 1, getLocation().getCol() + 1);
            side2 = new Location(getLocation().getRow() - 1, getLocation().getCol() - 1);
            if(getLocation().getRow() == 6)
            {
                Location more = new Location(getLocation().getRow() - 2, getLocation().getCol());
                if (getBoard().get(more) == null)
                {
                    res.add(more);
                }
            }
        }
        else
        {
            front = new Location(getLocation().getRow() + 1, getLocation().getCol());
            side1 = new Location(getLocation().getRow() + 1, getLocation().getCol() + 1);
            side2 = new Location(getLocation().getRow() + 1, getLocation().getCol() - 1);
            if (getLocation().getRow() == 1)
            {  
                Location more = new Location(getLocation().getRow() + 2, getLocation().getCol());
                if (getBoard().get(more) == null)
                {
                    res.add(more);
                }
            }
        }
        if (getBoard().isValid(front) && getBoard().get(front) == null)
            res.add(front);
        if (getBoard().isValid(side1) && getBoard().get(side1) != null && getBoard().get(side1).getColor() != getColor())
        {
            res.add(side1);
        }
        if (getBoard().isValid(side2) && getBoard().get(side2) != null && getBoard().get(side2).getColor() != getColor())
        {
            res.add(side2);
        }
        return res;
    }
}
