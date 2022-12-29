public class Queen extends Piece {
    Queen(Player p, Pos pos) {
        super(PieceType.Queen, p, pos);
        availableMovesCache = new Pos[26];
    }

    @Override
    void updateAvailableMoves() {
        System.out.println("Updating available moves for Queen");
        count = 0;
        getMovesInDirection(-1,-1);//Up-Left
        getMovesInDirection(-1,0);//Up
        getMovesInDirection(-1,1);//Up-Right
        getMovesInDirection(0,1);//Right
        getMovesInDirection(1,1);//Down-Right
        getMovesInDirection(1,0);//Down
        getMovesInDirection(1,-1);//Down-Left
        getMovesInDirection(0,-1);//Left
        trimCacheToCount();
    }
}
