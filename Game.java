import java.awt.Color;
import java.util.Scanner;

/**
 * Represents a game
 * of chess with all the
 * pieces working functionally
 * and two players (
 * can be human, smart, or random).
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public class Game
{
    /**
     * Runs the chess game.
     * Puts all the pieces on board,
     * displays board, creates two
     * players.
     * 
     * @param args String array from java for main method
     */
    public static void main(String[] args)
    {
        Board board = new Board();
        Piece blackKing = new King(Color.BLACK, "images/black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        Piece whiteKing = new King(Color.WHITE, "images/white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7,4));
        Piece blackRook1 = new Rook(Color.BLACK, "images/black_rook.gif");
        blackRook1.putSelfInGrid(board, new Location(0,0));
        Piece blackRook2 = new Rook(Color.BLACK, "images/black_rook.gif");
        blackRook2.putSelfInGrid(board, new Location(0,7));
        Piece whiteRook1 = new Rook(Color.WHITE, "images/white_rook.gif");
        whiteRook1.putSelfInGrid(board, new Location(7, 0));
        Piece whiteRook2 = new Rook(Color.WHITE, "images/white_rook.gif");
        whiteRook2.putSelfInGrid(board, new Location(7,7));
        for (int i = 0; i < 8; i++)
        {
            Piece whitePawn = new Pawn(Color.WHITE, "images/white_pawn.gif");
            whitePawn.putSelfInGrid(board, new Location(6, i));
            Piece blackPawn = new Pawn(Color.BLACK, "images/black_pawn.gif");
            blackPawn.putSelfInGrid(board, new Location(1, i));
        }
        Piece blackBishop1 = new Bishop(Color.BLACK, "images/black_bishop.gif");
        blackBishop1.putSelfInGrid(board, new Location(0,2));
        Piece blackBishop2 = new Bishop(Color.BLACK, "images/black_bishop.gif");
        blackBishop2.putSelfInGrid(board, new Location(0,5));
        Piece whiteBishop1 = new Bishop(Color.WHITE, "images/white_bishop.gif");
        whiteBishop1.putSelfInGrid(board, new Location(7, 2));
        Piece whiteBishop2 = new Bishop(Color.WHITE, "images/white_bishop.gif");
        whiteBishop2.putSelfInGrid(board, new Location(7,5));
        Piece blackKnight1 = new Knight(Color.BLACK, "images/black_knight.gif");
        blackKnight1.putSelfInGrid(board, new Location(0,1));
        Piece blackKnight2 = new Knight(Color.BLACK, "images/black_knight.gif");
        blackKnight2.putSelfInGrid(board, new Location(0,6));
        Piece whiteKnight1 = new Knight(Color.WHITE, "images/white_knight.gif");
        whiteKnight1.putSelfInGrid(board, new Location(7, 1));
        Piece whiteKnight2 = new Knight(Color.WHITE, "images/white_knight.gif");
        whiteKnight2.putSelfInGrid(board, new Location(7,6));
        Piece blackQueen = new Queen(Color.BLACK, "images/black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        Piece whiteQueen = new Queen(Color.WHITE, "images/white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7,3));
        BoardDisplay display = new BoardDisplay(board);
        /*for (Move m: board.allMoves(Color.WHITE))
        {
            display.setColor(m.getDestination(), Color.YELLOW);
        }*/
        Scanner sc = new Scanner(System.in);
        System.out.println("Configure players. Types: human, smart, random");
        Player white = createPlayer(sc, board, display, Color.WHITE, "White");
        Player black = createPlayer(sc, board, display, Color.BLACK, "Black");
        play(board, display, white, black);
        sc.close();
    }

    private static Player createPlayer(Scanner sc, Board board, BoardDisplay display, Color color, String defaultName)
    {
        String side = (color == Color.WHITE) ? "White" : "Black";
        System.out.print(side + " player type (human/smart/random) [human]: ");
        String type = sc.nextLine().trim().toLowerCase();
        if (type.isEmpty())
        {
            type = "human";
        }
        System.out.print(side + " player name [" + defaultName + "]: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty())
        {
            name = defaultName;
        }
        switch (type)
        {
            case "smart":
                return new SmartPlayer(board, name, color);
            case "random":
                return new RandomPlayer(board, name, color);
            case "human":
            default:
                return new HumanPlayer(board, name, color, display);
        }
    }

    /**
     * Gets and
     * executes the next
     * turn for a given player,
     * lighitng up the board.
     * 
     * @param board the board the turn is executed on
     * @param display the display that shows the turn lit up
     * @param player the player whose turn is being executed
     */
    private static void nextTurn(Board board, BoardDisplay display, Player player)
    {
        try
        {
            Thread.sleep(500);
            if (player instanceof HumanPlayer)
            {
                String side = (player.getColor() == Color.WHITE) ? "White" : "Black";
                System.out.println(side + " player's turn: " + player.getName());
            }
            display.setTitle(player.getName());
            Move move = player.nextMove();
            board.executeMove(move);
            display.clearColors();
            display.setColor(move.getSource(), Color.YELLOW);
            display.setColor(move.getDestination(), Color.YELLOW);
        }
        catch (InterruptedException e)
        {
        }
    }

    /*private static boolean hasLost(Board board, Color color)
    {
        return (board.allMoves(color).size() == 0);
    }*/

    /**
     * Plays chess by
     * repeatedly calling
     * each player for a turn.
     * 
     * @param board the board to be played on
     * @param display the display to show the game
     * @param white the player with white pieces
     * @param black the player with black pieces
     */
    public static void play(Board board, BoardDisplay display, Player white, Player black)
    {
        while (board.isKingInGrid(Color.WHITE) && board.isKingInGrid(Color.BLACK) && board.anyPiecesInGrid(Color.WHITE) && board.anyPiecesInGrid(Color.BLACK))
        {
            nextTurn(board, display, white);
            nextTurn(board, display, black);
        }
        System.out.println("Game over");
        System.exit(0);
        /*while (!(hasLost(board, Color.WHITE) || hasLost(board, Color.BLACK)))
        {
            nextTurn(board, display, white);
            if (hasLost(board, Color.BLACK))
            {
                System.out.println("checkmate!");
            }
            else if (board.inCheck(Color.BLACK))
            {  
                System.out.println("check!");
            }
            nextTurn(board, display, black);
            if (hasLost(board, Color.WHITE))
            {
                System.out.println("checkmate!");
            }
            else if (board.inCheck(Color.WHITE))
            {
                System.out.println("check!");
            }
        }*/
    }
}