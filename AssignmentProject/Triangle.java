import java.awt.*;
import javax.swing.*;

/**
 * Triangle is a subclass extended from {@link Piece}
 * 
 * @author Yi Xiang, Yan Xue
 */
public class Triangle extends Piece {
    /**
     * Constructor to create a {@link Triangle} {@link Piece}
     * 
     * @param isRed boolean value that determines if the piece is red or not
     */
    public Triangle(boolean isRed) {
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
        if (horizontalChange == verticalChange) {
            for (int i = 1; i < inbetween.length - 1; i++) {
                for (int j = 1; j < inbetween[0].length - 1; j++) {
                    if (i == j) {
                        if (inbetween[i][j].getPiece() != null) {
                            return false;
                        }
                    }
                }
            }
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
                image = new ImageIcon(this.getClass().getResource("/Pieces/Triangle.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/Triangle" + "Up.png")).getImage();
            }
        } else {
            if (!isBlueTurn) {
                image = new ImageIcon(this.getClass().getResource("/Pieces/TriangleR.png")).getImage();
            } else {
                image = new ImageIcon(this.getClass().getResource("/Pieces/TriangleR" + "Up.png")).getImage();
            }
        }
        Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
