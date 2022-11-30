package martianchess;

import java.awt.*;

public class Player {
    private static int numPlayers = 4;
    private static Player players[] = new Player[numPlayers];
    private static Player currentPlayer;
    private Board board = new Board();
    private Color color;
    private int score;
    
    Player(){
    }
    
    public static void Reset() {
        if (players[0] == null) {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.blue);
            if (numPlayers >= 3){
                players[2] = new Player(Color.yellow);
                players[3] = new Player(Color.green);
            }
        }
        for (Player obj: players){
            obj.board.reset();
        }
        currentPlayer = players[0];
    }
    public static void draw(Graphics2D g,MartianChess thisObj){
        for (Player obj: players){
            for (int x = 0; x < Board.numColumns(); x ++){
                for(int y = 0; y < Board.numRows(); y ++){
                    if (obj.board.get(x,y) != null)
                        obj.board.get(x, y).Draw(g, thisObj);
                }
            }
        }
    }
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public static void switchCurrentPlayer() {
        if (currentPlayer == players[0])
            currentPlayer = players[1];
        else
            currentPlayer = players[0];
    }    
    public static Player getPlayer1() {
        return players[0];
    }
    public static Player getPlayer2() {
        return players[1];
    }    
    public static Player getPlayer3() {
        return players[2];
    }
    public static Player getPlayer4() {
        return players[3];
    }    
    public Board getBoard(){
        return(board);
    }
    public static int getNumPlayers(){
        return(numPlayers);
    }
    public Player(Color _color) {
        color = _color;
        score = 0;
    }    
    public Color getColor() {
        return (color);
    }
    public void addScore(int _value)
    {
        score += _value;
    }
    public int getScore()
    {
        return score;
    }
}
