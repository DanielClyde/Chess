import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//TODO Clean up
public class Window extends Application {
    public void start(Stage stage){
        ChatBox chatBox = new ChatBox();
        stage.setTitle("Chat away");
        stage.setHeight(500);
        Scene sc = new Scene(chatBox);
        stage.setScene(sc);
        stage.show();
    }
}
