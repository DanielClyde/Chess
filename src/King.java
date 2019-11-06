import javafx.scene.image.Image;

import java.util.ArrayList;

public class King extends Piece {
    public King(Position p, boolean isWhite) {
        this.setFitWidth(80);
        this.setFitHeight(80);
        this.pos = p;
        this.isWhite = isWhite;
        this.imgUrl = this.isWhite ?
                "https://www.pinclipart.com/picdir/big/69-695089_all-photo-png-clipart-king-chess-piece-clip.png" :
                "https://www.pinclipart.com/picdir/big/82-825511_big-image-png-king-chess-piece-silhouette-clipart.png";
        this.setImage(new Image(imgUrl));
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<Position>();
        for (int rows = this.pos.row - 1; rows <= this.pos.row + 1; rows++) {
            for (int cols = this.pos.col - 1; cols <= this.pos.col + 1; cols++) {
                if (rows >= 0 && rows <= 7 && cols >= 0 && cols <= 7) {
                    moves.add(new Position(cols, rows));
                }
            }
        }
        return moves;
    }
    public String toString() {
        return "King at " + this.pos.col + "x" + this.pos.row;
    }
}
