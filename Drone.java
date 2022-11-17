package martianchess;

import java.awt.Toolkit;

public class Drone extends Pieces{
    Drone(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./MPiece_2Point.PNG");
        val = 2;
    }
}
