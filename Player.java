import java.awt.Color;

/**
 * Represents a
 * player in a chess game.
 * 
 * @author Linda Zeng
 * @version 4.9.23
 */
public abstract class Player {
    private Board board; //board the player is playing in
    private String name; //name of player
    private Color color; //color player is playing

    /**
     * Constructs a player with given attributes.
     * 
     * @param boardP    board to play on
     * @param nameP     name of player
     * @param colorP    color to play
     */
    public Player(Board boardP, String nameP, Color colorP)
    {
        board = boardP;
        name = nameP;
        color = colorP;
    }

    /**
     * Returns board of player
     * 
     * @return board player is playing on
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns name of player
     * 
     * @return String name of player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns color player
     * is on.
     * 
     * @return color of player
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Returns next move the 
     * player will play
     * 
     * @return player's next move
     */
    public abstract Move nextMove();
}
