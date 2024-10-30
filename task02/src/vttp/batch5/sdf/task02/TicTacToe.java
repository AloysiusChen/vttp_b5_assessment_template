package vttp.batch5.sdf.task02;

public class TicTacToe {
    char[][] board = new char[3][3];

    // Create empty Tic Tac Toe board, each field populated with '.'
    void createBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Print Tic Tac Toe board
    void printBoard() {
        System.out.println("Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    // Check for draw outcome
    boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.')
                    return false;
            }
        }
        return true;
    }

    // Check for win outcome
    boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    // Minimax
    int minimax(char[][] board, boolean isMax) {
        if (checkWin('X'))
            return 1;
        if (checkWin('O'))
            return -1;
        if (checkDraw())
            return 0;

        if (isMax) {
            int outcomeMin = Integer.MIN_VALUE;
            int score;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = 'X';
                        score = minimax(board, false);
                        board[i][j] = '.';
                        outcomeMin = Math.max(outcomeMin, score);
                    }
                }
            }
            return outcomeMin;
        } else {
            int outcomeMax = 1000;
            int score;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '.') {
                        board[i][j] = 'O';
                        score = minimax(board, true);
                        board[i][j] = '.';
                        outcomeMax = Math.min(outcomeMax, score);
                    }
                }
            }
            return outcomeMax;
        }
    }

    // Utility
    void utility() {
        int testMove = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = 'X';
                    testMove = minimax(board, false);
                    board[i][j] = '.';

                    System.out.printf("y=%d, x=%d, utility=%d\n", i, j, testMove);
                }
            }
        }
    }
}