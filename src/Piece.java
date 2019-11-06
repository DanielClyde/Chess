import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Piece extends ImageView {
    public String imgUrl;
    public Position pos;
    public boolean isWhite;
    public boolean isCaptured;
    public int width = 40;

    public abstract ArrayList<Position> getLegalMoves();
    public abstract String toString();

}
