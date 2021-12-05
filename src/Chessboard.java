public class Chessboard {
    public static int QUEEN = 9;
    public static int BLOCKED = 1;
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
    }

    /**
     * Blocks the row and the diagonals from a given field
     * @param x
     * @param y
     */
    public void blockFromField(int x, int y) {
        // block row
        for (int xi = (x+1); xi < width; xi++) {
            board[xi][y] = Chessboard.BLOCKED;
        }

        // block diagonal up
        int yUp = y-1;
        int yDown = y+1;
        for (int xi = (x+1); xi < width; xi++) {
            if (yUp >= 0) board[xi][yUp] = Chessboard.BLOCKED;
            if (yDown < height) board[xi][yDown] = Chessboard.BLOCKED;
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
                System.out.print(board[x][y] + "  ");
            }
            System.out.println();
        }
    }
}
