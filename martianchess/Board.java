package martianchess;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Board {
    private final static int NUM_ROWS = 4;
    private final static int NUM_COLUMNS = 4;  
    private static Pieces board[][] = new Pieces[NUM_ROWS][NUM_COLUMNS];
    public static Image boardImage = Toolkit.getDefaultToolkit().getImage("./Board.PNG");
    
    public void Draw(Graphics2D g,MartianChess thisObj)
    {
        if (this == Player.getPlayer1().getBoard()){
            if(Player.getNumPlayers() == 2)
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/4 + 25)*3, 0, .125, .125);
            else
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4),Window.getYNormal(Window.getHeight2()/4 + 25)*3, 0, .125, .125);
        }
        else if (this == Player.getPlayer2().getBoard()){
            if(Player.getNumPlayers() == 2)
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal((Window.getHeight2()/4) - 25),0,.125,.125);
            else
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4)*3,Window.getYNormal(Window.getHeight2()/4*3 + 25), 0, .125, .125);
        }
        if (Player.getNumPlayers() == 4){
            if (this == Player.getPlayer3().getBoard())
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4),Window.getYNormal((Window.getHeight2()/4) - 25),0,.125,.125);
            if (this == Player.getPlayer4().getBoard())
                drawImage(g,thisObj,this.boardImage,Window.getX(Window.getWidth2()/4*3),Window.getYNormal((Window.getHeight2()/4) - 25),0,.125,.125);
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
}
