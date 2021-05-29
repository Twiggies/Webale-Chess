/**
 * Class for the boxes in {@link Board}
 * 
 * @author Yi Xiang, Wei Lun
 */
public class Box {
    private Piece piece;
    private int file;
    private int rank;

    /**
     * Constructor to construct {@link Box}
     * 
     * @param file  file (coloumn) value of the box in the {@link Board}
     * @param rank  rank (row) value of the box in the {@link Board}
     * @param piece {@link Piece} to be put in the box
     */
    public Box(int file, int rank, Piece piece){
        this.setPiece(piece);
        this.setFile(file);
        this.setRank(rank);
    }

    /**
     * Method to get {@link #piece} in the box
     * 
     * @return private {@link Piece} {@link #piece}
     */
    public Piece getPiece(){
        return this.piece;
    }

    /**
     * Method to put the desired {@link Piece} in the box
     * 
     * @param p {@link Piece} to be put in the box
     */
    public void setPiece(Piece p){
        this.piece = p;
    }

    /**
     * Method to get the {@link #file} value of the box
     * 
     * @return private int {@link #file}
     */
    public int getFile(){
        return this.file;
    }

    /**
     * Method to set the {@link #file} value of the box
     * 
     * @param file file value of the box in the {@link Board}
     */
    public void setFile(int file){
        this.file = file;
    }

    /**
     * Method to get the {@link #rank} value of the box
     * 
     * @return private int {@link #rank}
     */
    public int getRank(){
        return this.rank;
    }

    /**
     * Method to set the {@link #rank} value of the box
     * 
     * @param rank rank value of the box in the {@link Board}
     */
    public void setRank(int rank){
        this.rank = rank;
    }
}