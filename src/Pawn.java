import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(Position p, boolean isWhite, PieceImages images, Board bd) {
        super(p, isWhite, bd);
        this.setImg(isWhite ? images.whitePawn : images.blackPawn);
    }

    /**
     * Finds the legal moves for the piece
     * @return an array list of positions where the piece can legally move
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();

        int forwardTwo = this.isWhite() ? 2 : -2;
        int forwardOne = this.isWhite() ? 1 : -1;

        if (this.getPos().row + forwardOne >= 0 && this.getPos().row + forwardOne <= 7) {
            if (!this.getBoard().board[this.getPos().col][this.getPos().row + forwardOne].hasPiece) {

                moves.add(new Position(this.getPos().col, this.getPos().row + forwardOne));

                if (this.getPos().row + forwardTwo >= 0 && this.getPos().row + forwardTwo <= 7 && this.isFirstMove()) {
                    if (!this.getBoard().board[this.getPos().col][this.getPos().row + forwardTwo].hasPiece) {
                    moves.add(new Position(this.getPos().col, this.getPos().row + forwardTwo));}
                }
            }
        }

        if (this.getPos().row + forwardOne >= 0 && this.getPos().row + forwardOne <= 7 && this.getPos().col - 1 >= 0) {
            if (this.getBoard().board[this.getPos().col - 1][this.getPos().row + forwardOne].hasPiece && (
                    this.getBoard().board[this.getPos().col - 1][this.getPos().row + forwardOne].piece.isWhite() ^ this.isWhite())) {
                moves.add(new Position(this.getPos().col - 1, this.getPos().row + forwardOne));
            }
        }
        if (this.getPos().row + forwardOne >= 0 && this.getPos().row + forwardOne <= 7 && this.getPos().col + 1 <= 7) {
            if (this.getBoard().board[this.getPos().col + 1][this.getPos().row + forwardOne].hasPiece && (
                    this.getBoard().board[this.getPos().col + 1][this.getPos().row + forwardOne].piece.isWhite() ^ this.isWhite())) {
                moves.add(new Position(this.getPos().col + 1, this.getPos().row + forwardOne));
            }
        }


        return moves;
    }

    public String toString() {
        return "Pawn at " + this.getPos().col + "x" + this.getPos().row;
    }
}
