package martianchess;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private static int numPlayers = 4;
    private static Player players[] = new Player[numPlayers];
    public ArrayList<Pieces> collectedPieces = new ArrayList<Pieces>();
    private static Player currentPlayer;
    private int wins;
    private static int TSLT;
    private Color color;
    private int score;
    
    Player(){
    }
    public int getScore(){
        return(score);
    }
    public static void resetTSLT(){
        TSLT = -1;
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
        Board.reset();
        currentPlayer = players[0];
        TSLT =0;
    }
    public static void draw(Graphics2D g,MartianChess thisObj){
        for (int x = 0; x < Board.numColumns(); x ++){
            for(int y = 0; y < Board.numRows(); y ++){
                if (Board.get(x,y) != null)
                    Board.get(x, y).Draw(g, thisObj, x, y);
            }
        }
    }
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }
    public static void switchCurrentPlayer() {
        if (currentPlayer == players[0])
            currentPlayer = players[1];
        else if (currentPlayer == players[1])
        currentPlayer = players[2];
        else if (currentPlayer == players[2])
            currentPlayer = players[3];
        else if (currentPlayer == players[3])
            currentPlayer = players[0];
        TSLT ++;
        if (TSLT >= 7)
            MartianChess.gameOver();
    }    
    public static Player getPlayer1() {
        return players[0];
    }
    public void addWin(){
        wins ++;
    }
    public int getWin(){
        return(wins);
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
}
