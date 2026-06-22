import java.awt.*;
import java.util.*;

/**
 * Represents
 * a piece on the chess
 * board.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public abstract class Piece
{
	//the board this piece is on
	private Board board;

	//the location of this piece on the board
	private Location location;

	//the color of the piece
	private Color color;

	//the file used to display this piece
	private String imageFileName;

	//the approximate value of this piece in a game of chess
	private int value;

	/**
     * Constructs a new Piece with the given color,
     * value, and file name (image to be displayed).
     * 
     * @param col color of piece
     * @param fileName name of file with image of piece
     * @param val value of piece in game
     */
	public Piece(Color col, String fileName, int val)
	{
		color = col;
		imageFileName = fileName;
		value = val;
	}

	/**
     * returns the board this piece is on
     * 
     * @return the board of the piece
     */
	public Board getBoard()
	{
		return board;
	}

	/**
     * returns the location of this piece on the board
     *
     * @return location of piece on board
     */
	public Location getLocation()
	{
		return location;
	}

	/**
     * returns the color of this piece
     * 
     * @return color of this pliece
     */
	public Color getColor()
	{
		return color;
	}

	/**
     * returns the name of the file used to display this piece
     * 
     * @return file name of piece
     */
	public String getImageFileName()
	{
		return imageFileName;
	}

	/**
     * returns a number representing the relative value of this piece
     * 
     * @return value of piece
     */
	public int getValue()
	{
		return value;
	}

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed. 
     * @Precondition: (1) This piece is not contained in a grid (2)
     * loc is valid in gr
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. 
     * @Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. 
     * @Precondition: (1) This piece is contained in a grid (2)
     * newLocation is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }

    /**
     * Returns true if
     * the given location
     * is valid, and 
     * either empty or
     * occupied by a Piece of a
     * different color.
     * 
     * @param dest the destination to be checked
     * @return true if the dest
     *         is valid and either
     *         empty or occupied
     *         by a piece of diff color,
     *         false otherwise      
     */
    public boolean isValidDestination(Location dest)
    {
        if (board.isValid(dest))
        {
            return (board.get(dest) == null || board.get(dest).getColor() != color);
        }
        return false;
    }

    /**
     * Indicates which locations
     * this piece can move to.
     * 
     * @return an ArrayList of
     *         locations this can
     *         move to
     */
    public abstract ArrayList<Location> destinations();

    /**
     * Adds to a
     * given list all the locations
     * that are in
     * a given direction from
     * this piece until
     * it reaches
     * another piece or the edge of
     * the board.
     * If the piece reached is of opposing
     * color, its location is also added
     * to the list.
     * 
     * @param dests the list of locations
     *              to add to
     * @param direction the direction to check
     *                  locations
     */
    public void sweep(ArrayList<Location> dests, int direction)
    {
        Location dest = getLocation().getAdjacentLocation(direction);
        while (board.isValid(dest) && board.get(dest) == null)
        {
            dests.add(dest);
            dest = dest.getAdjacentLocation(direction);
        }
        if (board.isValid(dest) && board.get(dest).getColor() != color)
        {
            dests.add(dest);
        }
    }

    /**
     * Checks if two
     * objects are equal.
     * 
     * @param o other object to be checked
     * @return true if the objects are
     *          equal, false otherwise
     */
    /*public boolean equals(Object o)
    {
        if (o instanceof Piece)
        {
            Piece p = (Piece)o;
            return (p.getColor().equals(getColor()) && p.getValue() == getValue() && p.getImageFileName().equals(p.getImageFileName()));
        }
        else
        {
            return false;
        }
    }*/
}