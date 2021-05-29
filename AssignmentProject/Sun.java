import java.awt.*;
import javax.swing.*;

/**
 * Sun is a subclass extended from {@link Piece}
 * 
 * @author Yi Xiang, Wei Lun
 */
public class Sun extends Piece {
    /**
     * Constructor to create a {@link Sun} {@link Piece}
     * 
     * @param isRed boolean value that determines if the piece is red or not
     */
    public Sun(boolean isRed) {
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
        if (horizontalChange + verticalChange == 1) {
            return true;
        } else if (horizontalChange == 1 && verticalChange == 1) {
            return true;
        }
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
                image = new ImageIcon(this.getClass().getResource("/Pieces/Sun.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/Sun" + "Up.png")).getImage();
            }
        } else {
            if (!isBlueTurn) {
                image = new ImageIcon(this.getClass().getResource("/Pieces/SunR.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/SunR" + "Up.png")).getImage();
            }
        }
        Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
