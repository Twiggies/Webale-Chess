import javax.swing.*;

/**
 * Piece is an abstract class that we will use to extend into different types of
 * pieces
 * 
 * @author Yi Xiang, Yan Xue, Wei Lun, Andrew Low, Ruiwen
 */
public abstract class Piece {
	boolean isRed = true;

	/**
	 * Constructor to create a {@link Piece}
	 * 
	 * @param isRed boolean value that determines if the piece is red or not
	 */
	public Piece(boolean isRed) {
		this.isRed = isRed;
	}
	
	/**
	 * Method to check if the selected piece is red
	 * 
	 * @return boolean {@link #isRed}
	 */
	public boolean checkRed() {
		return this.isRed;
	}
	
	/**
	 * Abstract method that check if move is valid
	 * 
	 * @param current   selected starting {@link Box}
	 * @param end       selected ending {@link Box}
	 * @param inbetween
	 */
	public abstract boolean movePiece(Box current, Box end, Box[][] inbetween);
	
	/**
	 * Abstract method that loads image of the piece
	 * 
	 * @param isBlueTurn boolean value that determines if it's blue's turn
	 */
	public abstract ImageIcon loadImage(boolean isBlueTurn);

	/**
	 * Returns the state of the piece as a string
	 */
	public String toString()
	{
		return isRed + " " + getClass().getSimpleName();
	}
}