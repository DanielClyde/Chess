import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends GridPane{
    public Tile[][] tiles;
    public Tile activeTile = null;

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
                    if (t.piece != null && this.activeTile == null) {
                        System.out.println(t.piece.toString());
                        this.activeTile = t;
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else if (t.isHighlighted.getValue() && this.activeTile.piece != null){
                        t.setPiece(activeTile.piece);
                        this.activeTile.setPiece(null);
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                    } else {
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                    }
                });
                t.isHighlighted.addListener((o,b,b1) -> {
                   if (o.getValue() == true) {
                       t.setBackground(new Background(new BackgroundFill(Color.YELLOW, null,null)));
                   } else {
                       Color c = t.isWhite ? Color.BURLYWOOD : Color.GREEN;
                       Background back = new Background(new BackgroundFill(c, null, null));
                       t.setBackground(back);
                   }
                });
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    private void clearHighlightedTiles() {
        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.isHighlighted.getValue()) {
                    t.isHighlighted.set(false);
                }
            }
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
                        this.tiles[i][j].isHighlighted.set(true);
                    }
                }
            }
        }
    }
}
