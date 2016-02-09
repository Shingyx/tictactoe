package ai;

import game.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Node for the MiniMax algorithm.
 */
public class Node {
    private Player[][] board;
    private int value;
    private ArrayList<Node> children;

    public Node(Player[][] board, int value) {
        this.board = board;
        this.value = value;
        this.children = new ArrayList<>();
    }

    public Node(Player[][] board, Node... nodes) {
        this.board = board;
        this.value = 0;
        this.children = new ArrayList<>();
        Collections.addAll(children, nodes);
    }

    public Node(Player[][] board, List<Node> nodes) {
        this.board = board;
        this.value = 0;
        this.children = new ArrayList<>(nodes);
    }

    private int maxValue(int alpha, int beta) {
        if (children.size() == 0) {
            return this.value;
        }
        int result = Integer.MIN_VALUE;
        for (Node node : children) {
            result = Math.max(result, node.minValue(alpha, beta));
            if (result >= beta) {
                break;
            }
            alpha = Math.max(alpha, result);
        }
        return result;
    }

    private int minValue(int alpha, int beta) {
        if (children.size() == 0) {
            return this.value;
        }
        int result = Integer.MAX_VALUE;
        for (Node node : children) {
            result = Math.min(result, node.maxValue(alpha, beta));
            if (result <= alpha) {
                break;
            }
            beta = Math.min(beta, result);
        }
        return result;
    }

    @Override
    public String toString() {
        if (children.size() == 0) {
            return String.valueOf(this.value);
        } else {
            return "[" + children.toString() + "]";
        }
    }
}
