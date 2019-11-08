import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whiteBishop: images.blackBishop;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);
        moves = addDownRight(moves);
        return moves;
    }
    public ArrayList<Position> addDownLeft(ArrayList<Position> m){
        // add tiles to the down and left
        boolean done = false;
        int row = this.pos.row+1;
        int column = this.pos.col-1;
        while(!done){
            if (row <= 7 && column >= 0 ){
                if (!board.tiles[column][row].hasPiece){
                    m.add(new Position(column, row));
                    row++;
                    column--;
                }
                else done = true;
            }
            else done = true;
        }
        return m;
    }

    public ArrayList<Position> addUpLeft(ArrayList<Position> m){
        // add tiles to the down and left
        boolean done = false;
        int row = this.pos.row-1;
        int column = this.pos.col-1;
        while(!done){
            if (row >= 0 && column >= 0 ){
                if (!board.tiles[column][row].hasPiece){
                    m.add(new Position(column, row));
                    row--;
                    column--;
                }
                else done = true;
            }
            else done = true;
        }
        return m;
    }

    public ArrayList<Position> addDownRight(ArrayList<Position> m){
        // add tiles to the down and left
        boolean done = false;
        int row = this.pos.row+1;
        int column = this.pos.col+1;
        while(!done){
            if (row <= 7 && column <= 7 ){
                if (!board.tiles[column][row].hasPiece){
                    m.add(new Position(column, row));
                    row++;
                    column++;
                }
                else done = true;
            }
            else done = true;
        }
        return m;
    }

    public ArrayList<Position> addUpRight(ArrayList<Position> m){
        // add tiles to the down and left
        boolean done = false;
        int row = this.pos.row-1;
        int column = this.pos.col+1;
        while(!done){
            if (row >= 0 && column <= 7 ){
                if (!board.tiles[column][row].hasPiece){
                    m.add(new Position(column, row));
                    row--;
                    column++;
                }
                else done = true;
            }
            else done = true;
        }
        return m;
    }
















    public String toString() {
        return "Bishop at " + this.pos.col + "x" + this.pos.row;
    }
}
