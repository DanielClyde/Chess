import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board extends GridPane{
    public Tile[][] tiles;
    public Tile activeTile = null;

    public Board () {
        //TODO add method to start client stuffs @Daniel (add port and ip adress to constructor)
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
                        //TODO add capture logic include adding things to the graveyard @Josh
                        t.setPiece(activeTile.piece);
//                        t.piece.isFirstMove=true; //setPiece sets this to false, this is where we'll make it true for the first time
                        this.activeTile.setPiece(null);
                        activeTile.hasPiece = false;
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                        //TODO this is where a message will be sent (send a board) @Daniel
                        //TODO call a method to check for check and thats it @Jaxon
                        //TODO add a check for if the king was just captured then end the game @Jaxon or whatever
                    } else {
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                    }
                    this.updatePieceBoards();
                    //TODO toggle turn
                });
                t.isHighlighted.addListener((o,b,b1) -> {
                   if (o.getValue() == true) {
                       t.startHighlight();
                   } else {
                       t.clearHighlight();
                   }
                });
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    private void updatePieceBoards() {
        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.piece != null) {
                    t.piece.updateBoard(this);
                }
            }
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
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once
                } else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false, pi, this));
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once

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
