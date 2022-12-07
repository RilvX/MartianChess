package martianchess;

import java.awt.Toolkit;

public class Queen extends Pieces {
    Queen(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./LPiece_3Point.PNG");
        val = 3;
    }
    public boolean move(int x, int y, int sx, int sy){
        for (int xpos = (sx + 1); xpos < Board.numColumns(); xpos ++){
            if (xpos == x && y == sy){
                if (((x < 4 && sx < 4)
                || (x > 3 && sx > 3))
                && Board.get(xpos, sy) != null){

                }
                else{
                    if (Board.get(x, y) != null)
                        Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                        Player.resetTSLT();
                    Board.set(this, x, y);
                    Board.set(null, sx, sy);
                    return(true);
                }
            }
            else if (Board.get(xpos, sy) != null){
                xpos = Board.numColumns();
            }
        }
        for (int xpos = (sx - 1); xpos >= 0; xpos --){
            if (xpos == x && y == sy){
                if (((x < 4 && sx < 4)
                || (x > 3 && sx > 3))
                && Board.get(xpos, sy) != null){
                    
                }
                else{
                    if (Board.get(x, y) != null)
                        Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                        Player.resetTSLT();
                    Board.set(this, x, y);
                    Board.set(null, sx, sy);
                    return(true);
                }
            }
            else if (Board.get(xpos, sy) != null){
                xpos = -1;
            }
        }
        for (int ypos = (sy + 1); ypos < Board.numColumns(); ypos ++){
            if (ypos == y && x == sx){
                if (((y < 4 && sy < 4)
                || (y > 3 && sy > 3))
                && Board.get(sx, ypos) != null){
                    
                }
                else{
                    if (Board.get(x, y) != null)
                        Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                        Player.resetTSLT();
                    Board.set(this, x, y);
                    Board.set(null, sx, sy);
                    return(true);
                }
            }
            else if (Board.get(sx, ypos) != null){
                ypos = Board.numRows();
            }
        }
        for (int ypos = (sy - 1); ypos >= 0; ypos --){
            if (ypos == y && x == sx){
                if (((y < 4 && sy < 4)
                || (y > 3 && sy > 3))
                && Board.get(sx, ypos) != null){
                    
                }
                else{
                    if (Board.get(x, y) != null)
                        Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                        Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                        Player.resetTSLT();
                    Board.set(this, x, y);
                    Board.set(null, sx, sy);
                    return(true);
                }
            }
            else if (Board.get(sx, ypos) != null){
                ypos = -1;
            }
        }
        for (int i = 1; (sx + i) < Board.numColumns() && (sy + i) < Board.numRows(); i ++){
            if ((i + sx) == x && (i + sy) == y){
                if (Board.get(x, y) != null)
                    Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                    Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                    Player.resetTSLT();
                Board.set(this, x, y);
                Board.set(null, sx, sy);
                return(true);
            }
            else if (Board.get((sx + i), (sy + i)) != null){
                i = Board.numColumns();
            }
        }
        for (int i = 1; (sx - i) >= 0 && (sy + i) < Board.numRows(); i ++){    
            if ((sx - i) == x && (i + sy) == y){
                if (Board.get(x, y) != null)
                    Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                    Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                    Player.resetTSLT();
                Board.set(this, x, y);
                Board.set(null, sx, sy);
                return(true);
            }
            else if (Board.get((sx - i), (sy + i)) != null){
                i = Board.numColumns();
            }
        }
        for (int i = 1; (sx + i) < Board.numColumns() && (sy - i) >= 0; i ++){ 
            if ((i + sx) == x && (sy - i) == y){
                if (Board.get(x, y) != null)
                    Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                    Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                    Player.resetTSLT();
                Board.set(this, x, y);
                Board.set(null, sx, sy);
                return(true);
            }
            else if (Board.get((sx + i), (sy - i)) != null){
                i = Board.numColumns();
            }
        }
        for (int i = 1; (sx - i) >= 0 && (sy - i) >= 0; i ++){ 
            if ((sx - i) == x && (sy - i) == y){
                if (Board.get(x, y) != null)
                    Player.getCurrentPlayer().addScore(Board.get(x, y).val);
                    Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                    Player.resetTSLT();
                Board.set(this, x, y);
                Board.set(null, sx, sy);
                return(true);
            }
            else if (Board.get((sx - i), (sy - i)) != null){
                i = Board.numColumns();
            }
        }
        return(false);
    }
}
