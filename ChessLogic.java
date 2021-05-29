import java.awt.Color;
import java.io.*;
import java.util.*;

/**
 * Class that contains mostly the game logic.
 * 
 * @author Yan Xue, Yi Xiang, Ruiwen, Wei Lun
 */
public class ChessLogic {

    private Board board;
    private BoardFrame boardFrame;

    /**
     * Constructor for {@link ChessLogic} class
     * 
     * @param board      private {@link Board} that is in play
     * @param boardFrame private {@link BoardFrame} that contains {@link #board}
     */
    ChessLogic(Board board, BoardFrame boardFrame)
    {
        this.board = board;
        this.boardFrame = boardFrame;
    }

    /**
     * Method that retrieves {@link #board}
     * 
     * @return private {@link Board} {@link #board}
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Method that sets the {@link #board}
     * 
     * @param board
     */
    public void setBoard(Board board)
    {
        this.board = board;
    }

    /**
     * Method that sets the {@link #boardFrame}
     * 
     * @param frame private {@link BoardFrame} that contains {@link #board}
     */
    public void setFrame(BoardFrame frame)
    {
        this.boardFrame = frame;
    }
    
    /**
     * Method that runs after the first box selection
     * 
     * @param current the selected starting {@link Box}
     * @return boolean value to check if the selected starting {@link Box} is valid
     */
    public boolean startMove(Box current)
    {
        if (!board.getColorTurn())
        {
            boardFrame.highlightBox(current.getRank(), current.getFile());
        }
        else
        {
            boardFrame.highlightBox(7 - current.getRank(), 6 - current.getFile());
        }
        if (moveVerify(current, board.getColorTurn()))
        {
            return true;
        }
        updateVisuals();
        return false;
    }

    /**
     * Method that runs after the second successful box selection and completes a set of events for a move
     * 
     * @param current the selected starting {@link Box}
     * @param end     the selected destination {@link Box}
     * @return boolean value to check if the selected destination {@link Box} is
     *         valid
     */
    public boolean endMove(Box current, Box end)
    {
        if (!teleportPiece(current, end)) { //if the move is invalid, return false and the rest of the sequence doesn't run
            boardFrame.invalidMove();
            updateVisuals();
            return false;
        }
        board.setTurnCount(board.getTurnCount() + 1);
        board.setColorTurn(!board.getColorTurn());
        if (((board.getTurnCount() - 1) % 4) == 0)
        {
            switchTriPlus(true);
            switchTriPlus(false);
        }
        if (board.getWin())
        {
            flipBoard();
            updateVisuals();
            if (!board.getColorTurn())
            {
                boardFrame.gameOver("Red");
                //stops board flipping after game ends
                return false;
            }
            else
            {
                boardFrame.gameOver("Blue");
                //stops board flipping after game ends
                return false;            
            }
        }
        flipBoard();
        return true;
        
    }

    /**
     * Method that handles the board flipping
     */
    public void flipBoard() {
        Box[][] newBoard = new Box[8][7]; //reversing the 2D array 
        for (int i=0; i<8; i++) {
            for (int j=0;j<7;j++) {
                newBoard[i][j] = board.getChessBoxes()[7-i][6-j];
            }
        }
        for (int i=0; i<8; i++) {
            for (int j=0;j<7;j++) {
                board.getChessBoxes()[i][j] = newBoard[i][j];
            }
        }
    }


    /**
     * Method that checks if a move is valid
     * 
     * @param current the selected starting {@link Box}
     * @param isRed   boolean value that determines if the {@link Piece} is red
     * @return boolean value that determines if the move is valid
     */
    public boolean moveVerify(Box current, boolean isRed)
    {
        if (current.getPiece() == null || current.getPiece().isRed != isRed)
        {
            return false;
        }
        return true;
    }
    
    /**
     * Method that handles the moving of pieces
     * 
     * @param current the selected starting {@link Box}
     * @param end     the selected destination {@link Box}
     * @return boolean value that determines if a move is done
     */
    public boolean teleportPiece(Box current, Box end)
    {
        int lengthy = end.getRank() - current.getRank();
        int lengthx = end.getFile() - current.getFile();

        Box[][] inbetween = new Box[Math.abs(lengthy) + 1][Math.abs(lengthx) + 1]; 
        if (board.getColorTurn())
        {
            flipBoard();
        }
        for (int i = 0; i <= Math.abs(lengthy); i++) //Gets the boxes within an area between a piece's current position and destination
        {
            for (int j = 0; j <= Math.abs(lengthx); j++)
            {
                inbetween[i][j] = board.getChessBoxes()[current.getRank() + i * (int)Math.signum(lengthy)][current.getFile() + j * (int)Math.signum(lengthx)];
            }
        }
        if (board.getColorTurn())
        {
            flipBoard();
        }

        //Checks for empty box and valid piece movement; if false, the piece does not move
        if (!current.getPiece().movePiece(current, end, inbetween))
        {
            return false;
        }

        //Perform a win check everytime a move occurs
        board.setWin(checkWinCondition(end));

        //Moves the pieces into place
        //any pieces that aren't of the same color will be "eaten" (which just means replaced)
        end.setPiece(current.getPiece());
        current.setPiece(null);
        return true;
    }

