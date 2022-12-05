package martianchess;

import java.awt.Toolkit;

public class Queen extends Pieces {
    Queen(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./LPiece_3Point.PNG");
        val = 3;
    }
    public void move(Board _board, int x, int y){
        if (_board == board){
            if (x == xpos || y == ypos){
                _board.set(this, x, y);
            }
            for (int s = 0; s < 4; s ++){
                if ((s + xpos) == x && (s + ypos) == y){
                    _board.set(this, x, y);
                }
                if ((s + xpos) == x && (ypos - s) == y){
                    _board.set(this, x, y);
                }
                if ((xpos - s) == x && (s + ypos) == y){
                    _board.set(this, x, y);
                }
                if ((xpos - s) == x && (ypos - s) == y){
                    _board.set(this, x, y);
                }
            }
        }
        else if (board == Player.getPlayer1().getBoard()){
            if (_board == Player.getPlayer2().getBoard() && y == ypos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer2().getBoard()){
                int sx = (3 - ypos);
                for (int sy = (4 - xpos); sy < Board.numColumns(); sy ++){
                    System.out.println("Check: " + sx + ", " + sy);
                    if (sx == x && sy == y){
                        _board.set(this, x, y);
                    }
                    sx --;
                }
            }
            else if (_board == Player.getPlayer3().getBoard() && x == xpos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer3().getBoard()){
                int sx = (xpos);
                for (int sy = (3 - ypos); sy < Board.numColumns(); sy ++){
                    System.out.println("Check: " + sx + ", " + sy);
                    if (sx == x && sy == y){
                        _board.set(this, x, y);
                    }
                    sx --;
                }
            }
        }
        else if (board == Player.getPlayer2().getBoard()){
            if (_board == Player.getPlayer1().getBoard() && y == ypos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer1().getBoard()){
                int sx = (3 - ypos);
                for (int sy = (4 - xpos); sy < Board.numColumns(); sy ++){
                    System.out.println("Check: " + sx + ", " + sy);
                    if (sx == x && sy == y){
                        _board.set(this, x, y);
                    }
                    sx --;
                }
            }
            else if (_board == Player.getPlayer4().getBoard() && x == xpos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer4().getBoard()){
                int sx = (xpos);
                for (int sy = (3 - ypos); sy < Board.numColumns(); sy ++){
                    System.out.println("Check: " + sx + ", " + sy);
                    if (sx == x && sy == y){
                        _board.set(this, x, y);
                    }
                    sx --;
                }
            }
        }
        else if (board == Player.getPlayer3().getBoard()){
            if (_board == Player.getPlayer4().getBoard() && y == ypos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer1().getBoard() && x == xpos){
                _board.set(this, x, y);
            }
        }
        else if (board == Player.getPlayer4().getBoard()){
            if (_board == Player.getPlayer3().getBoard() && y == ypos){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer2().getBoard() && x == xpos){
                _board.set(this, x, y);
            }
        }
    }
}
