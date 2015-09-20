package game;

import java.util.Arrays;

/**
 * Manages the game state and handles the game logic.
 */
public class Game {

    private char[][] state;
    private char turn;

    /**
     * Constructor for the game.
     */
    public Game() {
        state = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                state[row][col] = ' ';
            }
        }
        turn = 'x';
    }

    /**
     * Get the state of the game in the form of a 2D array.
     *
     * @return The state of the game as a 2D array.
     */
    public char[][] getState() {
        return state;
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
     * Get the state of the game in the form of a string.
     *
     * @return The state of the game as a string.
     */
    public String getStateString() {
        String row1 = Arrays.toString(state[0]);
        String row2 = Arrays.toString(state[1]);
        String row3 = Arrays.toString(state[2]);
        return String.format("%s\n%s\n%s", row1, row2, row3);
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

        if (state[row][col] == ' ') {
            state[row][col] = turn;
            if (turn == 'x') {
                turn = 'o';
            } else {
                turn = 'x';
            }
            result = true;
        }
        return result;
    }
}
