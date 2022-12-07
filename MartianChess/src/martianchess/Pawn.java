package martianchess;

import java.awt.Toolkit;

public class Pawn extends Pieces {
    Pawn(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG");
        val = 1;
    }
    public boolean move(int x, int y, int sx, int sy){
        if ((x == (sx + 1) && y == (sy + 1)
        || x == (sx + 1) && y == (sy - 1)
        || x == (sx - 1) && y == (sy + 1)
        || x == (sx - 1) && y == (sy +-1))){
            if (Board.get(x, y) != null){
                if ((sx == 3 && x == 4) 
                || (sy == 3 && y == 4)
                || (sx == 4 && x == 3)
                || (sy == 4 && y == 3)){
                    Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                    Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                    Player.resetTSLT();
                    Board.set(this, x, y);
                    Board.set(null, sx, sy);
                    return(true);
                }
                else if (Board.get(x, y).pieceImage == Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG")){
                    boolean check = false;
                    Pieces rem = null;
                    for (Pieces obj: Player.getCurrentPlayer().collectedPieces){
                        if (obj instanceof Drone && !check){
                            rem = obj;
                            check = true;
                        }
                    }
                    if (check){
                        Player.getCurrentPlayer().collectedPieces.remove(rem);
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(sx, sy));
                        Board.set(new Drone(), x, y);
                        Board.set(null, sx, sy);
                        Player.resetTSLT();
                        return(true);
                    }
                }
            }
            else {
                Board.set(this, x, y);
                Board.set(null, sx, sy);
                return(true);
            }
        }
        return(false);
    }
}
