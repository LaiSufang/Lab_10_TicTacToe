import java.util.Scanner;

public class TicTacToe {

    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board [][] = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String rowMovePrompt = "Please enter the row number of your move: ";
        String colMovePrompt = "Please enter the column number of your move: ";
        String player;
        int rowMove;
        int colMove;
        int moveCnt;
        boolean isEnteredMoveValid;
        boolean isWinOrTie = false;

        do {
            // 1. Clear the board, move count to 0 and set the player to X (since X always moves first)
            clearBoard();
            moveCnt = 0;
            player = "X";
            // 2. Show the board, get the coordinates for the move which should be 1 – 3 for the row and col
            display();
            do {
                System.out.println("Player " + player + ":");
                do {
                    rowMove = SafeInput.getRangedInt(scanner, rowMovePrompt, 1, ROW);
                    colMove = SafeInput.getRangedInt(scanner, colMovePrompt, 1, COL);
                    // 3. convert the player move to the array indices (which are 0 – 2) by subtracting 1
                    rowMove--;
                    colMove--;
                    // 4. loop (through #2 and #3) until the converted player coordinates are a valid move
                    isEnteredMoveValid = isValidMove(rowMove,colMove);
                    if (!isEnteredMoveValid) {
                        System.out.println("Move has already been taken, please choose another move!");
                    }
                } while (!isEnteredMoveValid);

                // 5. Record the move on the board and increment the move counter
                board[rowMove][colMove] = player;
                moveCnt++;
                display();
                // 6. if appropriate check for a win or a tie (i.e. if it is possible for a win or a tie at this point in the game, check for it.)
                if(moveCnt > 4) {
                    if(isWin(player)) {
                        isWinOrTie = true;
                        System.out.println("Player " + player + " wins!");
                    }
                    else {
                        if(moveCnt > 6) {
                            if(isTie()) {
                                isWinOrTie = true;
                                System.out.println("Game is tie!");
                            }
                        }
                    }
                }
                // 8. Toggle the player for the next move (i.e. X becomes O, O becomes X)
                if(player.equals("X")) {
                    player = "O";
                }
                else {
                    player = "X";
                }
            } while (!isWinOrTie);
            // 7. If there is a win or tie announce it and then prompt the players to play again or exit.
            isWinOrTie = false; // reset game result for next game
        } while (SafeInput.getYNConfirm(scanner, "Do you want to play again[Y/N]?"));
    }

    private static void clearBoard() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                board[row][col] = " "; // make this cell a space
            }
        }
    }

    private static void display() {
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if(col < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col) {
        // is it a space?
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)) {
            return true;
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for(int col = 0; col < COL; col++) {
            if(board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false; // no col win
    }

    private static boolean isRowWin(String player) {
        for(int row = 0; row < ROW; row++) {
            if(board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false; // no row win
    }

    private static boolean isDiagonalWin(String player) {
        if(board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            return true;
        }
        else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            return true;
        }
        return false; // no diagonal win
    }

    private static boolean isTie() {
        boolean isTie = false;
        boolean hasX = false;
        boolean hasO = false;
        int eliminatedRows = 0;
        int eliminatedCols = 0;
        int eliminatedDiagonals = 0;

        // check all rows
        for(int row = 0; row < ROW; row++) {
            for(int col = 0; col < COL; col++) {
                if(board[row][col].equals("X")) {
                    hasX = true;
                }
                if(board[row][col].equals("O")) {
                    hasO = true;
                }
            }
            if (hasX && hasO) {
                eliminatedRows++;
            }
            // reset hasX and hasO for next row
            hasX = false;
            hasO = false;
        }
        // check all columns
        for(int col = 0; col < COL; col++) {
            for(int row = 0; row < ROW; row++) {
                if(board[row][col].equals("X")) {
                    hasX = true;
                }
                if(board[row][col].equals("O")) {
                    hasO = true;
                }
            }
            if (hasX && hasO) {
                eliminatedCols++;
            }
            // reset hasX and hasO for next column
            hasX = false;
            hasO = false;
        }
        // check both diagonals
        if((board[0][0].equals("X") || board[1][1].equals("X") || board[2][2].equals("X")) && (board[0][0].equals("O") || board[1][1].equals("O") || board[2][2].equals("O"))) {
            eliminatedDiagonals++;
        }
        if((board[0][2].equals("X") || board[1][1].equals("X") || board[2][0].equals("X")) && (board[0][2].equals("O") || board[1][1].equals("O") || board[2][0].equals("O"))) {
            eliminatedDiagonals++;
        }
        if(eliminatedRows == 3 && eliminatedCols == 3 && eliminatedDiagonals == 2) {
            isTie = true;
        }

        return isTie;
    }
}