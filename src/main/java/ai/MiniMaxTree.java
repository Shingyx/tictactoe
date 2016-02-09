package ai;

import game.Player;

/**
 *
 */
public class MiniMaxTree {

    private Player[][] currentBoard;
    private Player currentTurn;

    public MiniMaxTree(Player[][] currentBoard, Player currentTurn) {
        this.currentBoard = currentBoard;
        this.currentTurn = currentTurn;
        computeScore();
    }

    private int getScoreForOneLine(Player[] values, Player currentTurn) {
        int countX = 0, countO = 0;
        for (Player v : values) {
            if (v == Player.X) {
                countX++;
            } else if (v == Player.O) {
                countO++;
            }
        }
        //The player who has turn should have more advantage.
        int advantage = 1;
        int result = 0;
        if (countO == 0) {
            if (currentTurn == Player.X) {
                advantage = 3;
            }
            result = (int) (Math.pow(10, countX) * advantage);
        } else if (countX == 0) {
            if (currentTurn == Player.O) {
                advantage = 3;
            }
            result = (int) (-Math.pow(10, countO) * advantage);
        }
        return result;
    }

    private int computeScore() {
        int result = 0;
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] line : lines) {
            Player pos0 = currentBoard[line[0] / 3][line[0] % 3];
            Player pos1 = currentBoard[line[1] / 3][line[1] % 3];
            Player pos2 = currentBoard[line[2] / 3][line[2] % 3];
            Player[] values = {pos0, pos1, pos2};
            result += getScoreForOneLine(values, currentTurn);
        }
        return result;
    }
}
