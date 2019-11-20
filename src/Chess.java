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
    public static boolean isWhitePlayer;
    public static Socket socket = null;
    public static ObjectInputStream in = null;
    public static ObjectOutputStream out = null;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ip address or localhost: ");
        String ip = input.nextLine();
        isWhitePlayer = ip == "localhost";
        BorderPane bp = new BorderPane();
        board = new Board();
        GraveyardPane graveyard = new GraveyardPane(board.capturedPieces);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        Scene sc = new Scene(bp);
        stage.setScene(sc);
        stage.show();
        connectToServer(ip, 58901);
        System.out.println("Connected... adding listener");
        board.isWhiteTurn.addListener((o, b1, b2) -> {
            if (socket == null || in == null || out == null) {
                System.out.println("null values, returning ");
                return;
            }
            try {
                out.writeObject(new GameMessage(MessageType.CHAT, null, null, false));
                GameMessage msg = null;
                while (true) {
                    msg = (GameMessage)in.readObject();
                    if (msg != null) {
                        System.out.println(msg.type);
                        break;
                    }
                }
            } catch (Exception e) {e.printStackTrace();}
        });
    }

    private static void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
