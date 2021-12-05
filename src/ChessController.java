import java.util.ArrayList;
import java.util.LinkedList;

public class ChessController {
    Chessboard board;
    // To Block the last failed attempts
    Chessboard deadQueens;
    // logging the queen positions
    LinkedList<int[]> queenLog = new LinkedList<>();
    // Set debug to true if you want to see the algorithm details in the log
    boolean debug = true;

    public static void main(String[] args) {
        ChessController cs = new ChessController();
        cs.start();
    }

    public void start () {
        board = new Chessboard();
        deadQueens = new Chessboard();

        int currX = 0;
        int backTraceColumn;

        while (currX < board.width) {
            log("current x: " + currX);
            int collumnState = processColumn(currX);
            log("collumn " + currX + " state: " + collumnState);
            if (debug) board.print();

            // If no success, remove the last queen and try again
            if (collumnState == 1) {
                currX++;
            } else {
                backTraceColumn = getLastColumnWithAvailableFields();
                int revertSteps = currX-backTraceColumn;
                log("cleaning board from column " + backTraceColumn + "(Reverting last " + revertSteps + " steps");
                for (int i = 0; i < revertSteps; i++) {
                    int oldX = queenLog.getLast()[0];
                    int oldY = queenLog.getLast()[1];
                    board.removeQueen(oldX, oldY);
                    deadQueens.blockField(oldX, oldY);
                    queenLog.removeLast();
                    log("backtraced (" + currX + ")! removed queen " + oldX + " " + oldY);
                }

                // If none of the empty fields in the collumn worked, reset dead queens and start one collum earlier
                if (collumnState == -1) {
                    log("resetting dead queens on column " + currX);
                    deadQueens.resetColumn(currX);
                }

                currX = getLastQueenColumn() + 1;

                if (debug) board.print();
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
    public int processColumn (int x) {
        int availableY = -1;
        int currY = 0;
        // Check if there is an empty/available field in the collumn
        while (availableY == -1 && currY < board.height) {
            if (board.isEmpty(x, currY) && deadQueens.isEmpty(x, currY)) {
                availableY = currY;
            }
            currY++;
        }
        if (availableY != -1) {
            setQueen(x, availableY);
            return 1; // 1 = Queen successfully set
        }

        // Check if there are empty fields but are occupied with dead queens
        while (currY < board.width) {
            if (board.isEmpty(x, currY)) {
               return 0; // 0 = empty fields but blocked by dead queens
            }
        }
        // There are no available fields also without dead queens
        return -1;
    }

    /**
     * Gets the last column with available fields -> used to get backtrace step value
     */
    public int getLastColumnWithAvailableFields() {
        int rowIndex = 0;
        for (int x = 0; x < board.width; x++) {
            if (board.hasColumnQueen(x) && board.hasColumnEmptyFields(x)) {
                rowIndex = x;
            }
        }
        return rowIndex;
    }

    /**
     * Get last column with queen on
     * @return
     */
    public int getLastQueenColumn() {
        int rowIndex = 0;
        for (int x = 0; x < board.width; x++) {
            if (board.hasColumnQueen(x)) {
                rowIndex = x;
            }
        }
        return rowIndex;
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
