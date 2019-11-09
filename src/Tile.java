import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    boolean isWhite;
    public Piece piece;
    public Position position;
    public boolean hasPiece;


    public Tile(boolean isWhite, Position position){
        this.position = position;
        this.isWhite = isWhite;
        Color c = isWhite ? Color.BURLYWOOD : Color.GREEN;
        Background b = new Background(new BackgroundFill(c, null, null));
        this.setBackground(b);
        this.getChildren().add(new Rectangle(50, 50, Color.TRANSPARENT));
    }


    public void setPiece(Piece p) {
        this.piece = p;
        this.getChildren().add(this.piece);
        this.hasPiece = true;
    }


}
