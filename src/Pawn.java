import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position p, boolean isWhite) {
        this.setFitWidth(80);
        this.setFitHeight(80);
        this.pos = p;
        this.isWhite = isWhite;
        this.imgUrl = this.isWhite ?
                "https://www.pinclipart.com/picdir/big/203-2039435_bishop-rubber-stamp-chess-pawn-png-clipart.png" :
                "https://www.pinclipart.com/picdir/big/184-1845764_chess-piece-remix-pawn-pen-clipart.png";
        this.setImage(new Image(imgUrl));
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
}