    /**
     * Method that handles the transformation of {@link Triangle} and {@link Plus}
     * 
     * @param isRed boolean value that determines if the piece is red
     */
    public void switchTriPlus(boolean isRed)
    {
        for (Box[] boxFile : board.getChessBoxes())
        {
            for (Box box : boxFile)
            {
                if (box.getPiece() != null)
                {
                    if (box.getPiece().getClass().getSimpleName() == "Triangle" && box.getPiece().isRed == isRed)
                    {
                        box.setPiece(new Plus(isRed));
                    }
                    else if (box.getPiece().getClass().getSimpleName() == "Plus" && box.getPiece().isRed == isRed)
                    {
                        box.setPiece(new Triangle(isRed));
                    }
                }
            }
        }
    }

    /**
     * Method that checks the win condition
     * 
     * @param end the selected destination {@link Box}
     * @return boolean value that determines if a team has won
     */
    public boolean checkWinCondition(Box end)
    {
        if (end.getPiece() != null)
        {
            if (end.getPiece().getClass().getSimpleName() == "Sun") //Checks if the piece eaten is Sun
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Method that starts a new game
     */
    public void newGame()
    {
        board.setTurnCount(1);
        board.setColorTurn(false);
        board.setWin(false);

        //rank = rows, file = cols.
        for (int rank = 0; rank < 8; rank++)
        {
            for (int file = 0; file < 7; file++)
            {
                if ((rank == 1 || rank == 6) && file % 2 == 0)
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, new Arrow(rank < 4, false));
                }
                else if ((rank == 0 || rank == 7) && (file == 0 || file == 6))
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, new Plus(rank < 4));
                }
                else if ((rank == 0 || rank == 7) && (file == 1 || file == 5))
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, new Triangle(rank < 4));
                }
                else if ((rank == 0 || rank == 7) && (file == 2 || file == 4))
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, new Chevron(rank < 4));
                }
                else if ((rank == 0 || rank == 7) && file == 3)
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, new Sun(rank < 4));
                }
                else
                {
                    board.getChessBoxes()[rank][file] = new Box(file, rank, null);
                }
            }
        }
    }

    /**
     * Method that updates the GUI
     */
    public void updateVisuals()
    {
        for (int i = 0; i< 8; i++) { 
            for (int j = 0; j < 7; j++) {
                if (board.getChessBoxes()[i][j].getPiece() != null) {
                    boardFrame.getBtn(i,j).setIcon(board.getChessBoxes()[i][j].getPiece().loadImage(board.getColorTurn()));
                }
                else
                {
                    boardFrame.getBtn(i,j).setIcon(null);
                }
                boardFrame.getBtn(i, j).setBackground(Color.WHITE);;
            }
        }
    }

    /**
     * Method that handle the saving of a game
     * 
     * @param board the {@link Board} that is in play
     */
    public void saveGame(Board board)
    {
        File file = new File("savedGame.txt");

        try
        {
            PrintWriter fos = new PrintWriter(file);
            if (board.getColorTurn()) flipBoard();
            fos.println(board.getTurnCount() + " " + board.getColorTurn());
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 7; x++){
                    if(board.getChessBoxes()[y][x].getPiece() != null){
                        fos.println(x + " " + y + " " + board.getChessBoxes()[y][x].getPiece().toString());
                    } else{
                        fos.println(x + " " + y + " " + "null");
                    }
                }
            }
            if (board.getColorTurn()) flipBoard();
            fos.close();
            boardFrame.saveSuccess();
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Method that handle the loading of a game
     */
    public void loadGame()
    {
        File file = new File("savedGame.txt");
        Scanner scan = null;
        int x = 0;
        int y = 0;
        try
        {
            scan = new Scanner(file);
            newGame();
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                String[] arrline = line.split(" "); 
                if (arrline.length == 2){
                    board.setTurnCount(Integer.parseInt(arrline[0]));
                    board.setColorTurn(Boolean.parseBoolean(arrline[1]));
                    board.setWin(false);
                } else if(arrline.length == 3){
                    if(x == 7){
                        x = 0;
                        y = y + 1;
                    }
                    board.getChessBoxes()[y][x].setFile((Integer.parseInt(arrline[0])));
                    board.getChessBoxes()[y][x].setRank((Integer.parseInt(arrline[1])));
                    board.getChessBoxes()[y][x].setPiece(null);
                    x = x + 1;
                }
                else if (arrline.length >= 4) {
                    if(x == 7){
                        x = 0;
                        y = y + 1;
                    }                    
                    board.getChessBoxes()[y][x].setFile((Integer.parseInt(arrline[0])));
                    board.getChessBoxes()[y][x].setRank((Integer.parseInt(arrline[1])));
                    if(arrline[3].equals("Arrow"))
                        board.getChessBoxes()[y][x].setPiece(new Arrow(Boolean.parseBoolean(arrline[2]), Boolean.parseBoolean(arrline[4])));
                    else if(arrline[3].equals("Plus"))
                        board.getChessBoxes()[y][x].setPiece(new Plus(Boolean.parseBoolean(arrline[2])));
                    else if(arrline[3].equals("Triangle"))
                        board.getChessBoxes()[y][x].setPiece(new Triangle(Boolean.parseBoolean(arrline[2])));
                    else if(arrline[3].equals("Chevron"))
                        board.getChessBoxes()[y][x].setPiece(new Chevron(Boolean.parseBoolean(arrline[2])));
                    else if(arrline[3].equals("Sun"))
                        board.getChessBoxes()[y][x].setPiece(new Sun(Boolean.parseBoolean(arrline[2])));
                    x = x + 1;
                }
            }
            if (board.getColorTurn()) flipBoard();
            scan.close();
            boardFrame.loadSuccess();
        }
        catch (FileNotFoundException e)
        {
            boardFrame.loadFail();
        }
    }
}