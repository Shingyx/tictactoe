import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application.
 *
 * TODO: make it do something
 */
public class Main extends Application {

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
}
