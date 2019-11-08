import javafx.scene.image.Image;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
                images.whiteRook :
                images.blackRook;
        this.setImage(img);
        this.board = bd;
        
    }

        public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        // add tiles above
        for (int rows=this.pos.row-1; rows>this.pos.row-7; rows--){
            if (rows >= 0) {
                if (!this.board.tiles[this.pos.col][rows].hasPiece) {
                    System.out.println("(" + this.pos.col + ", " + rows + ")");
                    moves.add(new Position(this.pos.col, rows));
                }
                else break;
            }
        }
        // add tiles below
        for (int rows=this.pos.row+1; rows<this.pos.row+7; rows++){
                if (rows <= 7) {
                    if (!this.board.tiles[this.pos.col][rows].hasPiece) {
                        System.out.println("(" + this.pos.col + ", " + rows + ")");
                        moves.add(new Position(this.pos.col, rows));
                    }
                    else break;
                }
            }
        // add tiles left
        for (int columns=this.pos.col-1; columns>=this.pos.col-7; columns--){
            if (columns >= 0){
                if (!this.board.tiles[columns][this.pos.row].hasPiece){
                    moves.add(new Position(columns, this.pos.row));
                }
                else break;
            }
        }
        // add tiles right
        for (int columns=this.pos.col+1; columns<=this.pos.col+7; columns++){
            if (columns <= 7){
                if (!this.board.tiles[columns][this.pos.row].hasPiece){
                    moves.add(new Position(columns, this.pos.row));
                }
                else break;
            }
        }






        
//        for (int rows=this.pos.row-7; rows<this.pos.row+8; rows++){
//            if (rows >=0 && rows <= 7){
//                moves.add(new Position(this.pos.col, rows));
//            }
//            if (rows == this.pos.row){
//                for (int cols = this.pos.col-7; cols <= this.pos.col+7; cols++){
//                    if (cols >= 0 && cols <= 7){
//                        moves.add(new Position(cols, rows));
//                    }
//                }
//            }
//        }




        return moves;
    }
    public String toString() {
        return "Rook at " + this.pos.col + "x" + this.pos.row;
    }
}
