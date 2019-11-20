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

        System.out.println("Connected... Let's Play!");
        play();
    }

    private static void connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e) {e.printStackTrace();}
    }

    private static void play() {
        while(true) {
            if(board.hasMessageToSend) {
                GameMessage msg = board.getToSend();
                try {
                    out.writeObject(msg);
                    out.flush();
                } catch (Exception e) {e.printStackTrace();}
            }
            try {
                GameMessage m = (GameMessage)in.readObject();
                if (m != null) {
                    board.onMessageReceived(m);
                }
            } catch (Exception e) {e.printStackTrace();}

        }
    }
}
