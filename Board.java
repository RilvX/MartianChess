package martianchess;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Board {
    private final static int NUM_ROWS = 4;
    private final static int NUM_COLUMNS = 4;  
    public Pieces board[][] = new Pieces[NUM_ROWS][NUM_COLUMNS];
    public static Image boardImage = Toolkit.getDefaultToolkit().getImage("./Board.PNG");
    
    public void reset(){
        for (int x = 0; x < NUM_COLUMNS; x++){
            for (int y = 0; y < NUM_ROWS; y ++){
                board[y][x] = null;
            }
        }
        board[0][0] = new Queen(this, 0, 0);
        board[1][0] = new Queen(this, 1, 0);
        board[0][1] = new Queen(this, 0, 1);
        board[1][1] = new Drone(this, 1, 1);
        board[2][0] = new Drone(this, 2, 0);
        board[0][2] = new Drone(this, 0, 2);
        board[2][2] = new Pawn(this, 2, 2);
        board[2][1] = new Pawn(this, 2, 1);
        board[1][2] = new Pawn(this, 1, 2);
    }
    public Pieces get(int x, int y){
        return(board[x][y]);
    }
    public void set(Pieces piece, int x, int y){
        board[x][y] = piece;
        piece.xpos = x;
        piece.ypos = y;
        piece.board = this;
    }
    public void Draw(Graphics2D g,MartianChess thisObj)
    {
        if (this == Player.getPlayer1().getBoard()){
            if(Player.getNumPlayers() == 2)
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/4*3 - 25), 0, .125, .125);
            else
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4) + 100,Window.getYNormal(Window.getHeight2()/4*3 - 23), 0, .125, .125);
        }
        else if (this == Player.getPlayer2().getBoard()){
            if(Player.getNumPlayers() == 2)
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal((Window.getHeight2()/4) + 25),0,.125,.125);
            else
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4*3) - 100,Window.getYNormal(Window.getHeight2()/4*3 - 23), 0, .125, .125);
        }
        if (Player.getNumPlayers() == 4){
            if (this == Player.getPlayer3().getBoard()){
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4) + 100,Window.getYNormal((Window.getHeight2()/4) + 23),0,.125,.125);
            }
            if (this == Player.getPlayer4().getBoard()){
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4*3) - 100,Window.getYNormal((Window.getHeight2()/4) + 23),0,.125,.125);
            }
        }
    }
    
    public void drawImage(Graphics2D g,MartianChess thisObj,Image image,int xpos,int ypos,double rot,double xscale,double yscale) {
        int width = image.getWidth(thisObj);
        int height = image.getHeight(thisObj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.drawImage(image,-width/2,-height/2,
        width,height,thisObj);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }   
    public static int numRows(){
        return(NUM_ROWS);
    }
    public static int numColumns(){
        return(NUM_COLUMNS);
    }
}
