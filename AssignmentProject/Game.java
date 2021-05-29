import java.awt.event.*;

/**
 * Game is the main class that creates the game instance.
 * 
 * @author Ruiwen, Yi Xiang
 */
public class Game implements ActionListener {
    private int startRow, startCol; //these two are used to save the row,col position of the start move (first click of a move)
    private int endRow, endCol;
    private boolean counter;

    private BoardFrame boardFrame;
    private Board board;
    private ChessLogic move;

    /**
     * Constructor for {@link Game}
     */
    Game() 
    {
        board = new Board();
        move = new ChessLogic(board, boardFrame);
        boardFrame = new BoardFrame(board.getChessBoxes(), board.getColorTurn());

        for (int i=0; i<8; i++)  {
            for (int j=0;j<7; j++) {
                boardFrame.getBtn(i, j).addActionListener(this);
            }
        }

        boardFrame.getNewBtn().addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent evt){         
                move.newGame();
                move.setBoard(board);
                move.setFrame(boardFrame);
                move.updateVisuals();
            }
        });

        boardFrame.getSaveBtn().addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent evt){
                if (board.getChessBoxes()[0][0] != null && !board.getWin())
                {
                    move.saveGame(board);
                    move.updateVisuals();
                }
            }
        });

        boardFrame.getLoadBtn().addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent evt){
                move.setFrame(boardFrame);
                move.loadGame();
                counter = false;
                board = move.getBoard();
                if (board.getChessBoxes()[0][0] != null && !board.getWin())
                {
                    move.updateVisuals();
                }
            }
        });
    }    

    

    /**
     * Overriden method from {@link ActionListener}. Manages the GUI as the game
     * goes.
     * 
     * @param evt ActionEvent
     */
    public void actionPerformed(ActionEvent evt) {
        //Move playeMove
        if (!board.getWin() && board.getChessBoxes()[0][0] != null)
        {
            if (!counter) {
                int pos = Integer.parseInt(evt.getActionCommand());
                startRow = pos/7; //calc the position of the first click
                startCol = pos%7; 
                if (move.startMove(board.getChessBoxes()[startRow][startCol])) //if the startMove is valid
                {
                    counter = true;
                }
            }
            else {
                int pos = Integer.parseInt(evt.getActionCommand());
                endRow = pos/7;
                endCol = pos%7;
                if (move.endMove(board.getChessBoxes()[startRow][startCol], board.getChessBoxes()[endRow][endCol])) { //check if valid
                    counter = false;
                    move.updateVisuals(); //if valid, then it will update the board.
                }
                counter = false;
            }
        }
    }

    
    /**
     * Main function
     * 
     * @param args
     */
    public static void main (final String[] args) {
        new Game();
    }
}