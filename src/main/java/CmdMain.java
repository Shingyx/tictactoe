import ai.MiniMaxTree;
import game.Game;
import game.GameState;
import game.Player;

import java.util.Scanner;

/**
 * Main application for no GUI version.
 */
public class CmdMain {

    private Game game;
    private Player player;
    private Scanner scanner;

    public static void main(String[] args) {
        CmdMain main = new CmdMain();
        Player player = Player.NONE;
        if (args.length > 0) {
            switch (args[0].trim().toUpperCase()) {
                case "X":
                    player = Player.X;
                    break;
                case "O":
                    player = Player.O;
                    break;
                default:
                    System.err.println("Invalid argument");
                    return;
            }
        }
        main.initialise(player);
    }

    private void initialise(Player player) {
        scanner = new Scanner(System.in);
        if (player != Player.NONE) {
            this.player = player;
        } else {
            System.out.println("Choose a player to be: X or O");
            String input = scanner.next();
            input = input.trim().toUpperCase();
            switch (input) {
                case "X":
                    this.player = Player.X;
                    break;
                case "O":
                    this.player = Player.O;
                    break;
                default:
                    System.err.println("Invalid input");
                    return;
            }
        }
        System.out.println("You are player " + this.player.name());
        System.out.println("To play, type in row and column, e.g. '0 2' for top right corner, when prompted to do so");
        newGame();
        gameLoop();
    }

    private void gameLoop() {
        System.out.println(game);
        GameState state = game.calculateState();
        while (state == GameState.IN_PROGRESS) {
            Player currentTurn = game.getTurn();
            if (currentTurn == Player.X && player == Player.O ||
                    currentTurn == Player.O && player == Player.X) {
                System.out.println("AI turn");
                game.makeAiMove();
            } else {
                System.out.println("Your turn");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                boolean move = game.makeMove(row, col);
                while (!move) {
                    System.out.println("Invalid move");
                    System.out.println("Your turn");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                    move = game.makeMove(row, col);
                }
            }
            System.out.println(game);
            state = game.calculateState();
        }
        System.out.println(state.toString());
    }

    private void newGame() {
        game = new Game();
    }
}
