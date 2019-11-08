import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position p, boolean isWhite, PieceImages images ) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
                images.whitePawn :
                images.blackPawn;
        this.setImage(img);
        boolean isFirstMove = true;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){

            }
        }
//        if (this.pos.row-1)




        return null;}
    public String toString() {
        return "Pawn at " + this.pos.col + "x" + this.pos.row;
    }
}
