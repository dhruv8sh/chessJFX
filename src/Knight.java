public class Knight extends Piece{
    Knight(Player p, Pos pos) {
        super(PieceType.Knight, p, pos);
        availableMovesCache = new Pos[8];
    }

    @Override
    void updateAvailableMoves() {
        count = 0;
        getMoveOrKillBy( 1, 2);
        getMoveOrKillBy(-1, 2);
        getMoveOrKillBy( 1,-2);
        getMoveOrKillBy(-1,-2);
        getMoveOrKillBy( 2, 1);
        getMoveOrKillBy( 2,-1);
        getMoveOrKillBy(-2, 1);
        getMoveOrKillBy(-2,-1);
        trimCacheToCount();
    }
}
