import java.io.Serializable;
enum MessageType {
    CHAT,
    BOARD
}
public class GameMessage {
    public MessageType type;

    public Board board;
    GameMessage(MessageType type, Board b) {
        this.type = type;
        this.board = b;
    }
}
