public class ChessController {
    Chessboard board;

    public static void main(String[] args) {
        ChessController cs = new ChessController();
        cs.start();
    }

    public void start () {
        board = new Chessboard();
        board.print();
        setQueen(3,3);
        board.print();
    }

    /**
     * Sets a queen to the chess board and blocks all reachable fields
     * @param x
     * @param y
     */
    public void setQueen(int x, int y) {
        // Set the queen
        if (board.get(x, y) == 0) {
            board.setQueen(x, y);
        } else {
            System.out.println("Field " + x + " " + y + " is already blocked");
        }

        // Block all reachable fields
        board.blockFromField(x, y);
    }
}
