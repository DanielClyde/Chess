import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class Board extends GridPane {
    public Tile[][] tiles;
    public Tile activeTile = null;
    public boolean isWhiteTurn;
    public ArrayList<Piece> capturedPieces;
    public Socket socket;
    public ObjectInputStream in;
    public ObjectOutputStream out;

    public Board(String ip, int port) {
        //TODO add method to start client stuffs @Daniel (add port and ip adress to constructor)
        PieceImages pi = new PieceImages();
        this.capturedPieces = new ArrayList<>();
        this.tiles = new Tile[8][8];
        putTilesOnBoard();
        addPieces(pi);
        isWhiteTurn = true;
        try {
            this.socket = new Socket(ip, port);
            System.out.println("client connected");
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {}

    }

    private void sendMessage(GameMessage msg) throws IOException {
        System.out.println("Sending message...");
        this.out.writeObject(msg);
        this.out.flush();
        System.out.println("Message sent!");
    }

    /**
     * populates board with appropriate tiles and pieces
     */
    private void putTilesOnBoard() {
        boolean isWhite = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = new Tile(isWhite, new Position(x, y));
                t.setOnMouseClicked(e -> {
                    if (t.piece != null && this.activeTile == null && t.piece.isWhite == isWhiteTurn) {
                        System.out.println(t.piece.toString());
                        this.activeTile = t;
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else if (t.isHighlighted.getValue() && this.activeTile.piece != null ) {
                        t.getChildren().remove(1);
                        if (t.hasPiece) this.capturedPieces.add(t.piece); //adds the piece to the captured pieces arrayList
                        t.setPiece(activeTile.piece);
                        this.activeTile.setPiece(null);
                        activeTile.hasPiece = false;
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                        //TODO this is where a message will be sent (send a board) @Daniel
                        GameMessage message = new GameMessage("Test", this);
                        try {
                            this.sendMessage(message);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        checks();
                        isWhiteTurn = !isWhiteTurn;
                    } else {
                        this.activeTile = null;
                        this.clearHighlightedTiles();
                    }
                    this.updatePieceBoards();
                });
                t.isHighlighted.addListener((o, b, b1) -> {
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


        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.position.row == 1) {
                    t.setPiece(new Pawn(t.position, true, pi, this));
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once
                }
                else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false, pi, this));
                    t.piece.isFirstMove = true; //setPiece sets this to false, this is where we will make it true just once

                }
                if (t.position.row == 0) {
                    switch (t.position.col) {
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
                        default:
                            break;
                    }
                } else if (t.position.row == 7) {
                    switch (t.position.col) {
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
                        default:
                            break;
                    }
                }

            }
        }
    }

    /**
     * run checks on both kings to see if one is in checkmate or check
     */
    private void checks(){
        checks(true);
        checks(false);
    }

    /**
     * run checks on one king to see if it is in checkmate or check
     * @param isWhite
     */
    private void checks(boolean isWhite) {
        String player = isWhite ? "White" : "Black";
        if (check(isWhite)) {
            if (checkMate(isWhite)) System.out.println("Game over! " + player + " is in checkmate!");
            else System.out.println(player + " is in check!"); }
    }

    /**
     * check to see if a king is in check
     * @param isWhite
     * @return
     */
    private boolean check(boolean isWhite) {
        Tile kingTile = null;
        ArrayList<Position> opponentMoves = new ArrayList<>();
        // find king's tile and populate opponent pieces and moves
        for (Tile[] t : this.tiles) {
            for (Tile tile : t) {
                if (tile.piece instanceof King && tile.piece.isWhite == isWhite) {
                    kingTile = tile;
                }
                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
                    opponentMoves.addAll(tile.piece.getLegalMoves());
                }
            }
        }
        // compare every position with king's position
        for (Position opponentMove : opponentMoves) {
            if (opponentMove.equals(kingTile.position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check to see if a king is in checkmate
     * @param isWhite
     * @return
     */
    private boolean checkMate(boolean isWhite){
        ArrayList<Position> kingMoves = new ArrayList<>();
        ArrayList<Position> opponentMoves = new ArrayList<>();
        // find king's tile and populate opponent moves
        for (Tile[] t : this.tiles) {
            for (Tile tile : t) {
                if (tile.piece instanceof King && tile.piece.isWhite == isWhite) {
                    kingMoves = tile.piece.getLegalMoves();
                }
                if (tile.hasPiece && tile.piece.isWhite != isWhite) {
                    opponentMoves.addAll(tile.piece.getLegalMoves());
                }
            }
        }
        // check every possible move of the opponent against every possible move of the king, only return true if ALL
        // of the king's moves equal the opponent's
        int matchingTiles = 0;
        for (int i=0; i<kingMoves.size(); i++){
        for (Position opponentMove : opponentMoves) {
            if (opponentMove.equals(kingMoves.get(i))) {
                matchingTiles++;
                break;
            }
            }
        }
        return matchingTiles >= kingMoves.size();
    }

    private void highlightAvailableMoves(ArrayList<Position> moves, boolean isWhite) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position p = this.tiles[i][j].position;
                for (Position pos : moves) {
                    if (pos.col == p.col && pos.row == p.row) {
                        this.tiles[i][j].isHighlighted.set(true);
                    }
                }
            }
        }
    }
}
