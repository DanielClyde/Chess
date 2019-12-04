import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Stack;

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
        Pane pane = new StackPane();
        Image bgImage = new Image("/doggypotter.jpg", 1200, 500, false, true);
        BackgroundImage myBI= new BackgroundImage(
                bgImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        ImageView iv = new ImageView(bgImage);
        Background bg = new Background(myBI);
        iv.fitHeightProperty().bind(stage.heightProperty());
        iv.fitWidthProperty().bind(stage.widthProperty());
//        pane.setBackground(bg);
        Text t1 = new Text("Hello");
        Text t2 = new Text();
        t2.textProperty().bind(t1.textProperty());
        Scanner input = new Scanner(System.in);
        boolean connected;
        String ip;
        do {
            System.out.println("Enter ip address or localhost: ");
            ip = input.nextLine();
            connected = connectToServer(ip, 58901);
        }while(!connected);

        System.out.println("Connected... Let's Play!");
        BorderPane bp = new BorderPane();
        GraveyardPane graveyard = new GraveyardPane();
        StackPane topPane = new StackPane();
        ChatBox chatBox = new ChatBox();
        StackPane bottomPane = new StackPane();
        Board board = new Board(graveyard, topPane, ip.equals("localhost"), chatBox, bottomPane);

//        board.minHeightProperty().bind(stage.heightProperty());
//        board.maxHeightProperty().bind(stage.heightProperty());


        bp.setRight(chatBox);
        bp.setCenter(board);
        bp.setLeft(graveyard);
        bp.setTop(topPane);
        bp.setBottom(bottomPane);
        pane.getChildren().add(iv);
        pane.getChildren().add(bp);
        board.gameInProgress.addListener((o, b, b2) -> {
            if (!o.getValue()) {
                Pane gameOverPane = new StackPane();
                Rectangle r = new Rectangle(myBI.getImage().getWidth(), myBI.getImage().getHeight());
                r.setFill(Color.TRANSPARENT);
                Text gameOver = new Text("Game Over!");
                gameOver.setFont(new Font(80));
                gameOver.setTextAlignment(TextAlignment.CENTER);
                gameOverPane.getChildren().addAll(r, gameOver);
                pane.getChildren().add(gameOverPane);
            }
        });
        Scene sc = new Scene(pane, myBI.getImage().getWidth(), myBI.getImage().getHeight());
        stage.setScene(sc);
        stage.show();
    }

    private static boolean connectToServer(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            System.out.println("Connected to Server");
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            return true;
        } catch(IOException e) {return false;}
    }
}