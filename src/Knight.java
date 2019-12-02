import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Position p, boolean isWhite, PieceImages images, Board bd) {
        super(p, isWhite, bd);
        this.setImg(isWhite ? images.whiteKnight : images.blackKnight);
    }

    /**
     * Finds the legal moves for the piece
     * @return an array list of positions where the piece can legally move
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        // left two up one
        if (this.getPos().col - 2 >= 0 && this.getPos().row-1 >= 0){
            if (this.getBoard().tiles[this.getPos().col-2][this.getPos().row-1].hasPiece){
                if (this.getBoard().tiles[this.getPos().col-2][this.getPos().row-1].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col-2, this.getPos().row-1));
                }
            }
            else moves.add(new Position(this.getPos().col-2, this.getPos().row-1));}

        // right two up one
        if (this.getPos().col + 2 <= 7 && this.getPos().row-1 >= 0){
            if (this.getBoard().tiles[this.getPos().col+2][this.getPos().row-1].hasPiece){
                if (this.getBoard().tiles[this.getPos().col+2][this.getPos().row-1].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col+2, this.getPos().row-1));
                }
            }
            else moves.add(new Position(this.getPos().col+2, this.getPos().row-1));}

        // right two down one
        if (this.getPos().col + 2 <= 7 && this.getPos().row+1 <= 7){
            if (this.getBoard().tiles[this.getPos().col+2][this.getPos().row+1].hasPiece){
                if (this.getBoard().tiles[this.getPos().col+2][this.getPos().row+1].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col+2, this.getPos().row+1));
                }
            }
            else moves.add(new Position(this.getPos().col+2, this.getPos().row+1));}

        // left one up two
        if (this.getPos().col - 1 >= 0 && this.getPos().row-2 >= 0){
            if (this.getBoard().tiles[this.getPos().col-1][this.getPos().row-2].hasPiece){
                if (this.getBoard().tiles[this.getPos().col-1][this.getPos().row-2].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col-1, this.getPos().row-2));
                }
            }
            else moves.add(new Position(this.getPos().col-1, this.getPos().row-2));}

        // right one up two
        if (this.getPos().col + 1 <= 7 && this.getPos().row-2 >= 0){
            if (this.getBoard().tiles[this.getPos().col+1][this.getPos().row-2].hasPiece){
                if (this.getBoard().tiles[this.getPos().col+1][this.getPos().row-2].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col+1, this.getPos().row-2));
                }
            }
            else moves.add(new Position(this.getPos().col+1, this.getPos().row-2));}

        // left one down two
        if (this.getPos().col - 1 >= 0 && this.getPos().row+2 <= 7){
            if (this.getBoard().tiles[this.getPos().col-1][this.getPos().row+2].hasPiece){
                if (this.getBoard().tiles[this.getPos().col-1][this.getPos().row+2].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col-1, this.getPos().row+2));
                }
            }
            else moves.add(new Position(this.getPos().col-1, this.getPos().row+2));}

        // right one down two
        if (this.getPos().col + 1 <= 7 && this.getPos().row+2 <= 7){
            if (this.getBoard().tiles[this.getPos().col+1][this.getPos().row+2].hasPiece){
                if (this.getBoard().tiles[this.getPos().col+1][this.getPos().row+2].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col+1, this.getPos().row+2));
                }
            }
            else moves.add(new Position(this.getPos().col+1, this.getPos().row+2));}


        // left two up one
        if (this.getPos().col - 2 >= 0 && this.getPos().row+1 <= 7){
            if (this.getBoard().tiles[this.getPos().col-2][this.getPos().row+1].hasPiece){
                if (this.getBoard().tiles[this.getPos().col-2][this.getPos().row+1].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(this.getPos().col-2, this.getPos().row+1));
                }
            }
            else moves.add(new Position(this.getPos().col-2, this.getPos().row+1));}


        return moves;
    }

    public String toString() {
        return "Knight at " + this.getPos().col + "x" + this.getPos().row;
    }
}
