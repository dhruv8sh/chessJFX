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
        count = 0;
        getKillOnlyBy(direction,1);
        getKillOnlyBy(direction,-1);
        if( Board.pieceAt(pos.getOffset(direction,0)) == null) {
            availableMovesCache[count++] = pos.getOffset(direction, 0);
            if( pos.x == startRow && Board.pieceAt(pos.getOffset(direction*2, 0)) == null)
                availableMovesCache[count++] = pos.getOffset(direction*2,0);
        }
        trimCacheToCount();
    }
}
