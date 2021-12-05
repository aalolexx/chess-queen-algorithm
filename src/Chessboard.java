public class Chessboard {
    public static int QUEEN = -9;
    public static int BLOCKED_UNIT = 1; // The higher the number the more queens are blocking the field
    public static int DEBLOCK_UNIT = -1;
    public static int EMPTY = 0;

    int width = 8;
    int height = 8;

    int board[][] = new int[8][8];

    public Chessboard() {
        for (int x = 0; x < width; x++) {
            board[x] = new int[height];
            for (int y = 0; y < height; y++) {
                board[x][y] = Chessboard.EMPTY;
            }
        }
    }

    /**
     * Get Figure on x y chessboard field
     * @param x
     * @param y
     * @return
     */
    public int get(int x, int y) {
        return board[x][y];
    }

    /**
     * Checks if field is empty
     * @param x
     * @param y
     * @return
     */
    public boolean isEmpty(int x, int y) {
        return board[x][y] == Chessboard.EMPTY;
    }

    /**
     * Set Figure on x y chessboard field
     * @param x
     * @param y
     * @param value
     */
    private void set(int x, int y, int value) {
        board[x][y] = value;
    }

    /**
     * Set the queen on x y chessboard field
     * @param x
     * @param y
     */
    public void setQueen(int x, int y) {
        set(x, y, Chessboard.QUEEN);
        this.changeFromField(x, y, Chessboard.BLOCKED_UNIT);
    }

    /**
     * Removes the queen on x y chessboard field
     * @param x
     * @param y
     */
    public void removeQueen(int x, int y) {
        set(x, y, Chessboard.EMPTY);
        this.changeFromField(x, y, Chessboard.DEBLOCK_UNIT);
    }

    /**
     * Clears the whole board
     */
    public void clear() {
        for (int x = 0; x < width; x++) {
            board[x] = new int[height];
            for (int y = 0; y < height; y++) {
                board[x][y] = Chessboard.EMPTY;
            }
        }
    }

    /**
     * Blocks/Empties the row and the diagonals from a given field (From queen)
     * The higher the number of a field is the more queens are blocking the field
     * new State should either be 1 = one more queen is blocking the field or -1 = the queen is no longer blocking it
     * @param x
     * @param y
     */
    private void changeFromField(int x, int y, int newState) {
        // block row
        for (int xi = (x+1); xi < width; xi++) {
            board[xi][y] += newState;
        }

        // block diagonal up
        int yUp = y-1;
        int yDown = y+1;
        for (int xi = (x+1); xi < width; xi++) {
            if (yUp >= 0) board[xi][yUp] += newState;
            if (yDown < height) board[xi][yDown] += newState;
            yUp--;
            yDown++;
        }
    }

    /**
     * Print the chessboard to the console
     */
    public void print() {
        System.out.println("--------board---------");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (board[x][y] >= 0) {
                    System.out.print(" " + board[x][y] + " ");
                } else {
                    System.out.print(board[x][y] + " ");
                }
            }
            System.out.println();
        }
    }
}
