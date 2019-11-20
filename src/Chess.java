import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Chess extends Application{
    public static Board board;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ip address or localhost: ");
        String ip = input.nextLine();
        BorderPane bp = new BorderPane();
        board = new Board();
        GraveyardPane graveyard = new GraveyardPane(board.capturedPieces);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
        connectToServer(ip, 58901);
    }

    private static void connectToServer(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            board.isWhiteTurn.addListener((o, b1, b2) -> {
                try {
                    output.writeObject(new GameMessage(MessageType.CHAT, null, null, false));
                } catch (Exception e) {e.printStackTrace();}

            });
            GameMessage msg = null;
            while (true) {
                msg = (GameMessage)input.readObject();
                System.out.println("message received");
                System.out.println(msg.type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
