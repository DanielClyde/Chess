import javafx.scene.image.Image;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Position p, boolean isWhite, PieceImages images) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
//                "https://www.pinclipart.com/picdir/big/23-237242_rook-clip-art-clipart-chess-rook-clip-art.png" :
//                "https://www.pinclipart.com/picdir/big/67-676127_tiles-clipart-transparent-rook-chess-piece-clipart-png.png";
                images.whiteRook :
                images.blackRook;
        this.setImage(img);
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
    public String toString() {
        return "Rook at " + this.pos.col + "x" + this.pos.row;
    }
}
