import game.Game;
import game.Player;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Main application.
 * <p>
 * TODO: make it do something
 */
public class Main extends Application {

    @FXML
    private Label statusBar;
    @FXML
    private Button btnTopLeft;
    @FXML
    private Button btnTopMiddle;
    @FXML
    private Button btnTopRight;
    @FXML
    private Button btnMiddleLeft;
    @FXML
    private Button btnCentre;
    @FXML
    private Button btnMiddleRight;
    @FXML
    private Button btnBottomLeft;
    @FXML
    private Button btnBottomMiddle;
    @FXML
    private Button btnBottomRight;

    private Game game;
    private Button[][] buttons;

    /**
     * Main method to start the application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launch the application.
     *
     * @param primaryStage The application's primary stage.
     * @throws Exception Something went wrong or something?
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        final Parent root = loader.load();

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(root));

        // Set the min window size
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(480);

        primaryStage.show();
    }

    @FXML
    private void initialize() {
        game = new Game();
        statusBar.setText(game.getTurn() + "'s turn");
        initialiseButtons();
    }

    private void initialiseButtons() {
        buttons = new Button[3][3];
        buttons[0][0] = btnTopLeft;
        buttons[0][1] = btnTopMiddle;
        buttons[0][2] = btnTopRight;
        buttons[1][0] = btnMiddleLeft;
        buttons[1][1] = btnCentre;
        buttons[1][2] = btnMiddleRight;
        buttons[2][0] = btnBottomLeft;
        buttons[2][1] = btnBottomMiddle;
        buttons[2][2] = btnBottomRight;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setOnMouseClicked(generateButtonCallback(row, col));
            }
        }
    }

    private EventHandler<? super MouseEvent> generateButtonCallback(int row, int col) {
        return event -> {
            if (game.makeMove(row, col)) {
                refreshInterface();
            }
        };
    }

    private void refreshInterface() {
        Player[][] board = game.getBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(board[row][col].toString());
            }
        }
        statusBar.setText(game.getTurn() + "'s turn");
    }
}
