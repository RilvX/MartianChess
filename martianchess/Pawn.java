package martianchess;

import java.awt.Toolkit;

public class Pawn extends Pieces {
    Pawn(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG");
        val = 1;
    }
}
