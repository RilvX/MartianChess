package martianchess;

import java.awt.*;

public class Player {
    private static int numPlayers=2;
    private static Player players[] = new Player[numPlayers];
    private static Player currentPlayer;
    private Color color;
    private int score;
    public static void Reset() {
        if (players[0] == null) {
            players[0] = new Player(Color.red);
            players[1] = new Player(Color.yellow);
        }
        currentPlayer = players[0];
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
