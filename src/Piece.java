import java.util.Objects;

abstract class Piece {
    final PieceType type;
    final Player player;
    Pos pos;
    Pos[] availableMovesCache;
    protected int count;
    Piece(PieceType pieceType, Player p, Pos pos){
        type = pieceType;
        player = p;
        this.pos = pos;
        count = 0;
    }
    abstract void updateAvailableMoves();
    boolean isValidMove(Pos p){
        updateAvailableMoves();
        return contains(p);
    }
    void getMoveOrKillBy(int offsetx, int offsety){
        Pos temp = pos.getOffset(offsetx, offsety);
        if( temp.isInRange() &&(
                Board.pieceAt(temp) == null ||
                        Objects.requireNonNull(Board.pieceAt(temp)).player != player))
            availableMovesCache[count++] = pos.getOffset(offsetx,offsety);
    }
    boolean contains(Pos p){
        for(Pos pos:availableMovesCache) {
            if(pos == null)
                break;
            else if (pos.equals(p))
                return true;
        }
        return false;
    }
    protected void getMovesInDirection(int a, int b){
        Pos curr = new Pos(pos.x, pos.y);
        curr.moveBy(a,b);
        while( curr.isInRange() && Board.pieceAt(curr) == null){
                availableMovesCache[count++] = curr.clone();
                curr.moveBy(a,b);
        }
        if(Board.pieceAt(curr) != null && Objects.requireNonNull(Board.pieceAt(curr)).player != player)
            availableMovesCache[count++] = curr.clone();
    }
    void trimCacheToCount(){
        while(count < availableMovesCache.length)
            availableMovesCache[count++] = null;
        count = 0;
    }
    void printAvailable(){
        updateAvailableMoves();
        System.out.print("Available:[");
        for(Pos p : availableMovesCache)
            if(p!=null)
                p.print();
        System.out.println("]");
    }
}
