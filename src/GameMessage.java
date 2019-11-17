import java.io.Serializable;

public class GameMessage {
    public String message;
    public Board board;
    GameMessage(String msg, Board b) {
        this.message = msg;
        this.board = b;
    }
}
