package martianchess;

import java.awt.Toolkit;

public class Pawn extends Pieces {
    Pawn(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./SPiece_1Point.PNG");
        val = 1;
    }
    public void move(Board _board, int x, int y){
        if (_board == board){
            if (x == xpos + 1 && y == ypos + 1)
                _board.set(this, x, y);
            else if (x == xpos - 1 && y == ypos - 1)
                _board.set(this, x, y);
            else if (x == xpos + 1 && y == ypos - 1)
                _board.set(this, x, y);
            else if (x == xpos - 1 && y == ypos + 1)
                _board.set(this, x, y);
        }
        else if (board == Player.getPlayer1().getBoard()){
            if (_board == Player.getPlayer4().getBoard() && xpos == 3 && ypos == 3){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer3().getBoard() && ypos == 3 && y == 3){
                if (x == xpos + 1 || x == xpos - 1){
                    _board.set(this, x, y);
                }
            }
            else if (_board == Player.getPlayer2().getBoard() && xpos == 3 && x == 3){
                if (y == ypos + 1 || y == ypos - 1){
                    _board.set(this, x, y);
                }
            }
        }
        else if (board == Player.getPlayer4().getBoard()){
            if (_board == Player.getPlayer1().getBoard() && xpos == 3 && ypos == 3){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer2().getBoard() && ypos == 3 && y == 3){
                if (x == xpos + 1 || x == xpos - 1){
                    _board.set(this, x, y);
                }
            }
            else if (_board == Player.getPlayer3().getBoard() && xpos == 3 && x == 3){
                if (y == ypos + 1 || y == ypos - 1){
                    _board.set(this, x, y);
                }
            }
        }
        else if (board == Player.getPlayer3().getBoard()){
            if (_board == Player.getPlayer2().getBoard() && xpos == 3 && ypos == 3){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer1().getBoard() && ypos == 3 && y == 3){
                if (x == xpos + 1 || x == xpos - 1){
                    _board.set(this, x, y);
                }
            }
            else if (_board == Player.getPlayer4().getBoard() && xpos == 3 && x == 3){
                if (y == ypos + 1 || y == ypos - 1){
                    _board.set(this, x, y);
                }
            }
        }
        else if (board == Player.getPlayer2().getBoard()){
            if (_board == Player.getPlayer3().getBoard() && xpos == 3 && ypos == 3){
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer4().getBoard() && ypos == 3 && y == 3){
                if (x == xpos + 1 || x == xpos - 1){
                    _board.set(this, x, y);
                }
            }
            else if (_board == Player.getPlayer1().getBoard() && xpos == 3 && x == 3){
                if (y == ypos + 1 || y == ypos - 1){
                    _board.set(this, x, y);
                }
            }
        }  
    }
}
