package martianchess;

import java.awt.Toolkit;

public class Queen extends Pieces {
    Queen(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./LPiece_3Point.PNG");
        val = 3;
    }
    public void move(Board board, int x, int y){
        
    }
}
