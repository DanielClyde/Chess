import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position p, boolean isWhite, PieceImages images ) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
//                "https://www.pinclipart.com/picdir/big/203-2039435_bishop-rubber-stamp-chess-pawn-png-clipart.png" :
//                "https://www.pinclipart.com/picdir/big/184-1845764_chess-piece-remix-pawn-pen-clipart.png";
                images.whitePawn :
                images.blackPawn;
        this.setImage(img);
    }

    public ArrayList<Position> getLegalMoves() {
        return null;
    }
    public String toString() {
        return "Pawn at " + this.pos.col + "x" + this.pos.row;
    }
}
