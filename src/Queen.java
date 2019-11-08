import javafx.scene.image.Image;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ?
                images.whiteQueen :
                images.blackQueen;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addDownRight(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);

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
        return "Queen at " + this.pos.col + "x" + this.pos.row;
    }
}
