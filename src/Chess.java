import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
        stage.setWidth(300);
        stage.setHeight(400);
        BorderPane menu = new BorderPane();
        TextField ipAddress = new TextField("Enter ip address or localhost: ");
        menu.setCenter(ipAddress);
        Scene sc = new Scene(menu);
        stage.setScene(sc);
        stage.show();
        ipAddress.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.ENTER){
                System.out.println("yey");
                startUp(ipAddress.getText(), sc, stage);

            }
        });




}



    private static void startUp(String ip, Scene sc, Stage stage){
        connectToServer(ip, 58901);
        BorderPane bp = new BorderPane();
        GraveyardPane graveyard = new GraveyardPane();
        StackPane topPane = new StackPane();
        Board board = new Board(graveyard, topPane, ip.equals("localhost"));
        //TODO add in the chatbox pane
        bp.setCenter(board);
        bp.setLeft(graveyard);
        bp.setTop(topPane);
        sc = new Scene(bp);
        stage.setScene(sc);


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
