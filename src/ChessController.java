import java.util.ArrayList;
import java.util.LinkedList;

public class ChessController {
    Chessboard board;
    // To Block the last failed attempt
    int[] deadQueen;
    // logging the queen positions
    LinkedList<int[]> queenLog = new LinkedList<>();
    // Set debug to true if you want to see the algorithm details in the log
    boolean debug = false;

    public static void main(String[] args) {
        ChessController cs = new ChessController();
        cs.start();
    }

    public void start () {
        board = new Chessboard();
        int currX = 0;
        int currMaxX = 0;
        int backTraceValue = 1;

        while (currX < board.width) {
            log("current x: " + currX);
            boolean success = processColumn(currX);
            if (debug) board.print();
            // If no success, remove the last queen and try again
            if (!success) {
                deadQueen = null;
                log("Cleard dead queen");
                for (int i = 0; i < (currMaxX - backTraceValue); i++) {
                    int oldX = queenLog.getLast()[0];
                    int oldY = queenLog.getLast()[1];
                    board.removeQueen(oldX, oldY);
                    deadQueen = new int[]{oldX, oldY};
                    log("Dead Queen = " + oldX + " " + oldY);
                    queenLog.removeLast();
                    log("backtraced (" + currX + ")! removed queen " + oldX + " " + oldY);
                }
                log("backtracing steps: " + currMaxX + " | " + backTraceValue + " -> " + (currMaxX - backTraceValue));
                if (debug) board.print();
                currX -= (currMaxX - backTraceValue);
                backTraceValue++;
            } else {
                currX++;
                if (currMaxX < currX) currMaxX = currX;
            }
            // If backtracing reaches 0 something went wrong
            if (currX == 0) {
                System.out.println("couldnt solve the riddle... SORRY :(");
                break;
            }
        }

        // Riddle solved!
        System.out.println("RIDDLE SOLVED");
        board.prettyPrint();
    }

    /**
     * Checks the next available field for a queen in a given column
     * @param x
     * @return if there was an available field for queen
     */
    public boolean processColumn (int x) {
        int availableY = -1;
        int currY = 0;
        // Check if there is an empty/available field in the collumn
        while (availableY == -1 && currY < board.width) {
            if (board.isEmpty(x, currY)
                && (deadQueen == null || !(deadQueen[0] == x && deadQueen[1] == currY))) {
                availableY = currY;
            }
            currY++;
        }
        // If available continue, else backtrace algorithm
        if (availableY != -1) {
            setQueen(x, availableY);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets a queen to the chess board and blocks all reachable fields
     * @param x
     * @param y
     */
    public void setQueen(int x, int y) {
        // Set the queen
        if (board.isEmpty(x, y)) {
            board.setQueen(x, y);
            queenLog.add(new int[]{x,y});
        } else {
            System.out.println("Field " + x + " " + y + " is already blocked");
        }
    }

    /**
     * Log something if debug mode is on
     * @param msg
     */
    public void log(String msg) {
        if (debug) {
            System.out.println(msg);
        }
    }
}
