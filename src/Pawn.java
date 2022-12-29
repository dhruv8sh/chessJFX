public class Pawn extends Piece{
    private final int startRow, direction;
    Pawn(Player p, Pos pos){
        super(PieceType.Pawn, p, pos);
        availableMovesCache = new Pos[4];
        startRow = p == Player.Black? 1 : 6;
        direction = p == Player.Black? 1:-1;
    }
    @Override
    void updateAvailableMoves() {
        int direction = player == Player.Black ? 1 : -1;
        count = 0;
        getMoveOrKillBy(direction,-1);
        getMoveOrKillBy(direction,1);
        if( Board.pieceAt(pos.getOffset(direction,0)) == null) {
            availableMovesCache[count++] = pos.getOffset(direction, 0);
            if( pos.x == startRow && Board.pieceAt(pos.getOffset(direction*2, 0)) == null)
                availableMovesCache[count++] = pos.getOffset(direction*2,0);
        }
        trimCacheToCount();
    }
}
