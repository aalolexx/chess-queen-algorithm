import java.util.LinkedList;

public class ChessController {
    Chessboard board;
    // logging the queen positions
    LinkedList<int[]> queenLog = new LinkedList<>();
    int currX = 0;

    public static void main(String[] args) {
        ChessController cs = new ChessController();
        cs.start();
    }

    public void start () {
        board = new Chessboard();
        for (currX = 0; currX < board.width; currX++) {
            board.print();
            processColumn(currX);
        }
    }

    /**
     * Checks the next available field for a queen in a given column
     * @param x
     */
    public void processColumn (int x) {
        int availableY = -1;
        int currY = 0;
        // Check if there is an empty/available field in the collumn
        while (availableY == -1 && currY < board.width) {
            if (board.isEmpty(x, currY)) {
                availableY = currY;
            }
            currY++;
        }
        // If available continue, else backtrace algorithm
        if (availableY != -1) {
            setQueen(x, availableY);
        } else {
            // TODO
        }
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
            queenLog.push(new int[]{x,y});
        } else {
            System.out.println("Field " + x + " " + y + " is already blocked");
        }

        // Block all reachable fields
        board.blockFromField(x, y);
    }
}
