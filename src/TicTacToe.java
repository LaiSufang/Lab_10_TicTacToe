import java.util.Random;
import java.util.Scanner;

/// /// a console-based Tic Tac Toe program that allows two users to play the game as many times as they want

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String[ROW][COL];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        String currentPlayer = "X";
        int moveCount = 0;
        boolean continuePlay;
        boolean playing;
        int rowMove;
        int colMove;
//        final int MOVE_FOR_WIN = 5;
//        final int MOVE_FOR_TIE = 6;
//        final int ALL_MOVES = ROW * COL;

        // Clear the board before game starts
        clearBoard();

        // Display the game board
        System.out.println("Welcome to the Tic Tac Toe!");
        display();

        // entire game loop
        do {
            do {
                playing = true;

                //convert the player move coordinates to the array indices which are 0 – 2 by subtracting 1
                //loop until the converted player coordinates are a valid move
                //if appropriate check for a win or a tie (i.e. if it is possible for a win or a tie at this point in the game, check for it.)
                //If there is a win or tie announce it and then prompt the players to play again.
                //Toggle the player (i.e. X becomes O, O becomes X)
                do {
                    System.out.print("Player " + currentPlayer + "'s move. ");
//                    rowMove = SafeInput.getRangedInt(in, "Please enter your move on row [0-2]: ", 0, ROW);
//                    colMove = SafeInput.getRangedInt(in, "Please enter your move on column [0-2]: ", 0, COL);
                    rowMove = rand.nextInt(3);
                    colMove = rand.nextInt(3);
                } while (!isValidMove(rowMove, colMove));

                board[rowMove][colMove] = currentPlayer;
                System.out.println("Row-Col: " + rowMove  + colMove);
                display();
                moveCount++;

                // check wins when move counts reaches 5
                if (moveCount >= 5) {
                    if (isWin(currentPlayer)) {
                        System.out.println("Player " + currentPlayer +" win!");
                        playing = false;
                        moveCount = 0;
                        currentPlayer = "X";
                    }
                    else {
                        if (isSixStepEarlyTie()) {
                            System.out.println("Early tie! All the row, column, and diagonal win opportunities are blocked, no player is gonna win!");
                            playing = false;
                            moveCount = 0;
                            currentPlayer = "X";
                        } else if (moveCount == 9) {
                            System.out.println("No more moves, it's a full board tie!");
                            playing = false;
                            moveCount = 0;
                            currentPlayer = "X";
                        }
                    }
                }

                // toggle players between X and O
                if (currentPlayer.equals("X")) {
                    currentPlayer = "O";
                } else {
                    currentPlayer = "X";
                }

            } while (playing);

//            in.nextLine();
            continuePlay = SafeInput.getYNConfirm(in, "Do you want to play again[Y/N]: ");
            if (continuePlay) {
                clearBoard();
//                playing = true;
            }
        } while (continuePlay);
    }

    /// /// static methods
    // sets all the board elements to a space
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }
    // shows the Tic Tac Toe game used as part of the prompt for the users move choice…
    private static void display() {
        int r, c;
        for (r = 0; r < ROW; r++) {
            for (c = 0; c < COL - 1; c++) {
                System.out.print(board[r][c] + "|");
            }
            System.out.println(board[r][c] + "|");
        }
    }
    // returns true if there is a space at the given proposed move coordinates which means it is a legal move.
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }
    // checks to see if there is a win state on the current board for the specified player (X or O)
    // This method in turn calls three additional methods that break down the 3 kinds of wins that are possible.
    private static boolean isWin(String currentPlayer){
        return isColWin(currentPlayer) || isRowWin(currentPlayer) || isDiagonalWin(currentPlayer);
    }
    // checks for a col win for specified player
    private static boolean isColWin(String currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i].equals(currentPlayer) && board[1][i].equals(currentPlayer) && board[2][i].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }
    // checks for a row win for the specified player
    private static boolean isRowWin(String currentPlayer) {
        for(int i= 0; i < 3; i++)
        {
            if(board[i][0].equals(currentPlayer) && board[i][1].equals(currentPlayer) && board[i][2].equals(currentPlayer))
            {
                return true;
            }
        }
        return false; // no row win
    }
    // checks for a diagonal win for the specified player
    private static boolean isDiagonalWin(String currentPlayer) {
        if ((board[0][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][2].equals(currentPlayer)) ||
                (board[2][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[0][2].equals(currentPlayer))) {
            return true;
        }
        return false;
    }
    // checks for a tie condition:
    /// all spaces on the board are filled
    /// OR there is an X and an O in every win vector (i.e. all possible 8 wins are blocked by having both and X and an O in them.)
    /// if we have 9 moves (which fills the board) and we don’t have a win, then we have a full board TIE.
    /// So after checking for a win and not getting one, if we have 9 moves then the game is over with a full board tie.
    /// The other kind of TIE is when we have not filled the board but neither player can win.
    /// We can have this sort of TIE after 7 moves. To have this kind of tie, each of the 8 win vectors (row, col, diagonal) must be eliminated.
    /// If a win vector contains both and X and an O, that vector is eliminated. If all are eliminated then no win is possible even though we have open cells still.

    private static boolean isSixStepEarlyTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[0][0].equals("X") && board[1][2].equals("X") && board[2][0].equals("X")
                && board[0][2].equals("O") && board[1][0].equals("O")  && board[2][2].equals("O")) {
                    return true;
                }
            }
        }
        return false;
    }
}
