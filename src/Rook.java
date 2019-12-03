import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(Position p, boolean isWhite, PieceImages images, Board bd) {
        super(p, isWhite, bd);
        this.setImg(isWhite ? images.whiteRook : images.blackRook);
    }

    /**
     * Finds the legal moves for the rook
     * @return an array list of positions where the rook can legally move
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
            moves = addUp(moves);
            moves = addDown(moves);
            moves = addRight(moves);
            moves = addLeft(moves);
        return moves;
    }

    public String toString() {
        return "Rook at " + this.getPos().col + "x" + this.getPos().row;
    }

    private ArrayList<Position> addUp(ArrayList<Position> moves){
        boolean done = false;
        int row = this.getPos().row-1;
        while (!done){
            if (row >= 0){
                if (this.getBoard().board[this.getPos().col][row].hasPiece){
                    if (this.getBoard().board[this.getPos().col][row].piece.isWhite() ^ this.isWhite()){
                        moves.add(new Position(this.getPos().col, row));
                        break;
                    }else break;
                }
                moves.add(new Position(this.getPos().col, row));
                row--;
            }
            else done = true;
        }
        return moves;
    }

    private ArrayList<Position> addDown(ArrayList<Position> moves){
        boolean done = false;
        int row = this.getPos().row+1;
        while (!done){
            if (row <= 7){
                if (this.getBoard().board[this.getPos().col][row].hasPiece){
                    if (this.getBoard().board[this.getPos().col][row].piece.isWhite() ^ this.isWhite()){
                        moves.add(new Position(this.getPos().col, row));
                        break;
                    }else break;
                }
                moves.add(new Position(this.getPos().col, row));
                row++;
            }
            else done = true;
        }
        return moves;
    }

    private ArrayList<Position> addRight(ArrayList<Position> moves){
        boolean done = false;
        int col = this.getPos().col+1;
        while (!done){
            if (col <= 7){
                if (this.getBoard().board[col][this.getPos().row].hasPiece){
                    if (this.getBoard().board[col][this.getPos().row].piece.isWhite() ^ this.isWhite()){
                        moves.add(new Position(col, this.getPos().row));
                        break;
                    }else break;
                }
                moves.add(new Position(col, this.getPos().row));
                col++; }
            else done = true; }
        return moves;
    }

    private ArrayList<Position> addLeft(ArrayList<Position> moves){
        boolean done = false;
        int col = this.getPos().col-1;
        while (!done){
            if (col >= 0){
                if (this.getBoard().board[col][this.getPos().row].hasPiece){
                    if (this.getBoard().board[col][this.getPos().row].piece.isWhite() ^ this.isWhite()){
                        moves.add(new Position(col, this.getPos().row));
                        break;
                    }else break;
                }
                moves.add(new Position(col, this.getPos().row));
                col--;
            }
            else done = true;
        }
        return moves;

    }


}
