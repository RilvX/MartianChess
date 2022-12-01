package martianchess;

import java.awt.Toolkit;

public class Drone extends Pieces{
    Drone(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./MPiece_2Point.PNG");
        val = 2;
    }
    public void move(Board board, int x, int y){
        
    }
}
