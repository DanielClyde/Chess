import java.io.Serializable;
enum MessageType {
    CHAT,
    BOARD,
    INIT,
}
public class GameMessage {
    public MessageType type;
    public String chatMessage;
    public Board board;
    public boolean white;

    GameMessage(MessageType type, Board b, String chatMessage, boolean isWhite) {
        this.type = type;
        switch (this.type) {
            case CHAT:
                this.chatMessage = chatMessage;
                break;
            case BOARD:
                this.board = b;
                break;
            case INIT:
                this.white = isWhite;
                break;
            default:
                break;
        }
    }
}
