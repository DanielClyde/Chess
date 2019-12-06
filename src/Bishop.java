import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Position p, boolean isWhite, PieceImages images, Board bd) {
        super(p, isWhite, bd);
        this.setImg(isWhite ? images.whiteBishop : images.blackBishop);
    }

    /**
     * Finds the legal moves for the piece
     * @return an array list of legal moves which the piece can make
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);
        moves = addDownRight(moves);
        return moves;
    }

    public String toString() {
        return "Bishop at " + this.getPos().col + "x" + this.getPos().row;
    }

    private ArrayList<Position> addDownLeft(ArrayList<Position> moves){
        int row = this.getPos().row+1;
        int col = this.getPos().col-1;
        while(row <= 7 && col >= 0) {
                if (this.getBoard().board[col][row].hasPiece) {
                    if (this.getBoard().board[col][row].piece.isWhite() ^ this.isWhite()) {
                        moves.add(new Position(col, row));
                    }
                    break;
                }
                moves.add(new Position(col, row));
                row++;
                col--;
        }
        return moves;
    }

    private ArrayList<Position> addUpLeft(ArrayList<Position> moves){
        int row = this.getPos().row-1;
        int col = this.getPos().col-1;
        while(row >= 0 && col >= 0){
            if (this.getBoard().board[col][row].hasPiece){
                if (this.getBoard().board[col][row].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(col, row));
                }
                break;
            }
            moves.add(new Position(col, row));
            row--;
            col--;
}
        return moves;
    }

    private ArrayList<Position> addDownRight(ArrayList<Position> moves){
        int row = this.getPos().row+1;
        int col = this.getPos().col+1;
        while(row <= 7 && col <= 7){
                if (this.getBoard().board[col][row].hasPiece){
                    if (this.getBoard().board[col][row].piece.isWhite() ^ this.isWhite()){
                        moves.add(new Position(col, row));
                    }
                    break;
                }
                moves.add(new Position(col, row));
                row++;
                col++;
        }
        return moves;
    }

    private ArrayList<Position> addUpRight(ArrayList<Position> moves){
        int row = this.getPos().row-1;
        int col = this.getPos().col+1;
        while(row >= 0 && col <= 7){
            if (this.getBoard().board[col][row].hasPiece){
                if (this.getBoard().board[col][row].piece.isWhite() ^ this.isWhite()){
                    moves.add(new Position(col, row));
                }
                break;
            }
            moves.add(new Position(col, row));
            row--;
            col++;
        }
        return moves;
    }

}
