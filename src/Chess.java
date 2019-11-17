import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Chess extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ip address or localhost: ");
        String ip = input.nextLine();
        BorderPane bp = new BorderPane();
        Board board = new Board(ip, 58901);
        GraveyardPane graveyard = new GraveyardPane(board.capturedPieces);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
    }
}
