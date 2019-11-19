import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Chess extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane bp = new BorderPane();
        GraveyardPane graveyard = new GraveyardPane();
        StackPane topPane = new StackPane();
        Board board = new Board(graveyard, topPane);
        //TODO add in the chatbox pane
        bp.setCenter(board);
        bp.setLeft(graveyard);
        bp.setTop(topPane);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
    }
}
