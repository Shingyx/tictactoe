import game.Game;
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

    private Game game;

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
        btnTopLeft.setOnMouseClicked(generateButtonCallback(0, 0));
        btnTopMiddle.setOnMouseClicked(generateButtonCallback(0, 1));
        btnTopRight.setOnMouseClicked(generateButtonCallback(0, 2));
        btnMiddleLeft.setOnMouseClicked(generateButtonCallback(1, 0));
        btnCentre.setOnMouseClicked(generateButtonCallback(1, 1));
        btnMiddleRight.setOnMouseClicked(generateButtonCallback(1, 2));
        btnBottomLeft.setOnMouseClicked(generateButtonCallback(2, 0));
        btnBottomMiddle.setOnMouseClicked(generateButtonCallback(2, 1));
        btnBottomRight.setOnMouseClicked(generateButtonCallback(2, 2));
    }

    private EventHandler<? super MouseEvent> generateButtonCallback(int col, int row) {
        return event -> {
            if (game.makeMove(col, row)) {
                refreshInterface();
            }
        };
    }

    private void refreshInterface() {
        char[][] board = game.getBoard();
        btnTopLeft.setText(String.valueOf(board[0][0]));
        btnTopMiddle.setText(String.valueOf(board[0][1]));
        btnTopRight.setText(String.valueOf(board[0][2]));
        btnMiddleLeft.setText(String.valueOf(board[1][0]));
        btnCentre.setText(String.valueOf(board[1][1]));
        btnMiddleRight.setText(String.valueOf(board[1][2]));
        btnBottomLeft.setText(String.valueOf(board[2][0]));
        btnBottomMiddle.setText(String.valueOf(board[2][1]));
        btnBottomRight.setText(String.valueOf(board[2][2]));
        statusBar.setText(game.getTurn() + "'s turn");
    }
}
