package ai;

import game.Player;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * Generate MiniMax tree for the AI.
 */
public class MiniMaxTree {

    private Player[][] currentBoard;
    private Player currentTurn;

    public MiniMaxTree(Player[][] currentBoard, Player currentTurn) {
        this.currentBoard = currentBoard;
        this.currentTurn = currentTurn;
        computeScore(currentBoard, currentTurn);
    }

    public Iterable<Player[][]> getChildren(Player[][] board, Player turn) {
        return () -> new Iterator<Player[][]>() {
            int current = 0;

            @Override
            public boolean hasNext() {
                for (int i = current; i < 9; i++) {
                    if (board[i / 3][i % 3] == Player.NONE) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Player[][] next() {
                for (int i = current; i < 9; i++) {
                    if (board[i / 3][i % 3] == Player.NONE) {
                        current = i + 1;
                        Player[][] temp = new Player[3][];
                        for (int j = 0; j < 3; j++) {
                            temp[j] = board[j].clone();
                        }
                        temp[i / 3][i % 3] = turn;
                        return temp;
                    }
                }
                throw new ArrayIndexOutOfBoundsException();
            }

            @Override
            public void remove() {
                throw new NotImplementedException();
            }
        };
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

    private int computeScore(Player[][] board, Player currentTurn) {
        int result = 0;
        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] line : lines) {
            Player pos0 = board[line[0] / 3][line[0] % 3];
            Player pos1 = board[line[1] / 3][line[1] % 3];
            Player pos2 = board[line[2] / 3][line[2] % 3];
            Player[] values = {pos0, pos1, pos2};
            result += getScoreForOneLine(values, currentTurn);
        }
        return result;
    }
}
