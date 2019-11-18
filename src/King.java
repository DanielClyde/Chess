import javafx.scene.image.Image;

import java.util.ArrayList;

public class King extends Piece {
    public King(Position p, boolean isWhite, PieceImages images, Board bd) {
        this.setFitWidth(this.width);
        this.setFitHeight(this.width);
        this.pos = p;
        this.isWhite = isWhite;
        this.img = this.isWhite ? images.whiteKing : images.blackKing;
        this.setImage(img);
        this.board = bd;
    }

    public ArrayList<Position> getLegalMoves() {
        return getLegalMoves(true);
    }


        public ArrayList<Position> getLegalMoves(boolean withOpponentMoves) {
        //TODO add castleing @Josh
        ArrayList<Position> moves = new ArrayList<Position>();
        ArrayList<Position> opponentMoves = new ArrayList<>();
        Tile kingTile = null;
        for (int rows = this.pos.row - 1; rows <= this.pos.row + 1; rows++) {
            for (int cols = this.pos.col - 1; cols <= this.pos.col + 1; cols++) {
                if (rows >= 0 && rows <= 7 && cols >= 0 && cols <= 7) {
                    if (this.board.tiles[cols][rows].hasPiece){
                        if (this.board.tiles[cols][rows].piece.isWhite ^ this.isWhite){
                            if (!check(this.isWhite, this.board.tiles[cols][rows].position, false)){
                            moves.add(new Position(cols, rows));}
                        }

                    }else{
                        if (!check(this.isWhite, this.board.tiles[cols][rows].position, false)){
                            moves.add(new Position(cols, rows));}

                    }
                }
            }
        }
//        for (Tile[] t : this.board.tiles) {
//            for (Tile tile : t) {
//                if (tile.piece instanceof King && tile.piece.isWhite == isWhite) {
//                    kingTile = tile;
//                }
//                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
//                    if (tile.piece instanceof King && withOpponentMoves){
//                        opponentMoves.addAll(((King) tile.piece).getLegalMoves(false));
//                    } else if (! (tile.piece instanceof King)){
//                        opponentMoves.addAll(tile.piece.getLegalMoves());
//                    }
//                }
//            }
//        }
//        for (Position kingMove : moves){
//        for (Position opponentMove : opponentMoves) {
//            if (opponentMove.equals(kingMove)) {
//                moves.remove(opponentMove);
//            }
//        }}
        return moves;
    }
    private boolean check(boolean isWhite, Position pos, boolean withOpponentMoves) {
        ArrayList<Position> opponentMoves = new ArrayList<>();

        // find king's tile and populate opponent moves
        for (Tile[] t : this.board.tiles) {
            for (Tile tile : t) {
                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
                    if (tile.piece instanceof King && withOpponentMoves){
                        opponentMoves.addAll(((King) tile.piece).getLegalMoves(false));
                    }
                    else {opponentMoves.addAll(tile.piece.getLegalMoves());}
                }
            }
        }
        // compare every position with king's position
        for (Position opponentMove : opponentMoves) {
            if (opponentMove.equals(pos)) {
                return true;
            }
        }
        return false;
    }





    public String toString() {
        return "King at " + this.pos.col + "x" + this.pos.row;
    }
}
