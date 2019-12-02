import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

//TODO Clean up before submitting i.e remove commented out code and add docstrings
public class ChatBox extends VBox {
    private Integer cfIndex=0;
    private int cfLimit = 21;
    private Text[] chatText = new Text[cfLimit];


    public ChatBox(){
//        BackgroundImage myBI= new BackgroundImage(new Image("/forrest.jpg"),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//        Background bg = new Background(myBI);
//        this.setBackground(bg);
        Label title = new Label("Chat Box\n_________________________________________________");
        VBox chatField = new VBox();
        HBox tFAndSend = new HBox();
        Button send = new Button("Send");
        TextField textField = new TextField("Type Here");
        tFAndSend.getChildren().add(textField);
        tFAndSend.getChildren().add(send);
        for ( int i=0; i < chatText.length; i++ ){
            chatText[i] = new Text(" ");
            chatField.getChildren().add(chatText[i]);
        }

        this.getChildren().addAll(title,chatField,tFAndSend);


        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER){
                receiveText("You> " + textField.getText());
                sendChatMessage(createChatMessage(textField.getText()));
                textField.setText("");
            }
        });
        send.setOnMouseClicked(e -> {
            receiveText("You> " + textField.getText());
            sendChatMessage(createChatMessage(textField.getText()));
            textField.setText("");
        });
    }
    public void receiveText(String text){
        if (cfIndex<cfLimit){
            chatText[cfIndex].setText(text);
            cfIndex++;
        } else{
            for (int i=0; i<chatText.length-1; i++){
                chatText[i].setText(chatText[i+1].getText()); }
            chatText[cfIndex-1].setText(text); }
    }
    public GameMessage createChatMessage(String message){
        return new GameMessage(MessageType.CHAT, message);
    }

    public void sendChatMessage(GameMessage m){
        try{
            Chess.out.writeObject(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onMessageReceived(GameMessage m){

        receiveText("Them>" + m.chatMessage);
    }

    public void getMessages(){
        while(true){
            try{
                if ((GameMessage)Chess.in.readObject() != null){
                    GameMessage received = (GameMessage)Chess.in.readObject();
                    this.onMessageReceived(received);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }







}
