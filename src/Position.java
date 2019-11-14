public class Position {
    public int col;
    public int row;

    public Position(int x, int y) {
        this.col = x;
        this.row = y;
    }

    public boolean equals(Position p) {
        return this.row == p.row && this.col == p.col;
    }
}