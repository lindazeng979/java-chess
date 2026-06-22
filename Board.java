import java.awt.*;
import java.util.*;

/**
 * Represents a rectangular game board, containing Piece objects.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Board extends BoundedGrid<Piece>
{
	/**
	 * Constructs a new Board with 
	 * the standard dimensions
	 * 8 by 8.
	 */
	public Board()
	{
		super(8, 8);
	}

	/**
	 * Undos a given move.
	 * 
	 * @precondition:  move has already been made on the board
	 * @postcondition: piece has moved back to its source,
	 *                and any captured piece is returned to its location
	 * @param move move to be undoed
	 */
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}
		
	/**
	 * Returns a list
	 * of all possible moves
	 * for pieces of
	 * a given color.
	 * 
	 * @param color the color
	 * 				of the pieces
	 * 				to be checked
	 * @return an ArrayList of
	 * 			all possible moves
	 */
	public ArrayList<Move> allMoves(Color color)
	{
		ArrayList<Move> res = new ArrayList<Move>();
		ArrayList<Location> locs = getOccupiedLocations();
		for (int i = 0; i < locs.size(); i++)
		{
			if (get(locs.get(i)).getColor() == color)
			{
				ArrayList<Location> dests = get(locs.get(i)).destinations();
				for (int j = 0; j < dests.size(); j++)
				{
					res.add(new Move(get(locs.get(i)), dests.get(j)));
				}
			}
		}
		return res;
	}

	/**
	 * Executes a given move
	 * by removing and replacing
	 * a piece in a different
	 * location on the board.
	 * 
	 * @param move the move to be executed
	 */
	public void executeMove(Move move)
	{
		move.getPiece().removeSelfFromGrid();
		/*if (toBePromoted(move))
		{
			if (move.getPiece().getColor() == Color.WHITE)
			{
				Piece queen = new Queen(Color.WHITE, "white_queen.gif");
				queen.putSelfInGrid(this, move.getDestination());
			}
			else
			{
				Piece queen = new Queen(Color.BLACK, "black_queen.gif");
				queen.putSelfInGrid(this, move.getDestination());
			}
		}*/
		move.getPiece().putSelfInGrid(this, move.getDestination());
	}

	/**
	 * Checks if the king
	 * in a given color is still
	 * on the board
	 * 
	 * @param p	the color to be checked
	 * @return	true if the king is
	 * 			still there, false otherwise
	 */
	public boolean isKingInGrid(Color c)
	{
		ArrayList<Location> res = getOccupiedLocations();
		for (Location r: res)
		{
			if (get(r).getColor().equals(c) && get(r).getValue() == 1000)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if there
	 * are any pieces
	 * of a given color
	 * left on the board
	 * 
	 * @param color	color of pieces left
	 * 				to be checked
	 * @return	true if there are still
	 * 			pieces of that color,
	 * 			false otherwise
	 */
	public boolean anyPiecesInGrid(Color color)
	{
		ArrayList<Location> res = getOccupiedLocations();
		for (Location r: res)
		{
			if (get(r).getColor() == color)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true
	 * if the given move
	 * is of a pawn that is
	 * to be promoted.
	 *
	 * @param move the move to be checked
	 * @return true if the move's piece
	 * 			can be promoted; false otherwise
	 *
	private boolean toBePromoted(Move move)
	{
		Piece p = move.getPiece();
		if (p instanceof Pawn)
		{
			if ((p.getColor() == Color.WHITE && move.getDestination().getRow() == 0) || ((p.getColor() == Color.BLACK && move.getDestination().getRow() == 7)))
			{
				return true;
			}
		}
		return false;
	}*/

		/*//precondition: king must be on the board
	public boolean inCheck(Color color)
	{
		Location kingLoc = null;
		for (Location l: getOccupiedLocations())
		{
			if (get(l) instanceof King && get(l).getColor() == color)
			{
				kingLoc = l;
			}
		}
		if (color == Color.BLACK)
		{
			ArrayList<Move> moves = allMovesHelper(Color.WHITE);
			for (Move m: moves)
			{
				if (m.getDestination().equals(kingLoc))
				{
					return true;
				}
			}
			return false;
		}
		else
		{
			ArrayList<Move> moves = allMovesHelper(Color.BLACK);
			for (Move m: moves)
			{
				if (m.getDestination().equals(kingLoc))
				{
					return true;
				}
			}
			return false;
		}
	}*/

	/**
	 * Returns a list
	 * of all legal moves
	 * for pieces of
	 * a given color.
	 * When in check,
	 * only the king can move
	 * to locations
	 * where it won't die.
	 * 
	 * @param color the color
	 * 				of the pieces
	 * 				to be checked
	 * @return an ArrayList of
	 * 			all possible moves
	 *
	public ArrayList<Move> allMoves(Color color)
	{
		ArrayList<Move> res = allMovesHelper(color);
		if (inCheck(color))
		{
			for (int i = 0; i < res.size(); i++)
			{
				if (!(res.get(i).getPiece() instanceof King))
				{
					res.remove(i);
					i--;
				}
			}
		}
		return res;
	}*/
}