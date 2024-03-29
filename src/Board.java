import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class Board extends GridPane implements Serializable {
    public Tile[][] board;
    public Tile activeTile = null;
    public GraveyardPane graveyard;
    public StackPane topPane;
    public boolean whitePlayer;
    public SimpleBooleanProperty isWhiteTurn = new SimpleBooleanProperty(true);
    public ChatBox chatBox;
    private Text topTxt = new Text("Game Start");
    private Text btmTxt = new Text("");
    public SimpleBooleanProperty gameInProgress = new SimpleBooleanProperty(true);
    public StackPane bottomPane;


    public Board(GraveyardPane graveyard, StackPane topPane, boolean white, ChatBox chatBox, StackPane bottomPane) {

        this.chatBox = chatBox;
        this.whitePlayer = white;
        PieceImages pi = new PieceImages();
        this.topPane = topPane;
        this.bottomPane = bottomPane;

        this.topTxt.setFill(Color.BLACK);
        this.btmTxt.setFill(Color.WHITE);
        topTxt.setFont(Font.font("Verdana", 20));
        btmTxt.setFont(Font.font("Verdana", 40));

        topPane.getChildren().add(this.topTxt);
        bottomPane.getChildren().add(this.btmTxt);
        this.graveyard = graveyard;

        this.board = new Tile[8][8];
        putTilesOnBoard();
        addPieces(pi);
        this.isWhiteTurn.addListener((o,b,b1) -> {
            this.getMessages();
        });
        if (!this.whitePlayer) {
            System.out.println("Waiting for white to go first!");
            this.getMessages();
        }
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
                    if (t.piece != null && this.activeTile == null && this.whitePlayer == t.piece.isWhite()) {
                        this.activeTile = t;
                        ArrayList<Position> moves = t.piece.getLegalMoves();
                        this.highlightAvailableMoves(moves, t.isWhite);
                    } else if (t.isHighlighted.getValue() && this.activeTile.piece != null ) {
                        movePieces(t);
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
                this.board[x][y] = t;
                this.add(this.board[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    public void getMessages() {
        while(true){
            try {
                GameMessage received = (GameMessage)Chess.in.readObject();
                if (received != null && received.type == MessageType.CHAT){
                    chatBox.onMessageReceived(received);
                } else if (received != null && received.type==MessageType.MOVE) {
                    this.onMessageReceived(received);
                    break;
                }

            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public GameMessage createMoveMessage(Position from, Position to) {
        Position[] moves = {from ,to};
        return new GameMessage(MessageType.MOVE, moves, null);
    }

    public void sendMessage(GameMessage m) {
        try {
            Chess.out.writeObject(m);
        } catch (Exception e) {e.printStackTrace();}

    }


    public void onMessageReceived(GameMessage m) {
        Position from = m.movePositions[0];
        Position to = m.movePositions[1];
        Tile fromTile = this.board[from.col][from.row];
        Tile toTile = this.board[to.col][to.row];

        try {
            toTile.getChildren().remove(1);
        } catch (Exception e) {}
        if (toTile.hasPiece){
            graveyard.addPiece(toTile.piece); //adds the piece to the captured pieces arrayList
            if(toTile.piece instanceof King) gameEnd();
        }
        toTile.setPiece(fromTile.piece);
        fromTile.setPiece(null);
        fromTile.hasPiece = false;
        this.clearHighlightedTiles();
        if (gameInProgress.getValue()) checks();
        updatePieceBoards();
        this.changeTopPane("Your Turn!");
    }

    private void updatePieceBoards() {
        for (Tile[] row : this.board) {
            for (Tile t : row) {
                if (t.piece != null) {
                    t.piece.updateBoard(this);
                }
            }
        }
    }

    private void clearHighlightedTiles() {
        for (Tile[] row : this.board) {
            for (Tile t : row) {
                if (t.isHighlighted.getValue()) {
                    t.isHighlighted.set(false);
                }
            }
        }
    }


    private void addPieces(PieceImages pi) {


        for (Tile[] row : this.board) {
            for (Tile t : row) {
                if (t.position.row == 1) {
                    t.setPiece(new Pawn(t.position, true, pi, this));
                    t.piece.setFirstMove(true); //setPiece sets this to false, this is where we will make it true just once
                }
                else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false, pi, this));
                    t.piece.setFirstMove(true); //setPiece sets this to false, this is where we will make it true just once

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
        if (check(true)){
            changeBottomPane("White is in check! :(");
        }
        else if (check(false)){
            changeBottomPane("Black is in check!!!!!!!!!!!");
        }
        else{
            changeBottomPane("");
        }
    }


    /**
     * check to see if a king is in check
     * @param isWhite
     * @return
     */
    private boolean check(boolean isWhite) {
        Tile kingTile = null;
        ArrayList<Position> opponentMoves = new ArrayList<>();
        // find king's tile and populate opponent moves
        for (Tile[] t : this.board) {
            for (Tile tile : t) {
                if (tile.piece instanceof King && tile.piece.isWhite() == isWhite) {
                    kingTile = tile;
                }
                if (tile.hasPiece && tile.piece.isWhite() != isWhite) {
                    for(Position move : tile.piece.getLegalMoves()) opponentMoves.add(move);
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

    private void highlightAvailableMoves(ArrayList<Position> moves, boolean isWhite) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position p = this.board[i][j].position;
                for (Position pos : moves) {
                    if (pos.col == p.col && pos.row == p.row) {
                        this.board[i][j].isHighlighted.set(true);
                    }
                }
            }
        }
    }
    private void movePieces(Tile t){
        Position from = this.activeTile.position;
        Position to = t.position;
        System.out.println("Piece being taken: " + t.piece);
        System.out.println("Piece taking: " + this.activeTile.piece);
        t.getChildren().remove(1);
        if (t.hasPiece) {
            graveyard.addPiece(t.piece); //adds the piece to the captured pieces arrayList
            if(t.piece instanceof King) gameEnd();
        }

        t.setPiece(activeTile.piece);
        this.activeTile.setPiece(null);
        activeTile.hasPiece = false;
        this.activeTile = null;
        this.clearHighlightedTiles();
        if (this.gameInProgress.getValue()) checks();
        GameMessage toSend = this.createMoveMessage(from, to);
        this.updatePieceBoards();
        this.changeTopPane("Opponent's Turn!");
        this.sendMessage(toSend);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), ae -> {
            this.isWhiteTurn.set(!isWhiteTurn.getValue());
        }));
        timeline.play();
    }

    private void changeTopPane(String message){
        topTxt.setText(message);
    }
    private void changeBottomPane(String message) {
        btmTxt.setText(message);
    }

    private void gameEnd(){
        changeTopPane("Game over");
        System.out.println("The game is ended");
        this.gameInProgress.set(false);
    }

}
