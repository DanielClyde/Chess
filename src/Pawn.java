import javafx.geometry.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position p, boolean isWhite, PieceImages images, Board bd ) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whitePawn : images.blackPawn;
        this.setImage(img);
        this.board = bd;
        this.isFirstMove = true;
    }
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        int forwardTwo = this.isWhite ? 2 : -2;
        int forwardOne = this.isWhite ? 1 : -1;
        if (this.pos.row+forwardOne >= 0 && !this.board.tiles[this.pos.col][this.pos.row+forwardOne].hasPiece){// && !this.board.tiles[this.pos.col][this.pos.row-2].hasPiece){
            moves.add(new Position(this.pos.col, this.pos.row+forwardOne));
            if (this.pos.col + 1 <= 7 && this.board.tiles[this.pos.col+1][this.pos.row+forwardOne].hasPiece){
                moves.add(new Position(this.pos.col+1, this.pos.row+forwardOne));
            }
            if (this.pos.col - 1 >= 0 && this.board.tiles[this.pos.col-1][this.pos.row+forwardOne].hasPiece){
                moves.add(new Position(this.pos.col-1, this.pos.row+forwardOne));
            }
            if (this.isFirstMove && !this.board.tiles[this.pos.col][this.pos.row+forwardTwo].hasPiece){
                moves.add(new Position(this.pos.col, this.pos.row+forwardTwo));
            }
        }





        return moves;}
    public String toString() {
        return "Pawn at " + this.pos.col + "x" + this.pos.row;
    }
}
