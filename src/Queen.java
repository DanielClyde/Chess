import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Position position, boolean isWhite, PieceImages images, Board bd) {
        super(position, isWhite, bd);
        this.setImg(isWhite ? images.whiteQueen : images.blackQueen);
    }

    /**
     * Finds the legal moves for the piece
     * @return an array list of positions where the piece can legally move
     */
    public ArrayList<Position> getLegalMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves = addDownLeft(moves);
        moves = addDownRight(moves);
        moves = addUpLeft(moves);
        moves = addUpRight(moves);
        moves = addUp(moves);
        moves = addLeft(moves);
        moves = addDown(moves);
        moves = addRight(moves);
        return moves;
    }

    public String toString() {
        return "Queen at " + this.getPos().col + "x" + this.getPos().row;
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

    private ArrayList<Position> addDownLeft(ArrayList<Position> moves){
        int row = this.getPos().row+1;
        int col = this.getPos().col-1;
        while(row <= 7 && col >= 0) {
            if (this.getBoard().board[col][row].hasPiece) {
                if (this.getBoard().board[col][row].piece.isWhite() ^ this.isWhite()) {
                    moves.add(new Position(col, row));
                    break;
                } else break;
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
                    break;
                }
                else break;
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
                    break;
                }
                else break;
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
                    break;
                }
                else break;
            }
            moves.add(new Position(col, row));
            row--;
            col++;
        }
        return moves;
    }

}
