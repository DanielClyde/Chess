import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Board extends GridPane{
    public Tile[][] tiles;

    public Board () {
        this.tiles = new Tile[8][8];
        putTilesOnBoard();
        addPieces();
    }

    /**
     unfinished, called when a tile is clicked, check if there is a piece on deck and move piece to that tile
     */
//    public void handleOnDeck(int i, int j){
//        boardNodes[i][j] = this.onDeck;
//        for (Node n : gp.getChildren()){
//            System.out.println(n);
//        }
//        gp.add(this.onDeck.getImageView(), i, j);
//    }



    /**
    populates board with appropriate tiles and pieces
     */
    public void putTilesOnBoard() {
        boolean isWhite = true;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile t = new Tile(isWhite, new Position(x, y));
                t.setOnMouseClicked(e -> {
                    System.out.println("clicked");
                    System.out.println(t.position.col + " " + t.position.row);
                });
                this.tiles[x][y] = t;
                this.add(this.tiles[x][y], x, y);
                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

    }

    /**
     used to test putting pictures on the board
     */
    public void addPieces() {
        for (Tile[] row : this.tiles) {
            for (Tile t : row) {
                if (t.position.row == 1) {
                    t.setPiece(new Pawn(t.position, true));
                } else if (t.position.row == 6) {
                    t.setPiece(new Pawn(t.position, false));
                }

            }
        }
    }
}






// populate multidimensional array boardNodes with pieces (doesn't put anything on the gui)
//        for (int i=0; i < 8; i++){
//            for (int j=0; j < 8; j++) {
//                if (j == 1) boardNodes[i][j] = new Pieces.Pawn(p1, this);
//                else if (j == 6) boardNodes[i][j] = new Pieces.Pawn(p2, this);
//                else if (j == 7){
//                    if (i == 2 || i == 5) boardNodes[i][j] = new Pieces.Bishop(p2);
//                    if (i == 1 || i == 6) boardNodes[i][j] = new Pieces.Knight(p2);
//                    if (i == 0 || i == 7) boardNodes[i][j] = new Pieces.Rook(p2);
//                    if (i == 4) boardNodes[i][j] = new Pieces.King(p2);
//                    if (i == 3) boardNodes[i][j] = new Pieces.Queen(p2);
//                }
//                else if (j == 0){
//                    if (i == 2 || i == 5) boardNodes[i][j] = new Pieces.Bishop(p1);
//                    if (i == 1 || i == 6) boardNodes[i][j] = new Pieces.Knight(p1);
//                    if (i == 0 || i == 7) boardNodes[i][j] = new Pieces.Rook(p1);
//                    if (i == 3) boardNodes[i][j] = new Pieces.King(p1);
//                    if (i == 4) boardNodes[i][j] = new Pieces.Queen(p1);
//                }
//            }
//        }
