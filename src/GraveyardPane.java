import javafx.scene.layout.*;

/**
 * a pane that displays captured pieces in a chess program
 */
public class GraveyardPane extends VBox {
    private FlowPane whitePieces;
    private FlowPane blackPieces;

    public GraveyardPane(){
        System.out.println(this.getHeight() + "\n" + this.getWidth());

        whitePieces = new FlowPane();
        blackPieces = new FlowPane();
        this.getChildren().addAll(whitePieces, blackPieces);
    }

    /**
     * Adds a piece to the graveyard
     * @param piece the piece to be added
     */
    public void addPiece(Piece piece){
        if (piece.isWhite()) whitePieces.getChildren().add(piece);
        else blackPieces.getChildren().add(piece);
    }
}
