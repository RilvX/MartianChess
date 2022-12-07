package martianchess;
import java.awt.*;
import java.util.ArrayList;

public abstract class Pieces {
    public static ArrayList<Pieces> pieces = new ArrayList<Pieces>();
    Image pieceImage;
    protected int val;

    Pieces()
    {
    }
    
    public void animate()
    {
        
    }

    public static void Init()
    {
    }
    public int getVal(){
        return(val);
    }
    public void Draw(Graphics2D g,MartianChess thisObj, int xpos, int ypos)
    {
        if (Player.getNumPlayers() == 4){
            if (xpos < 4 && ypos < 4)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28),Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) ,0,.125,.125);
            else if (xpos > 3 && ypos < 4)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/4) + (xpos * 48) + 31),Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 48) ,0,.125,.125);
            else if (xpos < 4 && ypos > 3)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/4) + (xpos * 48) + 28),Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 35) ,0,.125,.125);
            else if (xpos > 3 && ypos > 3)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/4) + (xpos * 48) + 31),Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 35) ,0,.125,.125);
        }
        else if (Player.getNumPlayers() == 2){
            if (xpos < 4 && ypos < 4)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/4) + (xpos * 48) + 126),Window.getYNormal(((Window.getHeight2()/4)*3) - (ypos * 47) + 47) ,0,.125,.125);
            if (xpos < 4 && ypos > 3)
                drawImage(g,thisObj,this.pieceImage, Window.getX((Window.getWidth2()/2) - (xpos * 48) + 71),Window.getYNormal(77 + (ypos * 47)),0,.125,.125);
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
    public abstract boolean move(int x, int y, int sx, int sy);
}

//public class Pieces {
//    private int value;
//    Piece()
//    {
//        value = ;
//    }
//    
//    public int getValue()
//    {
//        return (value);
//    }
//
//    public void draw(Graphics2D g,int row,int column,int xdelta,int ydelta) {
//        g.setColor(color);
//        g.fillOval(Window.getX(column*xdelta), Window.getY(row*ydelta),xdelta, ydelta);
//
//        g.setColor(Color.black);        
//        g.setFont (new Font ("Arial",Font.PLAIN, 40));             
//        g.drawString("" + value, Window.getX(column*xdelta+26), Window.getY(row*ydelta+50) );
//    }
//}