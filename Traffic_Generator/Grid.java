/**
 * The Grid class creates an empty grid of Cell objects as a 2D array
 * Cells can be accessed through the getter
 */
public class Grid {

    /**
     * Instance variables
     */
    private int nRows, nCols;
    private Cell[][] cellArray; // The array of Cell objects


    /**
     * Constructor
     *
     * @param rows
     * @param cols
     */
    public Grid(int rows, int cols) {
        this.nRows = rows;
        this.nCols = cols;
        createEmptyGrid(rows, cols);
    }

    /**
     * Method to create a new empty grid
     *
     * @param r rows
     * @param c columns
     */
    private void createEmptyGrid(int r, int c) {
        cellArray = new Cell[c][r];
        for (int x = 0; x < c; x++) {
            for (int y = 0; y < r; y++) {
                this.cellArray[x][y] = new Cell();
            }
        }
    }


    /**
     * Getters
     */
    public int getRows() {
        return this.nRows;
    }

    public int getCols() {
        return this.nCols;
    }

    public Cell getCellAt(int x, int y) {
        return cellArray[x][y];
    }

    public Cell[][] getCellArray() {
        return this.cellArray;
    }
}


	



