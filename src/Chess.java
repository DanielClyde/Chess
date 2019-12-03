import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
//TODO Clean up
//TODO add exception handling for server client communication
public class Chess extends Application{
    public static Board board;
    public static Socket socket = null;
    public static ObjectOutputStream out = null;
    public static ObjectInputStream in = null;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        BackgroundImage myBI= new BackgroundImage(new Image("/doggypotter.jpg", 1200, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(myBI);
        pane.setBackground(bg);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ip address or localhost: ");
        String ip = input.nextLine();
        connectToServer(ip, 58901);
        System.out.println("Connected... Let's Play!");
        BorderPane bp = new BorderPane();
        GraveyardPane graveyard = new GraveyardPane();
        StackPane topPane = new StackPane();
        ChatBox chatBox = new ChatBox();
        Board board = new Board(graveyard, topPane, ip.equals("localhost"), chatBox);
        bp.setRight(chatBox);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        bp.setTop(topPane);
        pane.getChildren().add(bp);
        Scene sc = new Scene(pane);
        board.turnStatus.addListener((o, s, s1) -> {
            stage.setTitle(o.getValue());
        });
        stage.setScene(sc);
        stage.show();
    }

    private static void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {e.printStackTrace();}
    }
}