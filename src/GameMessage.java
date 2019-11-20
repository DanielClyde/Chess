import java.io.Serializable;
enum MessageType {
    CHAT,
    MOVE,
}
public class GameMessage implements Serializable {
    public MessageType type;
    public String chatMessage;
    public String moveMessage;

    GameMessage(MessageType type, String move, String chatMessage) {
        this.type = type;
        this.moveMessage = move;
        this.chatMessage = chatMessage;
    }
}
