public class Chessboard {
    int width = 8;
    int height = 8;

    int board[][] = new int[8][8];

    public Chessboard() {
        for (int x = 0; x < width; x++) {
            board[x] = new int[height];
            for (int y = 0; y < height; y++) {
                board[x][y] = 0;
            }
        }
    }

    public void print() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                System.out.print(board[x][y] + "  ");
            }
            System.out.println();
        }
    }
}
