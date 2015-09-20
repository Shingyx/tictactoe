package game;

import java.util.Arrays;

/**
 * Manages the game state and handles the game logic. Note X always goes first.
 */
public class Game {

    private char[][] board;
    private char turn;

    /**
     * Constructor for the game.
     */
    public Game() {
        board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
        turn = 'x';
    }

    /**
     * Get the board of the game in the form of a 2D array.
     *
     * @return The board of the game as a 2D array.
     */
    public char[][] getBoard() {
        return board;
    }

    /**
     * Get the symbol of the player in the current turn.
     *
     * @return The player's symbol
     */
    public char getTurn() {
        return turn;
    }

    /**
     * Get the board of the game in the form of a string.
     *
     * @return The board of the game as a string.
     */
    public String getBoardString() {
        String row0 = Arrays.toString(board[0]);
        String row1 = Arrays.toString(board[1]);
        String row2 = Arrays.toString(board[2]);
        return String.format("%s\n%s\n%s", row0, row1, row2);
    }

    /**
     * Reset the board for a new game.
     */
    public void newGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
        turn = 'x';
    }

    /**
     * Make a turn given a row and column.
     *
     * @param row Row of target cell.
     * @param col Column of target cell.
     * @return True if the move was successful.
     */
    public boolean makeMove(int row, int col) {
        boolean result = false;

        if (board[row][col] == ' ') {
            board[row][col] = turn;
            if (turn == 'x') {
                turn = 'o';
            } else {
                turn = 'x';
            }
            result = true;
        }
        return result;
    }

    /**
     * Get the state of the current game based on the points on the baord.
     *
     * @return Whether the game is in progress, x has won or o has won.
     */
    public GameState calculateState() {
        GameState result = GameState.IN_PROGRESS;
        if (checkWin('x')) {
            result = GameState.X_WIN;
        } else if (checkWin('o')) {
            result = GameState.O_WIN;
        } else if (checkTie()) {
            result = GameState.TIE;
        }
        return result;
    }

    /**
     * Check if the input player has won the game.
     *
     * @param player The character representation of the player.
     * @return True if the input player has won.
     */
    private boolean checkWin(char player) {
        boolean result = false;
        for (int i = 0; i < 3; i++) {
            // Check the rows
            if (player == board[i][0] && player == board[i][1] && player == board[i][2]) {
                result = true;
                break;
            }
            // Check the columns
            if (player == board[0][i] && player == board[1][i] && player == board[2][i]) {
                result = true;
                break;
            }
        }
        if (!result) {
            // check the diagonals
            if ((player == board[0][0] && player == board[1][1] && player == board[2][2]) ||
                    (player == board[0][2] && player == board[1][1] && player == board[2][0])) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Check if the game is a tie.
     *
     * @return True if the game is a tie.
     */
    private boolean checkTie() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}