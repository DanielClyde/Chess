import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends GridPane{
    public Tile[][] tiles;
    public Tile onDeck;

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
                    if (this.onDeck == null){
                    if (t.piece != null) {
                        this.onDeck = t;
                        System.out.println(t.piece.toString());
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else {
                        System.out.println("No piece at " + t.position.col + "x" + t.position.row);
                    }
                }
                else {
                    ArrayList<Position> moves = onDeck.piece.getLegalMoves();
                        System.out.println("09809809809809809");
                    for (Position p : moves){
                        if (p.row == t.position.row && p.col == t.position.col){
                            Piece piece = onDeck.piece;
                            onDeck.piece = null;
                            this.tiles[t.position.col][t.position.row].getChildren().remove(t.piece);
                            this.tiles[t.position.col][t.position.row].setPiece(piece);
                            System.out.println("============================");

                        }
                    }
                    for (Position p : moves){
//                        t.c = t.isWhite white : dark;
                    }
                }
                }
                );
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }


    private void addPieces(PieceImages pi) {
        this.tiles[3][3].setPiece(new Bishop(new Position(3,3), true, pi, this));
        this.tiles[1][5].setPiece(new Queen(new Position(1, 5), true, pi, this));

        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.position.row == 1) {
                    t.setPiece(new Pawn(t.position, true, pi, this));
                } else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false, pi, this));
                }
                if (t.position.row == 0) {
                    switch(t.position.col) {
                        case 0:
                        case 7:
                            t.setPiece(new Rook(t.position, true, pi, this));
                            break;
                        case 1:
                        case 6:
                            t.setPiece(new Knight(t.position, true, pi, this));
                            break;
                        case 2:
                        case 5:
                            t.setPiece(new Bishop(t.position, true, pi, this));
                            break;
                        case 3:
                            t.setPiece(new Queen(t.position, true, pi, this));
                            break;
                        case 4:
                            t.setPiece(new King(t.position, true, pi, this));
                            break;
                        default: break;
                    }
                } else if (t.position.row == 7){
                    switch(t.position.col) {
                        case 0:
                        case 7:
                            t.setPiece(new Rook(t.position, false, pi, this));
                            break;
                        case 1:
                        case 6:
                            t.setPiece(new Knight(t.position, false, pi, this));
                            break;
                        case 2:
                        case 5:
                            t.setPiece(new Bishop(t.position, false, pi, this));
                            break;
                        case 3:
                            t.setPiece(new Queen(t.position, false, pi, this));
                            break;
                        case 4:
                            t.setPiece(new King(t.position, false, pi, this));
                            break;
                        default: break;
                    }
                }

            }
        }
    }

    private void highlightAvailableMoves(ArrayList<Position> moves, boolean isWhite) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                Position p = this.tiles[i][j].position;
                for (Position pos : moves) {
                    if(pos.col == p.col && pos.row == p.row ){
                        this.tiles[i][j].setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
                    }
                }
            }
        }
    }
}

