public class King extends Piece{

    King(Player p, Pos pos) {
        super(PieceType.King, p, pos);
        availableMovesCache = new Pos[8];
    }

    boolean hasCheckAt(Pos p){
        return false;
    }

    @Override
    void updateAvailableMoves() {
        count = 0;
        getMoveOrKillBy(-1,-1);//Up-Left
        getMoveOrKillBy(-1,0);//Up
        getMoveOrKillBy(-1,1);//Up-Right
        getMoveOrKillBy(0,-1);//Left
        getMoveOrKillBy(0,1);//Right
        getMoveOrKillBy(1,-1);//Down-Left
        getMoveOrKillBy(1,0);//Down
        getMoveOrKillBy(1,1);//Down-Right
        trimCacheToCount();
    }
}
