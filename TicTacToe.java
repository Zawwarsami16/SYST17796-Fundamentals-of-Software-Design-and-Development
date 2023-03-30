import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static char[][] board = new char[ROWS][COLS];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = '-';
            }
        }
    }

    private static void playGame() {
        boolean gameFinished = false;
        while (!gameFinished) {
            displayBoard();
            makeMove();
            gameFinished = checkGameOver();
            switchPlayer();
        }
        displayBoard();
        System.out.println("Game over.");
    }

    private static void displayBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.printf("Player %c, enter row (1-3) and column (1-3): ", currentPlayer);
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        } while (!isValidMove(row, col));
        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            System.out.println("Invalid row or column.");
            return false;
        } else if (board[row][col] != '-') {
            System.out.println("Cell already occupied.");
            return false;
        }
        return true;
    }

    private static boolean checkGameOver() {
        if (checkRows() || checkCols() || checkDiagonals()) {
            System.out.printf("Player %c wins!\n", currentPlayer);
            return true;
        } else if (isBoardFull()) {
            System.out.println("Game tied.");
            return true;
        }
        return false;
    }

    private static boolean checkRows() {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0] != '-' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkCols() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] != '-' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
                || (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);

    }

    private static boolean isBoardFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}