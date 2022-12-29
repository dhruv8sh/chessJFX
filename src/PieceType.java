public enum PieceType {
    King("♔"),
    Queen("♕"),
    Rook("♖"),
    Knight("♘"),
    Bishop("♗"),
    Pawn("♙");
    final String name;
    PieceType(String s){
        name = s;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
