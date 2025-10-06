import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char[] PLAYERS = {'X', 'O'};
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Tic Tac Toe (CLI) ===");
        do {
            playOneGame();
        } while (askYesNo("Play again? (y/n): "));
        System.out.println("Thanks for playing!");
        SC.close();
    }

    private static void playOneGame() {
        char[][] board = new char[SIZE][SIZE];
        for (char[] row : board) Arrays.fill(row, EMPTY);

        int moveCount = 0;
        int turn = 0; // 0 -> X, 1 -> O

        while (true) {
            printBoard(board);
            char current = PLAYERS[turn];
            System.out.println("Player " + current + "'s turn.");

            int cell = askMove(board);
            place(board, cell, current);
            moveCount++;

            if (hasWon(board, current)) {
                printBoard(board);
                System.out.println("🎉 Player " + current + " wins!");
                return;
            }
            if (moveCount == SIZE * SIZE) {
                printBoard(board);
                System.out.println("It's a draw.");
                return;
            }
            turn ^= 1; // switch player
        }
    }

    // Map 1..9 to board positions; ensure empty
    private static int askMove(char[][] board) {
        while (true) {
            System.out.print("Choose a cell (1-9): ");
            String input = SC.nextLine().trim();
            if (!input.matches("[1-9]")) {
                System.out.println("Invalid input. Enter a number 1-9.");
                continue;
            }
            int cell = Integer.parseInt(input);
            int r = (cell - 1) / SIZE;
            int c = (cell - 1) % SIZE;
            if (board[r][c] != EMPTY) {
                System.out.println("Cell already taken. Try another.");
                continue;
            }
            return cell;
        }
    }

    private static void place(char[][] board, int cell, char player) {
        int r = (cell - 1) / SIZE;
        int c = (cell - 1) % SIZE;
        board[r][c] = player;
    }

    private static boolean hasWon(char[][] b, char p) {
        // rows & cols
        for (int i = 0; i < SIZE; i++) {
            if ((b[i][0] == p && b[i][1] == p && b[i][2] == p) ||
                (b[0][i] == p && b[1][i] == p && b[2][i] == p)) {
                return true;
            }
        }
        // diagonals
        return (b[0][0] == p && b[1][1] == p && b[2][2] == p) ||
               (b[0][2] == p && b[1][1] == p && b[2][0] == p);
    }

    private static void printBoard(char[][] b) {
        // Show numbers for empty cells to help users choose
        StringBuilder sb = new StringBuilder();
        int num = 1;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                char cell = (b[r][c] == EMPTY) ? (char) ('0' + num) : b[r][c];
                sb.append(" ").append(cell).append(" ");
                if (c < SIZE - 1) sb.append("|");
                num++;
            }
            sb.append("\n");
            if (r < SIZE - 1) sb.append("---+---+---\n");
        }
        System.out.print("\n" + sb + "\n");
    }

    private static boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String ans = SC.nextLine().trim().toLowerCase();
            if (ans.equals("y") || ans.equals("yes")) return true;
            if (ans.equals("n") || ans.equals("no")) return false;
            System.out.println("Please answer y/yes or n/no.");
        }
    }
}
