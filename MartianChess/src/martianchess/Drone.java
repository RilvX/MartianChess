package martianchess;

import java.awt.Toolkit;

public class Drone extends Pieces{
    Drone(){
        pieceImage = Toolkit.getDefaultToolkit().getImage("./MPiece_2Point.PNG");
        val = 2;
    }
    public boolean move(int x, int y, int sx, int sy){
        for (int xpos = (sx + 1); xpos < Board.numColumns(); xpos ++){
            if (xpos == x && y == sy){
                if (((x < 4 && sx < 4)
                || (x > 3 && sx > 3))
                && Board.get(xpos, sy) != null){
                    if (Board.get(x, y).pieceImage == Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG")){
                        boolean check = false;
                        Pieces rem = null;
                        for (Pieces obj: Player.getCurrentPlayer().collectedPieces){
                            if (obj instanceof Queen && !check){
                                rem = obj;
                                check = true;
                            }
                        }
                        if (check){
                            Player.getCurrentPlayer().collectedPieces.remove(rem);
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(sx, sy));
                            Board.set(new Queen(), x, y);
                            Board.set(null, sx, sy);
                            Player.resetTSLT();
                            return(true);
                        }
                    }
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
                    if (Board.get(x, y).pieceImage == Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG")){
                        boolean check = false;
                        Pieces rem = null;
                        for (Pieces obj: Player.getCurrentPlayer().collectedPieces){
                            if (obj instanceof Queen && !check){
                                rem = obj;
                                check = true;
                            }
                        }
                        if (check){
                            Player.getCurrentPlayer().collectedPieces.remove(rem);
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(sx, sy));
                            Board.set(new Queen(), x, y);
                            Board.set(null, sx, sy);
                            Player.resetTSLT();
                            return(true);
                        }
                    }
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
                    if (Board.get(x, y).pieceImage == Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG")){
                        boolean check = false;
                        Pieces rem = null;
                        for (Pieces obj: Player.getCurrentPlayer().collectedPieces){
                            if (obj instanceof Queen && !check){
                                rem = obj;
                                check = true;
                            }
                        }
                        if (check){
                            Player.getCurrentPlayer().collectedPieces.remove(rem);
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(sx, sy));
                            Board.set(new Queen(), x, y);
                            Board.set(null, sx, sy);
                            Player.resetTSLT();
                            return(true);
                        }
                    }
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
                    if (Board.get(x, y).pieceImage == Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG")){
                        boolean check = false;
                        Pieces rem = null;
                        for (Pieces obj: Player.getCurrentPlayer().collectedPieces){
                            if (obj instanceof Queen && !check){
                                rem = obj;
                                check = true;
                            }
                        }
                        if (check){
                            Player.getCurrentPlayer().collectedPieces.remove(rem);
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(x, y));
                            Player.getCurrentPlayer().collectedPieces.add(Board.get(sx, sy));
                            Board.set(new Queen(), x, y);
                            Board.set(null, sx, sy);
                            Player.resetTSLT();
                            return(true);
                        }
                    }
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
        return(false);
    }
}
