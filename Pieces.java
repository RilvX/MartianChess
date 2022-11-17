package martianchess;
import java.awt.*;
import java.util.ArrayList;

public abstract class Pieces {
    public static ArrayList<Pieces> pieces = new ArrayList<Pieces>();
    Image pieceImage;
    protected int val;
    int xpos;
    int ypos;

    Pieces()
    {
        xpos = Window.getWidth2()/2;
        ypos = Window.getHeight2()/2;
    }
    
    public void animate()
    {
        
    }

    public static void Init()
    {
    }
    
    public void Draw(Graphics2D g,MartianChess thisObj)
    {
        drawImage(g,thisObj,this.pieceImage,Window.getX(xpos),Window.getYNormal(ypos),0,1,1);
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