import javafx.scene.layout.BorderPane;

public class Window extends BorderPane {
    Board board = new Board();
    public Window(){
        this.setCenter(this.board);
    }
}
