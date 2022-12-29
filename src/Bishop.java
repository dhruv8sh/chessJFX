public class Bishop extends Piece{
    Bishop(Player p, Pos pos) {
        super(PieceType.Bishop, p, pos);
        availableMovesCache = new Pos[12];
    }

    @Override
    void updateAvailableMoves() {
        count = 0;
        getMovesInDirection(1,1);//Down-Right
        getMovesInDirection(1,-1);//Down-Left
        getMovesInDirection(-1,1);//Up-Right
        getMovesInDirection(-1,-1);//Up-Left
        trimCacheToCount();
    }
}
