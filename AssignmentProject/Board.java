/**
 * Board is the game board.
 * 
 * @author Ruiwen, Andrew Low
 */
public class Board {
    private Box[][] chessBoxes = new Box[8][7];
    private boolean colorTurn;
    private boolean win;
    private int turnCount;

    /**
     * Method to retrieve {@link #chessBoxes}
     * 
     * @return private {@link Box} array {@link #chessBoxes}
     */
    public Box[][] getChessBoxes()
    {
        return chessBoxes;
    }

    /**
     * Method to retrieve {@link #colorTurn}
     * 
     * @return private boolean {@link #colorTurn}, where false indicates it's blue's
     *         turn, and true as red's turn
     */
    public boolean getColorTurn()
    {
        return colorTurn;
    }

    /**
     * Method to set {@link #colorTurn}
     * 
     * @param i boolean value where false indicates it's blue's turn, and true as
     *          red's turn
     */
    public void setColorTurn(boolean i)
    {
        colorTurn = i;
    }

    /**
     * Method to get {@link #win} value
     * 
     * @return private boolean {@link #win} that identifies if somebody win the game
     */
    public boolean getWin()
    {
        return win;
    }

    /**
     * Method to set if the game is won
     * 
     * @param i boolean value for {@link #win}
     */
    public void setWin(boolean i)
    {
        win = i;
    }

    /**
     * Method that retrieve value of {@link #turnCount}
     * 
     * @return private int {@link #turnCount}
     */
    public int getTurnCount()
    {
        return turnCount;
    }

    /**
     * Method that set the value of {@link #turnCount}
     * 
     * @param i int value for {@link #turnCount}
     */
    public void setTurnCount(int i)
    {
        turnCount = i;
    }
    
    
}