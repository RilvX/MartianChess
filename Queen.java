package martianchess;

import java.awt.Toolkit;

public class Queen extends Pieces {
    Queen(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./LPiece_3Point.PNG");
        val = 3;
    }
}
