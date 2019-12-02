import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

/**
 * a pane that displays captured pieces in a chess program
 */
public class GraveyardPane extends VBox {
    public ArrayList<Piece> capturedPieces;
    FlowPane whitePieces;
    FlowPane blackPieces;

    public GraveyardPane(){

        System.out.println(this.getHeight() + "\n" + this.getWidth());

        //to show what it will look like still need to add functionality to add pieces to graveyard
        whitePieces = new FlowPane();
        blackPieces = new FlowPane(); //TODO Choose or create a better pane to display the captured pieces
        this.getChildren().addAll(whitePieces, blackPieces);
}

    public void printPieces(){
        for(Piece piece : capturedPieces){
            System.out.print(piece + ", ");
        }
    }

    public void addPiece(Piece piece){
        if (piece.isWhite) whitePieces.getChildren().add(piece);
        else blackPieces.getChildren().add(piece);
    }
}
