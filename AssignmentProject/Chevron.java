import java.awt.*;
import javax.swing.*;

/**
 * Chevron is a subclass extended from {@link Piece}
 * 
 * @author Yi Xiang, Andrew Low, Yan Xue
 */
public class Chevron extends Piece {
    /**
     * Constructor to create a {@link Chevron} {@link Piece}
     * 
     * @param isRed boolean value that determines if the piece is red or not
     */
    public Chevron(boolean isRed) {
        super(isRed);
    }

    @Override
    /**
     * Method that check if move is valid
     * 
     * @param current   selected starting {@link Box}
     * @param end       selected ending {@link Box}
     * @param inbetween
     */
    public boolean movePiece(Box current, Box end, Box[][] inbetween) {
        if (end.getPiece() != null) {
            if (end.getPiece().checkRed() == this.checkRed()) {
                return false;
            }
        }
        int horizontalChange = Math.abs(current.getFile() - end.getFile());
        int verticalChange = Math.abs(current.getRank() - end.getRank());
        if (horizontalChange * verticalChange == 2)
            return true;
        return false;
    }

    @Override
    /**
     * Method that loads image of the piece
     * 
     * @param isBlueTurn boolean value that determines if it's blue's turn
     */
    public ImageIcon loadImage(boolean isBlueTurn) {
        Image image;
        if (!this.checkRed()) {
            if (isBlueTurn) {
                image = new ImageIcon(this.getClass().getResource("/Pieces/Chevron.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/Chevron" + "Up.png")).getImage();
            }
        } else {
            if (!isBlueTurn) {
                image = new ImageIcon(this.getClass().getResource("/Pieces/ChevronR.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/ChevronR" + "Up.png")).getImage();
            }
        }
        Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
