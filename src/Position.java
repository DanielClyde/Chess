public class Position {
    public int col;
    public int row;
    public boolean canCapture;

    public Position(int x, int y) {
        this.col = x;
        this.row = y;
        this.canCapture = true;
    }

    // used for pawns because they can move forward two but can't capture two ahead of them
    public Position(int x, int y, boolean canCapture){
        this.col = x;
        this.row = y;
        this.canCapture = canCapture;
    }

    public boolean equals(Position p) {
        return this.row == p.row && this.col == p.col;
    }
}