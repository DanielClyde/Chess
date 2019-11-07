import javafx.scene.image.Image;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Position p, boolean isWhite, PieceImages images) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
//                "https://www.pinclipart.com/picdir/big/65-658889_king-rubber-stamp-white-queen-chess-piece-png.png" :
//                "https://www.pinclipart.com/picdir/big/352-3529225_king-chess-piece-shape-comments-queen-chess-piece.png";
                images.whiteQueen :
                images.blackQueen;
        this.setImage(img);
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
    public String toString() {
        return "Queen at " + this.pos.col + "x" + this.pos.row;
    }
}
