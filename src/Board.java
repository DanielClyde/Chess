import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends GridPane{
    public Tile[][] tiles;

    public Board () {
        PieceImages pi = new PieceImages();
        this.tiles = new Tile[8][8];
        putTilesOnBoard();
        addPieces(pi);
    }

    /**
    populates board with appropriate tiles and pieces
     */
    private void putTilesOnBoard() {
        boolean isWhite = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = new Tile(isWhite, new Position(x, y));
                t.setOnMouseClicked(e -> {
                    if (t.piece != null) {
                        System.out.println(t.piece.toString());
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else {
                        System.out.println("No piece at " + t.position.col + "x" + t.position.row);
                    }
                });
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    /**
     used to test putting pictures on the board
     */
    private void addPieces(PieceImages pi) {
        this.tiles[4][4].setPiece(new Rook(new Position(4,4), false, pi, this));
        this.tiles[7][4].setPiece(new Rook(new Position(7,4), true, pi, this));
        this.tiles[0][0].setPiece(new King(new Position(0,0), false, pi));
        this.tiles[0][7].setPiece(new Rook(new Position(0,7), true, pi, this));
//        for (Tile[] row : this.tiles) {
//            for (Tile t : row) {
//                if (t.position.row == 1) {
//                    t.setPiece(new Pawn(t.position, true, pi));
//                } else if (t.position.row == 6) {
//                    t.setPiece(new Pawn(t.position, false, pi));
//                }
//                if (t.position.row == 0) {
//                    switch(t.position.col) {
//                        case 0:
//                        case 7:
//                            t.setPiece(new Rook(t.position, true, pi, this));
//                            break;
//                        case 1:
//                        case 6:
//                            t.setPiece(new Knight(t.position, true, pi));
//                            break;
//                        case 2:
//                        case 5:
//                            t.setPiece(new Bishop(t.position, true, pi));
//                            break;
//                        case 3:
//                            t.setPiece(new Queen(t.position, true, pi));
//                            break;
//                        case 4:
//                            t.setPiece(new King(t.position, true, pi));
//                            break;
//                        default: break;
//                    }
//                } else if (t.position.row == 7){
//                    switch(t.position.col) {
//                        case 0:
//                        case 7:
//                            t.setPiece(new Rook(t.position, false, pi, this));
//                            break;
//                        case 1:
//                        case 6:
//                            t.setPiece(new Knight(t.position, false, pi));
//                            break;
//                        case 2:
//                        case 5:
//                            t.setPiece(new Bishop(t.position, false, pi));
//                            break;
//                        case 3:
//                            t.setPiece(new Queen(t.position, false, pi));
//                            break;
//                        case 4:
//                            t.setPiece(new King(t.position, false, pi));
//                            break;
//                        default: break;
//                    }
//                }
//
//            }
//        }
    }

    private void highlightAvailableMoves(ArrayList<Position> moves, boolean isWhite) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                Position p = this.tiles[i][j].position;
                for (Position pos : moves) {
                    if(pos.col == p.col && pos.row == p.row ){//&& !this.tiles[i][j].isActive() && (isWhite ^ this.tiles[i][j].isWhite)) {
                        this.tiles[i][j].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                    }
                }
            }
        }
    }
}






// populate multidimensional array boardNodes with pieces (doesn't put anything on the gui)
//        for (int i=0; i < 8; i++){
//            for (int j=0; j < 8; j++) {
//                if (j == 1) boardNodes[i][j] = new Pieces.Pawn(p1, this);
//                else if (j == 6) boardNodes[i][j] = new Pieces.Pawn(p2, this);
//                else if (j == 7){
//                    if (i == 2 || i == 5) boardNodes[i][j] = new Pieces.Bishop(p2);
//                    if (i == 1 || i == 6) boardNodes[i][j] = new Pieces.Knight(p2);
//                    if (i == 0 || i == 7) boardNodes[i][j] = new Pieces.Rook(p2);
//                    if (i == 4) boardNodes[i][j] = new Pieces.King(p2);
//                    if (i == 3) boardNodes[i][j] = new Pieces.Queen(p2);
//                }
//                else if (j == 0){
//                    if (i == 2 || i == 5) boardNodes[i][j] = new Pieces.Bishop(p1);
//                    if (i == 1 || i == 6) boardNodes[i][j] = new Pieces.Knight(p1);
//                    if (i == 0 || i == 7) boardNodes[i][j] = new Pieces.Rook(p1);
//                    if (i == 3) boardNodes[i][j] = new Pieces.King(p1);
//                    if (i == 4) boardNodes[i][j] = new Pieces.Queen(p1);
//                }
//            }
//        }
