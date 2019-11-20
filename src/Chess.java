import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

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
        System.out.println("Connected... adding listener");
        board.isWhiteTurn.addListener((o, b1, b2) -> {
            if (socket == null || in == null || out == null) {
                System.out.println("null values, returning ");
                return;
            }
            try {
                out.writeObject(new GameMessage(MessageType.BOARD, board, null, !board.isWhiteTurn.getValue()));
                GameMessage msg = null;
                while (true) {
                    msg = (GameMessage)in.readObject();
                    if (msg != null) {
                        switch (msg.type) {
                            case BOARD:
                                System.out.println("Message Received!");
                                board = msg.board;
                                break;
                            default:
                                break;
                        }
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
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {e.printStackTrace();}
    }
}
