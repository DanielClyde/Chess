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
        Board board = new Board();
        bp.setCenter(board);
        Scene sc = new Scene(bp);
        stage.setTitle("White's Turn");
        board.isWhiteTurn.addListener((o, b, b1) -> {
            if (o.getValue() == true){
                stage.setTitle("White's Turn");
            } else {stage.setTitle("Black's Turn");}

        });
        stage.setScene(sc);
        stage.show();
    }
}
