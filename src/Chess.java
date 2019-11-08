import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Chess extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane bp = new BorderPane();
        final long start = System.currentTimeMillis();
        Board board = new Board();
        final long end = System.currentTimeMillis();
        System.out.println("Board initialization time: " + (end - start));
        bp.setCenter(board);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
    }
}
