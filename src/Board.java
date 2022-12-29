import java.util.Objects;
import java.util.Scanner;

public class Board {
    private static final Cell[][] board = new Cell[8][8];
    protected static final Player p1 = Player.White, p2 = Player.Black;
    public static class Cell{
        Piece piece;
        void clear(){
            piece = null;
        }
        Cell(Piece p){
            piece = p;
        }
    }
    Board(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = new Cell(null);
            }
        }
        Board.init();
    }
    //returns the taken piece.
    static Piece movePiece(Pos p1, Pos p2){
        Piece temp = Board.pieceAt(p2);
        board[p2.x][p2.y].piece = board[p1.x][p1.y].piece;
        board[p2.x][p2.y].piece.updateAvailableMoves();
        board[p1.x][p1.y].clear();
        return temp;
    }
    static Piece pieceAt(Pos p){
        if(p.isInRange())
            return board[p.x][p.y].piece;
        else return null;
    }

    public static void main(String[] args) {
        new Board();
        Board.waitForMove(p1);
    }

    private static void init() {
        //placing Pawns
        for( int y = 0; y < 8; y++){
            Board.place(6,y, PieceType.Pawn, p1);
            Board.place(1,y, PieceType.Pawn, p2);
        }
        //Placing King
        Board.place(7,4, PieceType.King, p1);
        Board.place(0,4, PieceType.King, p2);
        //Placing Rooks
        Board.place(7,0, PieceType.Rook, p1);
        Board.place(7,7, PieceType.Rook, p1);
        Board.place(0,0, PieceType.Rook, p2);
        Board.place(0,7, PieceType.Rook, p2);
        //Placing Knights
        Board.place(7,1, PieceType.Knight, p1);
        Board.place(7,6, PieceType.Knight, p1);
        Board.place(0,1, PieceType.Knight, p2);
        Board.place(0,6, PieceType.Knight, p2);
        //Placing Bishop
        Board.place(7,2, PieceType.Bishop, p1);
        Board.place(7,5, PieceType.Bishop, p1);
        Board.place(0,2, PieceType.Bishop, p2);
        Board.place(0,5, PieceType.Bishop, p2);
        //Placing Queens
        Board.place(7,3, PieceType.Queen,p1);
        Board.place(0,3, PieceType.Queen,p2);
    }

    private static void place(int x, int y, PieceType pieceType, Player p) {
        Pos tempPos = new Pos(x,y);
        board[x][y].piece = switch(pieceType){
            case King -> new King(p,tempPos);
            case Pawn -> new Pawn(p,tempPos);
            case Queen -> new Queen(p,tempPos);
            case Rook -> new Rook(p,tempPos);
            case Bishop -> new Bishop(p,tempPos);
            case Knight -> new Knight(p,tempPos);
            default -> new Pawn(p,tempPos);
        };
    }
    private static void waitForMove(Player p){
        showBoard();
        Scanner sc = new Scanner(System.in);
        Pos select, to;
        System.out.println("Waiting for move from " + p.name +"...");
        System.out.print("Select piece at indices:");
        String input = sc.next();
        select = new Pos(
                input.charAt(0)-'0',
                input.charAt(1)-'0'
        );
        if(selectionIncorrect(select,p)){
            System.out.print("Incorrect selection! ");
            select.print();
            waitForMove(p);
        }else {
            Objects.requireNonNull(Board.pieceAt(select)).updateAvailableMoves();
            Objects.requireNonNull(Board.pieceAt(select)).printAvailable();
            System.out.print("Move "+Board.pieceAt(select).toString()+" piece to:");
            input = sc.next();
            to = new Pos(
                    input.charAt(0) - '0',
                    input.charAt(1) - '0'
            );
            if(Board.pieceAt(select).isValidMove(to)) {
                Board.movePiece(select, to);
                Board.waitForMove(p==p1?p2:p1);
            }
            else{
                System.out.println("Incorrect move!");
                waitForMove(p);
            }
        }
    }

    private static void showBoard() {
        for (Cell[] c:
             board) {
            for (Cell cell: c){
                if(cell.piece == null)
                    System.out.print("_\t");
                else
                    System.out.print(cell.piece.type.toString()+"\t");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }

    private static boolean selectionIncorrect(Pos pos, Player p){
        return Board.pieceAt(pos) == null ;//|| Board.pieceAt(pos).player != p;
    }
}