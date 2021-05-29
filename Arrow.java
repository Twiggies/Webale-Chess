import java.awt.*;
import javax.swing.*;

/**
 * Arrow is a subclass extended from {@link Piece}
 * 
 * @author Yi Xiang, Ruiwen, Andrew Low
 */
public class Arrow extends Piece {
    boolean isTurned = false;

    /**
     * Constructor to create a {@link Arrow} {@link Piece}
     * 
     * @param isRed    boolean value that determines if the piece is red or not
     * @param isTurned a flag that turns to 'true' when Arrow reach the end of the
     *                 board
     */
    public Arrow(boolean isRed, boolean isTurned) {
        super(isRed);
        this.isTurned = isTurned;
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

        int forwardVerticalChange = end.getRank() - current.getRank();
        int backwardVerticalChange = current.getRank() - end.getRank();
        int horizontalChange = Math.abs(current.getFile() - end.getFile());

        if (!this.checkRed()) {
            if (isTurned == false) {
                if (horizontalChange + forwardVerticalChange == forwardVerticalChange) {
                    if (forwardVerticalChange == -2 || forwardVerticalChange == -1) {
                        for (int i = 1; i < inbetween.length - 1; i++) {
                            if (inbetween[i][0].getPiece() != null) {
                                return false;
                            }
                        }
                        if (end.getRank() == 0) {
                            isTurned = true;
                        }
                        return true;
                    }
                }
            } else {
                if (horizontalChange + backwardVerticalChange == backwardVerticalChange) {
                    if (backwardVerticalChange == -2 || backwardVerticalChange == -1) {
                        for (int i = 1; i < inbetween.length - 1; i++) {
                            if (inbetween[i][0].getPiece() != null) {
                                return false;
                            }
                        }
                        if (end.getRank() == 7) {
                            isTurned = false;
                        }
                        return true;
                    }
                }
            }
        } else {
            if (isTurned == false) {
                if (horizontalChange + forwardVerticalChange == forwardVerticalChange) {
                    if (forwardVerticalChange == 2 || forwardVerticalChange == 1) {
                        for (int i = 1; i < inbetween.length - 1; i++) {
                            if (inbetween[i][0].getPiece() != null) {
                                return false;
                            }
                        }
                        if (end.getRank() == 7) {
                            isTurned = true;
                        }
                        return true;
                    }
                }
            } else {
                if (horizontalChange + backwardVerticalChange == backwardVerticalChange) {
                    if (backwardVerticalChange == 2 || backwardVerticalChange == 1) {
                        for (int i = 1; i < inbetween.length - 1; i++) {
                            if (inbetween[i][0].getPiece() != null) {
                                return false;
                            }
                        }
                        if (end.getRank() == 0) {
                            isTurned = false;
                        }
                        return true;
                    }
                }
            }
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
                if (isTurned) {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/Arrow" + "Up.png")).getImage();
                } else {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/Arrow.png")).getImage();
                }
            } else {
                if (isTurned) {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/Arrow.png")).getImage();
                } else {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/Arrow" + "Up.png")).getImage();
                }
            }
        } else {
            if (!isBlueTurn) {
                if (isTurned) {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/ArrowR" + "Up.png")).getImage();
                } else {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/ArrowR.png")).getImage();
                }
            } else {
                if (isTurned) {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/ArrowR.png")).getImage();
                } else {
                    image = new ImageIcon(this.getClass().getResource("/Pieces/ArrowR" + "Up.png")).getImage();
                }
            }
        }
        Image scaledImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    @Override
    public String toString() {
        return isRed + " " + getClass().getSimpleName() + " " + isTurned;
    }
}
