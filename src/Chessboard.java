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

    public int get(int x, int y) {
        return board[x][y];
    }

    private void set(int x, int y, int value) {
        board[x][y] = value;
    }

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
