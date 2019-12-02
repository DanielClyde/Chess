import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//TODO Is this used or can it be removed from the project?
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
