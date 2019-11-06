import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chess extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.out.println("Testing");
        Board board = new Board();
        Scene sc = new Scene(board);
        stage.setScene(sc);
        stage.show();
    }
}
