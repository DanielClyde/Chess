import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * a pane that displays captured pieces in a chess program
 */
//TODO add functionality to dipslay captured pieces
public class GraveyardPane extends VBox {
    public ArrayList<Piece> capturedPieces;

    public GraveyardPane(ArrayList<Piece> pieces){
        this.capturedPieces = pieces;
        //to show what it will look like still need to add functionality to add pieces to graveyard
        this.getChildren().addAll(new Button("White Pieces"), new Button("Black Pieces"));
}

    public void printPieces(){
        for(Piece piece : capturedPieces){
            System.out.print(piece + ", ");
        }
    }
}
