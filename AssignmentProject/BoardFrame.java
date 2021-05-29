import java.awt.*;
import javax.swing.*;

/**
 * Class of the GUI frame.
 * 
 * @author Yi Xiang, Yan Xue, Wei Lun, Ruiwen, Andrew Low
 */
public class BoardFrame {
    private JFrame gameFrame;
    private JButton[][] playbtn = new JButton[8][7];
    private JButton nGame = new JButton("New Game");
    private JButton sGame = new JButton("Save Game");
    private JButton lGame = new JButton("Load Game");

    /**
     * Constructor of {@link BoardFrame}
     * 
     * @param board      2D Array of {@link Box}
     * @param isblueturn boolean value that determines if it's blue's turn
     */
    BoardFrame(Box[][] board, boolean isblueturn) 
    { 
        gameFrame = new JFrame("Webale Chess");
        final JPanel gameChoices = new JPanel(new FlowLayout());
        final JPanel b = new JPanel(new GridLayout(8,7));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                playbtn[i][j] = new JButton();
                playbtn[i][j].setActionCommand(Integer.toString(i*7+j));
                if (board[i][j] != null)
                {
                    if (board[i][j].getPiece() != null) {
                        playbtn[i][j].setIcon(board[i][j].getPiece().loadImage(isblueturn));
                    }
                }
                b.add(playbtn[i][j]);
            }
        }
        gameFrame.add(gameChoices, BorderLayout.NORTH);
        gameChoices.add(nGame);
        gameChoices.add(sGame);
        gameChoices.add(lGame);
        gameFrame.add(b, BorderLayout.CENTER);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);   
        gameFrame.setSize(1024,720);     
    }
    
    /**
     * Method to get the specific button in the given coordinates
     * 
     * @param i row location
     * @param j column location
     * @return private {@link JButton} {@link #playbtn}
     */
    public JButton getBtn(int i,int j) {
        return playbtn[i][j];
    }

    /**
     * Method to get the New Game button
     * 
     * @return private {@link JButton} {@link #nGame}
     */
    public JButton getNewBtn(){
        return nGame;
    }

    /**
     * Method to get the Save Game button
     * 
     * @return private {@link JButton} {@link #sGame}
     */
    public JButton getSaveBtn(){
        return sGame;
    }

    /**
     * Method to get the Load Game button
     * 
     * @return private {@link JButton} {@link #lGame}
     */
    public JButton getLoadBtn(){
        return lGame;
    }

    /**
     * Method that shows a message dialog when game is over
     * 
     * @param winTeam String that tells which side wins
     */
    public void gameOver(String winTeam) {
        JOptionPane.showMessageDialog(gameFrame, winTeam + " wins! Please start a new game.", "Game Over", 1);
    }

    /**
     * Method that shows a message dialog when game is saved
     */
    public void saveSuccess(){
        JOptionPane.showMessageDialog(gameFrame, "Your game has been saved successfully!", "Game saved!", 1);
    }

    /**
     * Method that shows a message dialog when game loads succesfully
     */
    public void loadSuccess(){
        JOptionPane.showMessageDialog(gameFrame, "Your game has been loaded successfully!", "Game loaded!", 1);
    }

    /**
     * Method that shows a message dialog when game failed to load
     */
    public void loadFail(){
        JOptionPane.showMessageDialog(gameFrame, "Save file not found!", "Failed to load game!", 0);
    }

    /**
     * Method that shows a message dialog when an invalid move is done
     */
    public void invalidMove(){
        JOptionPane.showMessageDialog(gameFrame, "Such piece movement is not possible", "Invalid move!", 0);
    }

    /**
     * Method that highlights the selected box on the GUI
     * 
     * @param i row location
     * @param j column location
     */
    public void highlightBox(int i, int j)
    {
        playbtn[i][j].setBackground(Color.GREEN);
    }
}