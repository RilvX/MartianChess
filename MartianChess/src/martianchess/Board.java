package martianchess;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Board {
    private final static int NUM_ROWS = 16;
    private final static int NUM_COLUMNS = 16;  
    public static Pieces board[][] = new Pieces[NUM_ROWS][NUM_COLUMNS];
    public static Image boardImage = Toolkit.getDefaultToolkit().getImage("./Board.PNG");
    
    public static void reset(){
        for (int x = 0; x < NUM_COLUMNS; x++){
            for (int y = 0; y < NUM_ROWS; y ++){
                board[y][x] = null;
            }
        }
        board[0][0] = new Queen();
        board[0][1] = new Queen();
        board[1][0] = new Queen();

        board[1][1] = new Drone();
        board[0][2] = new Drone();
        board[2][0] = new Drone();

        board[1][2] = new Pawn();
        board[2][1] = new Pawn();
        board[2][2] = new Pawn();



        board[7][0] = new Queen();
        board[7][1] = new Queen();
        board[6][0] = new Queen();

        board[6][1] = new Drone();
        board[7][2] = new Drone();
        board[5][0] = new Drone();

        board[6][2] = new Pawn();
        board[5][1] = new Pawn();
        board[5][2] = new Pawn();



        board[0][7] = new Queen();
        board[0][6] = new Queen();
        board[1][7] = new Queen();

        board[1][6] = new Drone();
        board[2][7] = new Drone();
        board[0][5] = new Drone();

        board[1][5] = new Pawn();
        board[2][6] = new Pawn();
        board[2][5] = new Pawn();



        board[7][7] = new Queen();
        board[7][6] = new Queen();
        board[6][7] = new Queen();

        board[6][6] = new Drone();
        board[7][5] = new Drone();
        board[5][7] = new Drone();
        
        board[6][5] = new Pawn();
        board[5][6] = new Pawn();
        board[5][5] = new Pawn();
    }
    public static Pieces get(int x, int y){
        return(board[x][y]);
    }
    public static void set(Pieces piece, int x, int y){
        board[x][y] = piece;
    }
    public static void draw(Graphics2D g,MartianChess thisObj)
    {
        if(Player.getNumPlayers() == 2)
            drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal(Window.getHeight2()/4*3 - 25), 0, .125, .125);
        else
        drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/4) + 100,Window.getYNormal(Window.getHeight2()/4*3 - 23), 0, .125, .125);
        if(Player.getNumPlayers() == 2)
            drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/2),Window.getYNormal((Window.getHeight2()/4) + 25),0,.125,.125);
        else
            drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/4*3) - 100,Window.getYNormal(Window.getHeight2()/4*3 - 23), 0, .125, .125);
        if (Player.getNumPlayers() == 4){
            drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/4) + 100,Window.getYNormal((Window.getHeight2()/4) + 23),0,.125,.125);
            
            drawImage(g,thisObj, boardImage,Window.getX(Window.getWidth2()/4*3) - 100,Window.getYNormal((Window.getHeight2()/4) + 23),0,.125,.125);
        }
    }
    
    public static void drawImage(Graphics2D g,MartianChess thisObj,Image image,int xpos,int ypos,double rot,double xscale,double yscale) {
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
