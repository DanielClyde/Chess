import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Position p, boolean isWhite, PieceImages images) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
//                "https://www.pinclipart.com/picdir/big/60-602746_chess-piece-bishop-king-chessboard-alfil-ajedrez-para.png" :
//                "https://www.pinclipart.com/picdir/big/100-1006999_clipart-silhouette-chess-piece-remix-bishop-alfil-chess.png";
                images.whiteBishop:
                images.blackBishop;
        this.setImage(img);
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
    public String toString() {
        return "Bishop at " + this.pos.col + "x" + this.pos.row;
    }
}
