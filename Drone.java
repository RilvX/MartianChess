package martianchess;

import java.awt.Toolkit;

public class Drone extends Pieces{
    Drone(Board _board, int x, int y){
        super(_board, x, y);
        pieceImage = Toolkit.getDefaultToolkit().getImage("./MPiece_2Point.PNG");
        val = 2;
    }
    public void move(Board _board, int x, int y){
        if (_board == board){
            if (_board.get(x, y) != null){
                
            }
            else if (x == xpos || y == ypos){
                _board.set(this, x, y);
            }
        }
        else if (board == Player.getPlayer1().getBoard()){
            if (_board == Player.getPlayer2().getBoard() && y == ypos){
                if (_board.get(x, y) != null){
                    Player.getPlayer1().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer3().getBoard() && x == xpos){
                if (_board.get(x, y) != null){
                    Player.getPlayer1().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
        }
        else if (board == Player.getPlayer2().getBoard()){
            if (_board == Player.getPlayer1().getBoard() && y == ypos){
                if (_board.get(x, y) != null){
                    Player.getPlayer2().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer4().getBoard() && x == xpos){
                if (_board.get(x, y) != null){
                    Player.getPlayer2().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
        }
        else if (board == Player.getPlayer3().getBoard()){
            if (_board == Player.getPlayer4().getBoard() && y == ypos){
                if (_board.get(x, y) != null){
                    Player.getPlayer3().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer1().getBoard() && x == xpos){
                if (_board.get(x, y) != null){
                    Player.getPlayer3().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
        }
        else if (board == Player.getPlayer4().getBoard()){
            if (_board == Player.getPlayer3().getBoard() && y == ypos){
                if (_board.get(x, y) != null){
                    Player.getPlayer4().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
            else if (_board == Player.getPlayer2().getBoard() && x == xpos){
                if (_board.get(x, y) != null){
                    Player.getPlayer4().addScore(_board.get(x, y).getVal());
                }
                _board.set(this, x, y);
            }
        }
    }
}
