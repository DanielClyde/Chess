import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Piece extends ImageView {
    private Position pos;
    private boolean isWhite;
    private boolean isFirstMove = true;
    private Board board;

    final int SIZE = 40;

    public abstract ArrayList<Position> getLegalMoves();
    public abstract String toString();

    public Piece(Position p, boolean isWhite, Board bd) {
        this.setFitWidth(this.SIZE);
        this.setFitHeight(this.SIZE);
        this.pos = p;
        this.isWhite = isWhite;
        this.board = bd;
    }

    public void setImg(Image img) {
        this.setImage(img);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public Board getBoard() {
        return board;
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }
}
