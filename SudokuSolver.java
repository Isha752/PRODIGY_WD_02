package prodigy;

public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the Sudoku puzzle.");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;

        // Find the first empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // If there are no empty cells, the puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try numbers 1 to 9 in the empty cell
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                }
                // If placing num at board[row][col] doesn't lead to a solution, backtrack
                board[row][col] = EMPTY;
            }
        }

        // Trigger backtracking
        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if num is not already in the current row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if num is not already in the current column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if num is not already in the current 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

