
import java.util.ArrayList;

public class King extends Piece {
    public King(Position p, boolean isWhite, PieceImages images, Board bd) {
        super(p, isWhite, bd);
        this.setImg(isWhite ? images.whiteKing : images.blackKing);
    }

    /**
     * Finds the legal moves for the piece
     * @return an array list of positions where the piece can legally move
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        for (int rows = super.getPos().row - 1; rows <= this.getPos().row + 1; rows++) {
            for (int cols = this.getPos().col - 1; cols <= this.getPos().col + 1; cols++) {
                if (rows >= 0 && rows <= 7 && cols >= 0 && cols <= 7) {
                    if (this.getBoard().tiles[cols][rows].hasPiece){
                        if (this.getBoard().tiles[cols][rows].piece.isWhite() ^ this.isWhite()){
                            moves.add(new Position(cols, rows));}
                    } else{
                        moves.add(new Position(cols, rows));}
                }
            }
        }

        return moves;
    }

    public String toString() {
        return "King at " + this.getPos().col + "x" + this.getPos().row;
    }
}
