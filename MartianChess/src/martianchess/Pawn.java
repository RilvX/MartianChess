package martianchess;

import java.awt.Toolkit;

public class Pawn extends Pieces {
    Pawn(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG");
        val = 1;
    }
}
