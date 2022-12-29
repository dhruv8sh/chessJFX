public class Rook extends Piece {


    Rook(Player p, Pos pos) {
        super(PieceType.Rook, p, pos);
        availableMovesCache = new Pos[14];
    }

    @Override
    void updateAvailableMoves() {
        count = 0;
        getMovesInDirection(1,0);//Down
        getMovesInDirection(-1,0);//Up
        getMovesInDirection(0,1);//Right
        getMovesInDirection(0,-1);//Left
        trimCacheToCount();
    }
}