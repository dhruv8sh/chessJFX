public class Pos {
    int x, y;
    Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
    boolean isInRange(){
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
    void moveBy(int a, int b){
        x += a;
        y += b;
    }
    static boolean offsetInRange(Pos p, int a, int b){
        return p.x + a >= 0 && p.y + b >= 0 && p.x + a < 8 && p.y + b < 8;
    }
    Pos getOffset(int a, int b){
        return new Pos(x+a, y+b);
    }
    @Override
    public Pos clone(){
        return new Pos(x,y);
    }
    public boolean equals(Pos p){return p.x == x && p.y == y;}
    public void print(){
        System.out.println("Pos("+x+", "+y+")");
    }
}
