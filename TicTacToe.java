import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    // 3x3 board, initialized with empty spaces
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    // Random number generator for AI moves 
    private static Random random = new Random();

    public static void main(String[] args) {

        // Game Logic will go here
        Scanner scanner = new Scanner(System.in); // Create a Scanner object to get user input 
        char currentPlayer = 'X'; // Player X goes first

        while (true) {
            printBoard(); // Print the current game board

            if (currentPlayer == 'X') {
                // Player's turn
                System.out.println("Player " + currentPlayer + ", enter a position (1-9): ");
                int position = scanner.nextInt(); // Read the player's input (a number between 1-9)

                // Convert position to row and column
                int row = (position - 1) / 3; // Divide by 3 to get the row index (0-2)
                int col = (position - 1) % 3; // Remainder after division gives the column index (0-2)

                // Mark the chosen spot with currentPlayer ('X' or 'O')
                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayer; // Update the board with the current player's move
                } else {
                    System.out.println("This spot is already taken! Try again.");
                    continue; // If the spot is taken, skip the rest of the loop and prompt again
                }
            } else {
                // AI's turn (Player O) 
                System.out.println("AI's turn (O)");
                makeAIMove(); // AI makes its move 
            }

            // Check if the game is over
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break; // Exit the loop if there's a winner
            } else if (checkTie()) {
                printBoard();
                System.out.println("It's a tie!");
                break; // Exit the loop if the game is tied
            }

            // If not, switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch between 'X' (Player) and 'O' (AI)
        }

        scanner.close(); // Close the scanner object
    }

    // Method to print the game board
    public static void printBoard() {
        System.out.println(" " + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    // Method to check if the current player has won
    public static boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a winner
        for (int i = 0; i < 3; i++) {
            // Check row
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            // Check column
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Method to check if the game is a tie
    public static boolean checkTie() {
        // If all spaces are filled and no one has won, it's a tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // If there's an empty space, it's not a tie yet
                }
            }
        }
        return true; // All spots are filled and there's no winner
    }

    // Method for the AI to make its move (randomly choose an empty spot) 
    public static void makeAIMove() {
        int row, col;
        do {
            row = random.nextInt(3); // Random row (0, 1, or 2) 
            col = random.nextInt(3); // Random column (0, 1, or 2) 
        } while (board[row][col] != ' '); // Continue looping until an empty spot is found 

        board[row][col] = 'O'; // AI places 'O' 
    }
}