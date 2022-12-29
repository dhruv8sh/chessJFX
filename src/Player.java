public enum Player {
    Black("Player 2"),
    White("Player 1");
    final String name;
    Player(String name){
        this.name = name;
    }
    public boolean hasCheck(){
        return true;
    }
}
