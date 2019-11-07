import javafx.scene.image.Image;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position p, boolean isWhite, PieceImages images) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
//                "https://www.pinclipart.com/picdir/big/191-1916865_horse-chess-piece-knight-comments-knight-chess-piece.png" :
//                "https://www.pinclipart.com/picdir/big/60-602828_chess-piece-knight-bishop-king-knight-chess-clip.png";
                images.whiteKnight :
                images.blackKnight;
        this.setImage(img);
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
    public String toString() {
        return "Knight at " + this.pos.col + "x" + this.pos.row;
    }
}
