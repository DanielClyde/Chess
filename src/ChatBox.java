import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.util.Queue;

public class ChatBox extends VBox {
    private Integer cfIndex=0;
    private int cfLimit = 7;
    private Queue<String> messages = new LinkedList<>();
    private Text[] chatText = new Text[cfLimit];


    public ChatBox(){
        VBox chatField = new VBox();
        TextField textField = new TextField("Type Here");
        for ( int i=0; i < chatText.length; i++ ){
            chatText[i] = new Text(" ");
            chatField.getChildren().add(chatText[i]);
        }
        textField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER){
                if (cfIndex<cfLimit){
                    chatText[cfIndex].setText(textField.getText());
                    cfIndex++;
                } else{
                    for (int i=0; i<chatText.length-1; i++){
                        chatText[i].setText(chatText[i+1].getText()); }
                    chatText[cfIndex-1].setText(textField.getText()); }
                textField.setText("");
            }
        });
        this.getChildren().addAll(chatField, textField);


    }







}
