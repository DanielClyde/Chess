import java.io.Serializable;
enum MessageType {
    CHAT,
    BOARD,
}
public class GameMessage implements Serializable {
    public MessageType type;
    public String chatMessage;
    public Board board;
    public boolean white;

    GameMessage(MessageType type, Board b, String chatMessage) {
        this.type = type;
        switch (this.type) {
            case CHAT:
                this.chatMessage = chatMessage;
                break;
            case BOARD:
                this.board = b;
                break;
            default:
                break;
        }
    }
}
